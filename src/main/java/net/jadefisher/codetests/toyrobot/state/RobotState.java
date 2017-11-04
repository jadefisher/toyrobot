package net.jadefisher.codetests.toyrobot.state;

import net.jadefisher.codetests.toyrobot.Direction;
import java.util.Objects;

/**
 * Holds the state of the Robot, that is it's position and direction. Also provides
 * behavior to support rotating left and right, and also supports moving in the forward direction.
 *
 * This class is immutable, so all state is provided in the constructor.
 */
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

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("RobotState{");
    sb.append("direction=").append(direction);
    sb.append(", xPosition=").append(xPosition);
    sb.append(", yPosition=").append(yPosition);
    sb.append('}');
    return sb.toString();
  }
}
