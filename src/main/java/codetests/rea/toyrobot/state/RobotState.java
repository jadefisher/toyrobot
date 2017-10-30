package codetests.rea.toyrobot.state;

import codetests.rea.toyrobot.Direction;

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
    return this;
  }
}
