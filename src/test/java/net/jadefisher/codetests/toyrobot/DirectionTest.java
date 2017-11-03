package net.jadefisher.codetests.toyrobot;

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

  @Test
  public void testXComponentValues() {
    assertThat(Direction.NORTH.getxComponent(), is(0));
    assertThat(Direction.EAST.getxComponent(), is(1));
    assertThat(Direction.SOUTH.getxComponent(), is(0));
    assertThat(Direction.WEST.getxComponent(), is(-1));
  }

  @Test
  public void testYComponentValues() {
    assertThat(Direction.NORTH.getyComponent(), is(1));
    assertThat(Direction.EAST.getyComponent(), is(0));
    assertThat(Direction.SOUTH.getyComponent(), is(-1));
    assertThat(Direction.WEST.getyComponent(), is(0));
  }
}
