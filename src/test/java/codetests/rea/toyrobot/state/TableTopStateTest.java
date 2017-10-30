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
}
