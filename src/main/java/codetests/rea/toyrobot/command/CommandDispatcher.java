package codetests.rea.toyrobot.command;

import codetests.rea.toyrobot.Direction;
import codetests.rea.toyrobot.state.TableTopState;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of the CommandFactory
 */
public class CommandDispatcher {

  private final Map<String, Command> commands;

  public CommandDispatcher(final ReportingOutput reportingOutput) {
    this.commands = getCommands(reportingOutput);
  }

  public TableTopState apply(String command, TableTopState tableTopState) {
    Objects.requireNonNull(command, "Valid command required");

    // split the command name and arguments (if any), on first whitespace
    String[] commandParts = command.split("\\s", 2);

    String commandName = commandParts[0];
    String arguments = commandParts.length > 1 ? commandParts[1] : null;

    if (commands.containsKey(commandName)) {
      TableTopState newTableTopState = commands.get(commandName)
          .execute(tableTopState, arguments);

      return newTableTopState.isInvalid() ? tableTopState : newTableTopState;
    }

    return tableTopState;
  }

  private Map<String, Command> getCommands(final ReportingOutput reportingOutput) {
    return
        Stream.of(
            new SimpleEntry<String, Command>(
                "PLACE",
                (tableTopState, arguments) -> {
                  Objects.requireNonNull(arguments, "Require X,Y,F format for PLACE command");

                  Matcher argumentMatcher = Pattern.compile("^(\\d+),(\\d+),(\\w+)$")
                      .matcher(arguments);

                  if (argumentMatcher.matches()) {
                    Integer x = Integer.valueOf(argumentMatcher.group(1));
                    Integer y = Integer.valueOf(argumentMatcher.group(2));

                    try {
                      Direction direction = Direction.valueOf(argumentMatcher.group(3));

                      return tableTopState.place(direction, x, y);
                    } catch (IllegalArgumentException e) {
                      // ignore unparsable direction enum
                    }
                  }

                  return tableTopState;
                }
            ),
            new SimpleEntry<String, Command>(
                "LEFT", (tableTopState, arguments) -> tableTopState.rotateRobotLeft()
            ),
            new SimpleEntry<String, Command>(
                "RIGHT", (tableTopState, arguments) -> tableTopState.rotateRobotRight()
            ),
            new SimpleEntry<String, Command>(
                "MOVE", (tableTopState, arguments) -> tableTopState.moveRobot()
            ),
            new SimpleEntry<String, Command>(
                "REPORT", (tableTopState, arguments) -> {
              tableTopState.getRobotState().ifPresent((robot) -> reportingOutput.report(
                  robot.getxPosition() + "," + robot.getyPosition() + ","
                      + robot.getDirection())
              );

              return tableTopState;
            })
        ).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue()));
  }
}