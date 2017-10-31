package codetests.rea.toyrobot;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.Test;

/**
 * Unit Tests for the ToyRobotRunner
 */
public class ToyRobotRunnerTest {

  @Test
  public void testToyRobotRunnerWithSimpleSteps() {
    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

    ByteArrayInputStream in = new ByteArrayInputStream(
        "PLACE 2,2,NORTH\nMOVE\nREPORT\n".getBytes());

    new ToyRobotRunner(5, 5, in, out).run();

    assertThat(out.toString(), is("2,3,NORTH\n"));
  }
}
