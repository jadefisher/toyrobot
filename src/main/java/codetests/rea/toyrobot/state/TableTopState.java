package codetests.rea.toyrobot.state;

import codetests.rea.toyrobot.Direction;
import java.util.Objects;
import java.util.Optional;

public class TableTopState {

  private final Integer width;
  private final Integer height;
  private final RobotState robotState;

  public TableTopState(final Integer width, final Integer height) {
    this(width, height, null);
  }

  private TableTopState(final Integer width, final Integer height, RobotState robotState) {
    this.width = width;
    this.height = height;
    this.robotState = robotState;
  }

  public Optional<RobotState> getRobotState() {
    return Optional.ofNullable(robotState);
  }

  public boolean isInvalid() {
    return robotState == null ||
        robotState.getxPosition() >= width ||
        robotState.getxPosition() < 0 ||
        robotState.getyPosition() >= height ||
        robotState.getyPosition() < 0;
  }

  public TableTopState place(final Direction direction, final Integer xPosition,
      final Integer yPosition) {
    return new TableTopState(width, height, new RobotState(direction, xPosition, yPosition));
  }

  public TableTopState rotateRobotLeft() {
    return robotState != null ? new TableTopState(width, height, robotState.rotateLeft()) : this;
  }

  public TableTopState rotateRobotRight() {
    return robotState != null ? new TableTopState(width, height, robotState.rotateRight()) : this;
  }

  public TableTopState moveRobot() {
    return robotState != null ? new TableTopState(width, height, robotState.move()) : this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TableTopState that = (TableTopState) o;
    return Objects.equals(width, that.width) &&
        Objects.equals(height, that.height) &&
        Objects.equals(robotState, that.robotState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, robotState);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("TableTopState{");
    sb.append("width=").append(width);
    sb.append(", height=").append(height);
    sb.append(", robotState=").append(robotState);
    sb.append(", invalid=").append(isInvalid());
    sb.append('}');
    return sb.toString();
  }
}
