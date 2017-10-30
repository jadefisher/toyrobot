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
    assertThat(robotState.getDirection(), is(Direction.NORTH));
    assertThat(robotState.getxPosition(), is(0));
    assertThat(robotState.getyPosition(), is(0));

    final RobotState translatedRobotState = robotState.rotateLeft();

    // Translation should be applied returned RobotState object
    assertThat(translatedRobotState.getDirection(), is(Direction.WEST));
    assertThat(translatedRobotState.getxPosition(), is(0));
    assertThat(translatedRobotState.getyPosition(), is(0));
  }
}
