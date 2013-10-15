package nfo.data;

import org.junit.*;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

  private String[] args;


  @Test
  public void testOneArgumentCommand() {
    ArgumentParser parser = ArgumentParser.getInstance();
    boolean isValidArgument;
    args    = new String[1];

    args[0] = "--no-gui";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);

    args[0] = "--verbose";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);

    args[0] = "-v";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);

    args[0] = "--load-data";
    isValidArgument = parser.setArguments(args);
    assertFalse(isValidArgument);

    args[0] = "--output-file";
    isValidArgument = parser.setArguments(args);
    assertFalse(isValidArgument);

    args[0] = "foo";
    isValidArgument = parser.setArguments(args);
    assertFalse(isValidArgument);
  }

  @Test
  public void testTwoArgumentCommand() {
    ArgumentParser parser = ArgumentParser.getInstance();
    boolean isValidArgument;
    args    = new String[2];

    args[0] = "--no-gui";
    args[1] = "--verbose";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);

    args[0] = "--load-data";
    args[1] = "~/file.data";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);

    args[0] = "--output-file";
    args[1] = "~/out.nfo";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);

    args[0] = "--no-gui";
    args[1] = "--load-data";
    isValidArgument = parser.setArguments(args);
    assertFalse(isValidArgument);

    args[0] = "--no-gui";
    args[1] = "--output-file";
    isValidArgument = parser.setArguments(args);
    assertFalse(isValidArgument);

    args[0] = "--no-gui";
    args[1] = "foo";
    isValidArgument = parser.setArguments(args);
    assertFalse(isValidArgument);
  }

  @Test
  public void testComplexCommand() {
    ArgumentParser parser = ArgumentParser.getInstance();
    boolean isValidArgument;
    args    = new String[3];

    args[0] = "--no-gui";
    args[1] = "--load-data";
    args[2] = "~/file.data";
    isValidArgument = parser.setArguments(args);
    assertTrue(isValidArgument);
  }

  @Test
  public void testGetArgument() {
    ArgumentParser parser = ArgumentParser.getInstance();
    Argument valueReturned;
    boolean valueReturnedIsNull;
    String arg;

    arg = "--no-gui";
    valueReturned = parser.getArgument(arg);
    assertTrue(valueReturned != null);

    arg = "--foo";
    valueReturned = parser.getArgument(arg);
    assertTrue(valueReturned == null);

    arg = "--verbose";
    if (parser.getArgument("-v") == parser.getArgument(arg))
      valueReturnedIsNull = true;
    else
      valueReturnedIsNull = false;
    assertTrue(valueReturnedIsNull);

    arg = "--load-data";
    if (parser.getArgument("-ld") == parser.getArgument(arg))
      valueReturnedIsNull = true;
    else
      valueReturnedIsNull = false;
    assertTrue(valueReturnedIsNull);

    arg = "--output-file";
    if (parser.getArgument("-o") == parser.getArgument(arg))
      valueReturnedIsNull = true;
    else
      valueReturnedIsNull = false;
    assertTrue(valueReturnedIsNull);

    arg = "--verbose";
    if (parser.getArgument("-v") == parser.getArgument(arg))
      valueReturnedIsNull = true;
    else
      valueReturnedIsNull = false;
    assertTrue(valueReturnedIsNull);
  }
}
