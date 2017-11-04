package net.jadefisher.codetests.toyrobot.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import net.jadefisher.codetests.toyrobot.Direction;
import net.jadefisher.codetests.toyrobot.InvalidCommandException;
import net.jadefisher.codetests.toyrobot.command.CommandDispatcher.ReportingOutput;
import net.jadefisher.codetests.toyrobot.state.RobotState;
import net.jadefisher.codetests.toyrobot.state.TableTopState;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit Tests for the CommandDispatcher
 */
public class CommandDispatcherTest {

  private TableTopState initialTableTopState;
  private CommandDispatcher commandDispatcher;
  private ReportingOutput reportingOutput;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() {
    initialTableTopState = new TableTopState(5, 5);
    reportingOutput = mock(ReportingOutput.class);
    commandDispatcher = new CommandDispatcher(reportingOutput);
  }

  @Test
  public void testPlaceCommandAddsRobotToTableTop() {
    TableTopState tableTopState = commandDispatcher.apply("PLACE 2,2,NORTH", initialTableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState().get(), CoreMatchers
        .is(new RobotState(Direction.NORTH, 2, 2)));
  }

  @Test
  public void testPlaceCommandIsIgnoredIfOffTable() {
    assertThat(
        commandDispatcher
            .apply("PLACE 5,2,WEST", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testUnparsableDirectionPlaceCommandIsHandled() {
    thrown.expect(InvalidCommandException.class);
    thrown.expectMessage(
        "Cannot parse direction 'W'. It should be one of [NORTH, EAST, SOUTH, WEST]");
    commandDispatcher.apply("PLACE 5,2,W", initialTableTopState);
  }

  @Test
  public void testMissingArgumentsPlaceCommandIsHandled() {
    thrown.expect(InvalidCommandException.class);
    thrown.expectMessage("Require X,Y,F format for PLACE command");
    commandDispatcher.apply("PLACE 5,WEST", initialTableTopState);
  }

  @Test
  public void testIntegerParseErrorForXPlaceCommandIsHandled() {
    thrown.expect(InvalidCommandException.class);
    thrown.expectMessage("Require X,Y,F format for PLACE command");
    commandDispatcher.apply("PLACE two,2,WEST", initialTableTopState);
  }

  @Test
  public void testIntegerParseErrorForYPlaceCommandIsHandled() {
    thrown.expect(InvalidCommandException.class);
    thrown.expectMessage("Require X,Y,F format for PLACE command");
    commandDispatcher.apply("PLACE 2,two,WEST", initialTableTopState);
  }

  @Test
  public void testRotateLeftCommand() {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 2,2,NORTH", initialTableTopState);

    // Rotate LEFT
    tableTopState = commandDispatcher.apply("LEFT", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState().get(), is(new RobotState(Direction.WEST, 2, 2)));
  }

  @Test
  public void testRotateLeftCommandIsIgnoredIfRobotNotPlaced() {
    // Rotate LEFT
    assertThat(
        commandDispatcher
            .apply("LEFT", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testRotateRightCommand() {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 2,2,NORTH", initialTableTopState);

    // Rotate LEFT
    tableTopState = commandDispatcher.apply("RIGHT", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState().get(), is(new RobotState(Direction.EAST, 2, 2)));
  }

  @Test
  public void testRotateRightCommandIsIgnoredIfRobotNotPlaced() {
    // Rotate LEFT
    assertThat(
        commandDispatcher
            .apply("RIGHT", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testMoveCommand() {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 2,2,NORTH", initialTableTopState);

    // Move robot
    tableTopState = commandDispatcher.apply("MOVE", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState().get(), is(new RobotState(Direction.NORTH, 2, 3)));
  }

  @Test
  public void testMoveCommandIsIgnoredIfResultsInRobotOffTable() {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 0,0,NORTH", initialTableTopState);

    // Keep moving NORTH way beyond the boundary
    for (int i = 0; i < 10; i++) {
      tableTopState = commandDispatcher.apply("MOVE", tableTopState);
    }

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState().get(), is(new RobotState(Direction.NORTH, 0, 4)));
  }

  @Test
  public void testMoveCommandIsIgnoredWhenRobotNotPlaced() {
    assertThat(
        commandDispatcher
            .apply("MOVE", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testReportCommand() {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 0,0,NORTH", initialTableTopState);

    // Report robot state
    commandDispatcher.apply("REPORT", tableTopState);

    verify(reportingOutput, times(1)).report("0,0,NORTH");
  }

  @Test
  public void testReportCommandIsIgnoredWhenRobotNotPlaced() {
    commandDispatcher.apply("REPORT", initialTableTopState);

    verify(reportingOutput, times(0)).report(anyString());
  }
}
