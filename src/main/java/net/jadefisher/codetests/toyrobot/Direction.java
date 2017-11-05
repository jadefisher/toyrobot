package net.jadefisher.codetests.toyrobot;

/**
 * All the valid directions which the Robot can face.
 * Note, they are declared in clockwise order so that the ordinal values allow
 * determination of the LEFT and RIGHT Directions, from a given direction.
 */
public enum Direction {
  NORTH(0, 1),
  EAST(1, 0),
  SOUTH(0, -1),
  WEST(-1, 0);

  private final Integer xComponent;
  private final Integer yComponent;

  Direction(final Integer xComponent, final Integer yComponent) {
    this.xComponent = xComponent;
    this.yComponent = yComponent;
  }

  /**
   * @return The Direction to the left of this Direction.
   */
  public Direction left() {
    final Integer numValues = Direction.values().length;
    final Integer leftOrdinal = ((this.ordinal() - 1) % numValues + numValues) % numValues;
    return Direction.values()[leftOrdinal];
  }

  /**
   * @return The Direction to the right of this Direction.
   */
  public Direction right() {
    final Integer numValues = Direction.values().length;
    final Integer rightOrdinal = ((this.ordinal() + 1) % numValues + numValues) % numValues;
    return Direction.values()[rightOrdinal];
  }

  /**
   * @return The component along the X-Axis attributed to this Direction.
   */
  public Integer getxComponent() {
    return xComponent;
  }

  /**
   * @return The component along the Y-Axis attributed to this Direction.
   */
  public Integer getyComponent() {
    return yComponent;
  }
}
