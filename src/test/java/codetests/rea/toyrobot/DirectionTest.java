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

  @Test
  public void testRightRotation() {
    assertThat(Direction.NORTH.right(), is(Direction.EAST));
    assertThat(Direction.EAST.right(), is(Direction.SOUTH));
    assertThat(Direction.SOUTH.right(), is(Direction.WEST));
    assertThat(Direction.WEST.right(), is(Direction.NORTH));
  }
}
