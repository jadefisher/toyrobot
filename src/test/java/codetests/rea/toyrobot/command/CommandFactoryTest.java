package codetests.rea.toyrobot.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import codetests.rea.toyrobot.state.TableTopState;
import org.junit.Test;

/**
 * Created by jfisher on 10/31/17.
 */
public class CommandFactoryTest {

  @Test
  public void testPlaceCommandAddsRobotToTableTop() {
    TableTopState tableTopState = new TableTopState(5, 5);
    assertThat(tableTopState.isInvalid(), is(true));

    tableTopState = CommandFactory.getInstance().apply("PLACE 2,2,NORTH", tableTopState);

    assertThat(tableTopState.isInvalid(), is(false));
  }
}
