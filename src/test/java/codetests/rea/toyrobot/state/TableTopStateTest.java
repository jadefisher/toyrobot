package codetests.rea.toyrobot.state;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Unit tests for the TableTopState domain object.
 */
public class TableTopStateTest {

  @Test
  public void tableTopIsInvalidUntilRobotPlaced() {
    final TableTopState tableTopState = new TableTopState(5, 5);

    assertThat(tableTopState.isInvalid(), is(true));
  }
}
