package codetests.rea.toyrobot.command;

import codetests.rea.toyrobot.state.TableTopState;
import java.util.Map;

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
      instance = new CommandFactory(null);
    }

    return instance;
  }

  public TableTopState apply(String command, TableTopState tableTopState) {
    return tableTopState;
  }
}
