package net.jadefisher.codetests.toyrobot.state;

import net.jadefisher.codetests.toyrobot.Direction;
import java.util.Objects;
import java.util.Optional;

/**
 * Holds the size of the tabletop, optionally the robot (if placed), behavior to place the robot,
 * delegation behavior to move and rotate the robot, and logic as to if the current state
 * (tabletop and robot) is valid.
 *
 * This class is immutable, so all state is provided in the constructor.
 */
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

  /**
   * The RobotState may or may not have been placed, and therefore this is optional.
   *
   * @return Optional of RobotState.
   */
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
