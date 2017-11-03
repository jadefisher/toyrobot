package net.jadefisher.codetests.toyrobot.state;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import net.jadefisher.codetests.toyrobot.Direction;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for the RobotState immutable domain object
 */
public class RobotStateTest {

  private RobotState initialState;

  @Before
  public void setUp() {
    initialState = new RobotState(Direction.NORTH, 0, 0);
  }

  @Test
  public void testRobotStateLeftRotation() {
    initialState.rotateLeft();

    // Robot shouldn't change it's internals after operation applied
    assertThat(initialState, is(new RobotState(Direction.NORTH, 0, 0)));

    // Translation should be applied to the returned RobotState object
    assertThat(initialState.rotateLeft(), is(new RobotState(Direction.WEST, 0, 0)));
  }

  @Test
  public void testRobotStateRightRotation() {
    initialState.rotateRight();

    // Robot shouldn't change it's internals after operation applied
    assertThat(initialState, is(new RobotState(Direction.NORTH, 0, 0)));

    // Translation should be applied to the returned RobotState object
    assertThat(initialState.rotateRight(), is(new RobotState(Direction.EAST, 0, 0)));
  }

  @Test
  public void testRobotStateMove() {
    initialState.move();

    // Robot shouldn't change it's internals after operation applied
    assertThat(initialState, is(new RobotState(Direction.NORTH, 0, 0)));

    // Move in NORTH direction
    assertThat(initialState.move(), is(new RobotState(Direction.NORTH, 0, 1)));

    // Move in EAST direction
    assertThat(initialState.rotateRight().move(), is(new RobotState(Direction.EAST, 1, 0)));

    // Move in SOUTH direction
    assertThat(initialState.rotateRight().rotateRight().move(),
        is(new RobotState(Direction.SOUTH, 0, -1)));

    // Move in WEST direction
    assertThat(initialState.rotateLeft().move(), is(new RobotState(Direction.WEST, -1, 0)));
  }
}
