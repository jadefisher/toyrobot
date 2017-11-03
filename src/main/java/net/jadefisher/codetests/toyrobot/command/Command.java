package net.jadefisher.codetests.toyrobot.command;

import net.jadefisher.codetests.toyrobot.state.TableTopState;

/**
 * Command interface to perform commands on the TableTopState
 */
@FunctionalInterface
public interface Command {
  TableTopState execute(TableTopState tableTopState, String arguments);
}
