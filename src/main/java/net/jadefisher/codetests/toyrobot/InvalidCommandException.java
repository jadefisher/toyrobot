package net.jadefisher.codetests.toyrobot;

/**
 * Runtime Exception to indicate a command was executed which can't be interpreted
 */
public class InvalidCommandException extends RuntimeException {

  public InvalidCommandException(String message) {
    super(message);
  }
}
