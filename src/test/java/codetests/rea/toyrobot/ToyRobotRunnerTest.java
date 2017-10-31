package codetests.rea.toyrobot;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Unit Tests for the ToyRobotRunner
 */
public class ToyRobotRunnerTest {

  @Test
  public void testToyRobotRunnerWithSimpleSteps() {
    List<String> input = Arrays.asList(
        "PLACE 2,2,NORTH",
        "MOVE",
        "REPORT"
    );

    List<String> expectedOutput = Arrays.asList(
        "2,3,NORTH");

    verifyRunner(input, expectedOutput);
  }

  @Test
  public void testToyRobotRunnerWithLongerSteps() {
    List<String> input = Arrays.asList(
        "PLACE 1,2,EAST",
        "REPORT",
        "MOVE",
        "MOVE",
        "LEFT",
        "MOVE",
        "REPORT"
    );

    List<String> expectedOutput = Arrays.asList(
        "1,2,EAST",
        "3,3,NORTH");

    verifyRunner(input, expectedOutput);
  }

  private void verifyRunner(List<String> input, List<String> expectedOutput) {
    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

    ByteArrayInputStream in = new ByteArrayInputStream(
        String.join(System.lineSeparator(), input).getBytes());

    new ToyRobotRunner(5, 5, in, out).run();

    String[] output = out.toString().split(System.lineSeparator());

    assertThat(Arrays.asList(output), is(expectedOutput));
  }
}
