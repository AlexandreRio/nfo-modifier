package nfo.data;

import org.junit.*;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

  private ArgumentParser parser;

  @Before
  public void setup() {
    parser = ArgumentParser.getInstance();
  }

  @Test
  public void test1() {
    assertEquals(4, 4);
  }

  @Test
  public void testOneArgumentCommand() {
    assertTrue(true);
  }

}
