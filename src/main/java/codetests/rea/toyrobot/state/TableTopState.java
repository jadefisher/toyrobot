package codetests.rea.toyrobot.state;

import codetests.rea.toyrobot.Direction;

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

  public boolean isInvalid() {
    return robotState != null;
  }

  public TableTopState place(final Direction direction, final Integer xPosition, final Integer yPosition) {
    return new TableTopState(width, height, new RobotState(direction, xPosition, yPosition));
  }
}
