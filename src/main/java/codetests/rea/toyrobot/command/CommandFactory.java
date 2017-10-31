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
public class CommandFactory {

  private static CommandFactory instance = null;
  private final Map<String, Command> commands;

  private CommandFactory(final Map<String, Command> commands) {
    this.commands = commands;
  }

  public static synchronized CommandFactory getInstance() {
    if (instance == null) {
      instance = new CommandFactory(
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
                      Direction direction = Direction.valueOf(argumentMatcher.group(3));

                      return tableTopState.place(direction, x, y);
                    }

                    return tableTopState;
                  })
          ).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue()))
      );
    }

    return instance;
  }

  public TableTopState apply(String command, TableTopState tableTopState) {
    Objects.requireNonNull(command, "Valid command required");

    Matcher commandRegexMatcher = Pattern.compile("^(\\w+)\\s(.+)*$").matcher(command);

    if (commandRegexMatcher.matches()) {
      String commandName = commandRegexMatcher.group(1);
      String arguments = commandRegexMatcher.group(2);

      if (commands.containsKey(commandName)) {
        TableTopState newTableTopState = commands.get(commandName)
            .execute(tableTopState, arguments);

        return newTableTopState.isInvalid() ? tableTopState : newTableTopState;
      }
    }

    return tableTopState;
  }
}
