package codetests.rea.toyrobot.command;

/**
 * Handler for reporting output
 */
@FunctionalInterface
public interface ReportingOutput {

  void report(String reportingMessage);
}
