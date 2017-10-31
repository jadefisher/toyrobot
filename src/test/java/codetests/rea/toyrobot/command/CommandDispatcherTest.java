package codetests.rea.toyrobot.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import codetests.rea.toyrobot.Direction;
import codetests.rea.toyrobot.state.RobotState;
import codetests.rea.toyrobot.state.TableTopState;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for the Command Factory
 */
public class CommandDispatcherTest {

  private TableTopState initialTableTopState;
  private CommandDispatcher commandDispatcher;
  private ReportingOutput reportingOutput;

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
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.NORTH, 2, 2)));
  }

  @Test
  public void testPlaceCommandIsIgnoredIfOffTable() {
    assertThat(
        commandDispatcher
            .apply("PLACE 5,2,WEST", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testUnparsablePlaceCommandIsIgnored() {
    // Bad Direction
    assertThat(
        commandDispatcher
            .apply("PLACE 5,2,W", initialTableTopState), is(initialTableTopState));

    // Missing arguments
    assertThat(
        commandDispatcher
            .apply("PLACE 5,WEST", initialTableTopState), is(initialTableTopState));

    // Bad number for x
    assertThat(
        commandDispatcher
            .apply("PLACE two,2,WEST", initialTableTopState), is(initialTableTopState));

    // Bad number for y
    assertThat(
        commandDispatcher
            .apply("PLACE 2,two,WEST", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testRotateLeftCommand() {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 2,2,NORTH", initialTableTopState);

    // Rotate LEFT
    tableTopState = commandDispatcher.apply("LEFT", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.WEST, 2, 2)));
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
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.EAST, 2, 2)));
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
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.NORTH, 2, 3)));
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
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.NORTH, 0, 4)));
  }

  @Test
  public void testReportCommand() throws IOException {
    // Place robot
    TableTopState tableTopState = commandDispatcher.apply("PLACE 0,0,NORTH", initialTableTopState);

    // Report robot state
    commandDispatcher.apply("REPORT", tableTopState);

    verify(reportingOutput, times(1)).report("0,0,NORTH");
  }
}
