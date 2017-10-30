package codetests.rea.toyrobot.state;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import codetests.rea.toyrobot.Direction;
import org.junit.Test;

/**
 * Unit Tests for the RobotState immutable domain object
 */
public class RobotStateTest {
  @Test
  public void testRobotStateLeftRotation() {
    final RobotState robotState = new RobotState(Direction.NORTH, 0, 0);

    robotState.rotateLeft();

    // Robot shouldn't change it's internals after operation applied
    assertThat(robotState, is(new RobotState(Direction.NORTH, 0, 0)));

    // Translation should be applied to the returned RobotState object
    assertThat(robotState.rotateLeft(), is(new RobotState(Direction.WEST, 0, 0)));
  }

  @Test
  public void testRobotStateRightRotation() {
    final RobotState robotState = new RobotState(Direction.NORTH, 0, 0);

    robotState.rotateRight();

    // Robot shouldn't change it's internals after operation applied
    assertThat(robotState, is(new RobotState(Direction.NORTH, 0, 0)));

    // Translation should be applied to the returned RobotState object
    assertThat(robotState.rotateRight(), is(new RobotState(Direction.EAST, 0, 0)));
  }

  @Test
  public void testRobotStateMove() {
    final RobotState robotState = new RobotState(Direction.NORTH, 0, 0);

    robotState.move();

    // Robot shouldn't change it's internals after operation applied
    assertThat(robotState, is(new RobotState(Direction.NORTH, 0, 0)));

    // Translation should be applied to the returned RobotState object
    assertThat(robotState.move(), is(new RobotState(Direction.NORTH, 0, 1)));
  }
}
