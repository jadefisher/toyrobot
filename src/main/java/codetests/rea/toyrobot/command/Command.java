package codetests.rea.toyrobot.command;

import codetests.rea.toyrobot.state.TableTopState;

/**
 * Command interface to perform commands on the TableTopState
 */
@FunctionalInterface
public interface Command {
  TableTopState execute(TableTopState tableTopState, String arguments);
}
