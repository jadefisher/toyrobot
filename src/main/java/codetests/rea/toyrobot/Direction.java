package codetests.rea.toyrobot;

public enum Direction {
  NORTH(0),
  EAST(1),
  SOUTH(2),
  WEST(3);

  private final Integer value;

  Direction(final Integer value) {
    this.value = value;
  }

  private Direction fromValue(final Integer value) {
    for (Direction direction : Direction.values()) {
      if (direction.value == value) {
        return direction;
      }
    }
    throw new RuntimeException("Unknown direction value " + value);
  }

  public Direction left() {
    final Integer numValues = Direction.values().length;
    final Integer leftValue = ((this.value - 1) % numValues + numValues) % numValues;
    return fromValue(leftValue);
  }

  public Direction right() {
    return this;
  }
}
