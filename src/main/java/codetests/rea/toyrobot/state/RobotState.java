package codetests.rea.toyrobot.state;

import codetests.rea.toyrobot.Direction;
import java.util.Objects;

public class RobotState {
  private final Direction direction;
  private final Integer xPosition;
  private final Integer yPosition;

  public RobotState(final Direction direction, final Integer xPosition, final Integer yPosition) {
    this.direction = direction;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  public Direction getDirection() {
    return direction;
  }

  public Integer getxPosition() {
    return xPosition;
  }

  public Integer getyPosition() {
    return yPosition;
  }

  public RobotState rotateLeft() {
    return new RobotState(direction.left(), xPosition, yPosition);
  }

  public RobotState rotateRight() {
    return new RobotState(direction.right(), xPosition, yPosition);
  }

  public RobotState move() {
    return new RobotState(direction,
        xPosition + direction.getxComponent(),
        yPosition + direction.getyComponent());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RobotState that = (RobotState) o;
    return direction == that.direction &&
        Objects.equals(xPosition, that.xPosition) &&
        Objects.equals(yPosition, that.yPosition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, xPosition, yPosition);
  }
}
