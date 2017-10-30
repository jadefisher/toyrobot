package codetests.rea.toyrobot.state;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import codetests.rea.toyrobot.Direction;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the TableTopState domain object.
 */
public class TableTopStateTest {

  private TableTopState initialState;

  @Before
  public void setUp() {
    initialState = new TableTopState(5, 5);
  }

  @Test
  public void tableTopIsInvalidUntilRobotPlaced() {
    assertThat(initialState.isInvalid(), is(true));

    assertThat(initialState.place(Direction.WEST, 2, 2).isInvalid(), is(false));
  }

  @Test
  public void tableTopIsInvalidWhenRobotPlacedOffEdge() {
    assertThat(initialState.place(Direction.WEST, 5, 2).isInvalid(), is(true));
    assertThat(initialState.place(Direction.WEST, 2, 5).isInvalid(), is(true));
    assertThat(initialState.place(Direction.WEST, -1, 2).isInvalid(), is(true));
    assertThat(initialState.place(Direction.WEST, 2, -1).isInvalid(), is(true));
  }

  @Test
  public void tableTopCanRotateRobotLeft() {
    assertThat(initialState.place(Direction.NORTH, 0, 0).rotateRobotLeft().getRobotState(),
        is(new RobotState(Direction.WEST, 0, 0)));
  }

  @Test
  public void tableTopCanRotateRobotRight() {
    assertThat(initialState.place(Direction.NORTH, 0, 0).rotateRobotRight().getRobotState(),
        is(new RobotState(Direction.EAST, 0, 0)));
  }

  @Test
  public void tableTopCanMoveRobot() {
    assertThat(initialState.place(Direction.NORTH, 0, 0).moveRobot().getRobotState(),
        is(new RobotState(Direction.NORTH, 0, 1)));
  }

  @Test
  public void tableTopCanMoveRobotOffBoard() {
    assertThat(initialState
            .place(Direction.NORTH, 0, 0)
            .moveRobot() // Move to 0, 1
            .moveRobot() // Move to 0, 2
            .moveRobot() // Move to 0, 3
            .moveRobot() // Move to 0, 4
            .moveRobot() // Move to 0, 5
            .isInvalid(),
        is(true));
  }

  @Test
  public void tableTopResetsRobotOnPlace() {
    assertThat(initialState
            .place(Direction.NORTH, 0, 0)
            .moveRobot()
            .moveRobot()
            .rotateRobotLeft()
            .place(Direction.SOUTH, 2, 2)
            .getRobotState(),
        is(new RobotState(Direction.SOUTH, 2, 2)));
  }
}
