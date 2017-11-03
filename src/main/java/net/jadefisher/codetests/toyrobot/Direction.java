package net.jadefisher.codetests.toyrobot;

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

  public Direction left() {
    final Integer numValues = Direction.values().length;
    final Integer leftValue = ((this.rotationalValue - 1) % numValues + numValues) % numValues;
    return fromRotationalValue(leftValue);
  }

  public Direction right() {
    final Integer numValues = Direction.values().length;
    final Integer rightValue = ((this.rotationalValue + 1) % numValues + numValues) % numValues;
    return fromRotationalValue(rightValue);
  }

  public Integer getxComponent() {
    return xComponent;
  }

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
