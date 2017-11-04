package net.jadefisher.codetests.toyrobot;

/**
 * All the valid directions which the Robot can face.
 */
public enum Direction {
  NORTH(0, 0, 1),
  EAST(1, 1, 0),
  SOUTH(2, 0, -1),
  WEST(3, -1, 0);

  private final Integer rotationalValue;
  private final Integer xComponent;
  private final Integer yComponent;

  Direction(final Integer rotationalValue,
      final Integer xComponent,
      final Integer yComponent) {
    this.rotationalValue = rotationalValue;
    this.xComponent = xComponent;
    this.yComponent = yComponent;
  }

  /**
   * @return The Direction to the left of this Direction.
   */
  public Direction left() {
    final Integer numValues = Direction.values().length;
    final Integer leftValue = ((this.rotationalValue - 1) % numValues + numValues) % numValues;
    return fromRotationalValue(leftValue);
  }

  /**
   * @return The Direction to the right of this Direction.
   */
  public Direction right() {
    final Integer numValues = Direction.values().length;
    final Integer rightValue = ((this.rotationalValue + 1) % numValues + numValues) % numValues;
    return fromRotationalValue(rightValue);
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

  private Direction fromRotationalValue(final Integer rotationalValue) {
    for (Direction direction : Direction.values()) {
      if (direction.rotationalValue == rotationalValue) {
        return direction;
      }
    }
    throw new RuntimeException("Unknown direction rotationalValue " + rotationalValue);
  }
}
