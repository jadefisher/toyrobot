package codetests.rea.toyrobot.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import codetests.rea.toyrobot.Direction;
import codetests.rea.toyrobot.state.RobotState;
import codetests.rea.toyrobot.state.TableTopState;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for the Command Factory
 */
public class CommandFactoryTest {

  private TableTopState initialTableTopState;
  private CommandFactory commandFactory;

  @Before
  public void setUp() {
    initialTableTopState = new TableTopState(5, 5);
    commandFactory = CommandFactory.getInstance();
  }

  @Test
  public void testPlaceCommandAddsRobotToTableTop() {
    TableTopState tableTopState = commandFactory.apply("PLACE 2,2,NORTH", initialTableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.NORTH, 2, 2)));
  }

  @Test
  public void testPlaceCommandIsIgnoredIfOffTable() {
    assertThat(
        commandFactory
            .apply("PLACE 5,2,WEST", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testUnparsablePlaceCommandIsIgnored() {
    // Bad Direction
    assertThat(
        commandFactory
            .apply("PLACE 5,2,W", initialTableTopState), is(initialTableTopState));

    // Missing arguments
    assertThat(
        commandFactory
            .apply("PLACE 5,WEST", initialTableTopState), is(initialTableTopState));

    // Bad number for x
    assertThat(
        commandFactory
            .apply("PLACE two,2,WEST", initialTableTopState), is(initialTableTopState));

    // Bad number for y
    assertThat(
        commandFactory
            .apply("PLACE 2,two,WEST", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testRotateLeftCommand() {
    // Place robot
    TableTopState tableTopState = commandFactory.apply("PLACE 2,2,NORTH", initialTableTopState);

    // Rotate LEFT
    tableTopState = commandFactory.apply("LEFT", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.WEST, 2, 2)));
  }

  @Test
  public void testRotateLeftCommandIsIgnoredIfRobotNotPlaced() {
    // Rotate LEFT
    assertThat(
        commandFactory
            .apply("LEFT", initialTableTopState), is(initialTableTopState));
  }

  @Test
  public void testRotateRightCommand() {
    // Place robot
    TableTopState tableTopState = commandFactory.apply("PLACE 2,2,NORTH", initialTableTopState);

    // Rotate LEFT
    tableTopState = commandFactory.apply("RIGHT", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
    assertThat(tableTopState.getRobotState(), is(new RobotState(Direction.EAST, 2, 2)));
  }

  @Test
  public void testRotateRightCommandIsIgnoredIfRobotNotPlaced() {
    // Rotate LEFT
    assertThat(
        commandFactory
            .apply("RIGHT", initialTableTopState), is(initialTableTopState));
  }
}
