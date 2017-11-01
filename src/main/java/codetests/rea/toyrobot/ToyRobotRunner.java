package codetests.rea.toyrobot;

import codetests.rea.toyrobot.command.CommandDispatcher;
import codetests.rea.toyrobot.state.TableTopState;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ToyRobotRunner {

  private final Integer width;
  private final Integer height;
  private final BufferedReader commandStream;
  private final PrintWriter reportingStream;

  public ToyRobotRunner(
      final Integer width,
      final Integer height,
      InputStream commandStream,
      OutputStream reportingStream) {
    this.width = width;
    this.height = height;
    this.commandStream = new BufferedReader(new InputStreamReader(commandStream));
    this.reportingStream = new PrintWriter(reportingStream, true);
  }

  public static void main(String... args) {
    new ToyRobotRunner(5, 5, System.in, System.out).run();
  }

  public void run() {
    TableTopState tableTopState = new TableTopState(width, height);
    CommandDispatcher commandDispatcher = new CommandDispatcher(reportingStream::println);

    String command;

    try {
      while ((command = commandStream.readLine()) != null) {
        try {
          tableTopState = commandDispatcher.apply(command, tableTopState);
        } catch (InvalidCommandException e) {
          System.err.println(e.getMessage());
        }
      }
    } catch (IOException e) {
      System.err.println("Exception reading input " + e.getCause());
    }
  }
}
