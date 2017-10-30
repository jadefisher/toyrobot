package codetests.rea.toyrobot;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Unit tests for the Direction enum
 */
public class DirectionTest {

  @Test
  public void testLeftRotation() {
    assertThat(Direction.NORTH.left(), is(Direction.WEST));
    assertThat(Direction.EAST.left(), is(Direction.NORTH));
    assertThat(Direction.SOUTH.left(), is(Direction.EAST));
    assertThat(Direction.WEST.left(), is(Direction.SOUTH));
  }
}
