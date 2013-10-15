package nfo.data;

import org.junit.*;
import junit.framework.TestCase;

public class ArgumentTest extends TestCase {

  private Argument noGui;
  private Argument verbose;
  private Argument loadProfile;
  private Argument outputFile;

  @Test
  public void testIsAValidAlias() {
    this.noGui       = new Argument("--no-gui"       , Settings.ARGUMENT_NO_GUI       , 0);
    this.verbose     = new Argument("--verbose"      , Settings.ARGUMENT_VERBOSE      , 0  , "-v");
    this.loadProfile = new Argument("--load-data" , Settings.ARGUMENT_LOAD_DATA , 1  , "-lp");
    this.outputFile  = new Argument("--output-file"  , Settings.ARGUMENT_OUTPUT_FILE  , 1  , "-o");
    boolean isValidAlias;

    isValidAlias = noGui.isAValidAlias("--no-gui");
    assertTrue(isValidAlias);
    isValidAlias = noGui.isAValidAlias("--foo");
    assertFalse(isValidAlias);

    isValidAlias = verbose.isAValidAlias("--verbose");
    assertTrue(isValidAlias);
    isValidAlias = verbose.isAValidAlias("-v");
    assertTrue(isValidAlias);
    isValidAlias = verbose.isAValidAlias("--foo");
    assertFalse(isValidAlias);

    isValidAlias = loadProfile.isAValidAlias("--load-data");
    assertTrue(isValidAlias);
    isValidAlias = loadProfile.isAValidAlias("-lp");
    assertTrue(isValidAlias);
    isValidAlias = loadProfile.isAValidAlias("--foo");
    assertFalse(isValidAlias);

    isValidAlias = outputFile.isAValidAlias("--output-file");
    assertTrue(isValidAlias);
    isValidAlias = outputFile.isAValidAlias("-o");
    assertTrue(isValidAlias);
    isValidAlias = outputFile.isAValidAlias("--foo");
    assertFalse(isValidAlias);
  }

  @Test
  public void testGetNumberOption() {
    this.noGui       = new Argument("--no-gui"       , Settings.ARGUMENT_NO_GUI       , 0);
    this.verbose     = new Argument("--verbose"      , Settings.ARGUMENT_VERBOSE      , 0  , "-v");
    this.loadProfile = new Argument("--load-data" , Settings.ARGUMENT_LOAD_DATA , 1  , "-lp");
    this.outputFile  = new Argument("--output-file"  , Settings.ARGUMENT_OUTPUT_FILE  , 1  , "-o");
    int numberOption;

    numberOption = noGui.getNumberOption();
    assertEquals(numberOption, 0);

    numberOption = verbose.getNumberOption();
    assertEquals(numberOption, 0);

    numberOption = loadProfile.getNumberOption();
    assertEquals(numberOption, 1);

    numberOption = outputFile.getNumberOption();
    assertEquals(numberOption, 1);
  }

  @Test
  public void testGetID() {
    this.noGui       = new Argument("--no-gui"       , Settings.ARGUMENT_NO_GUI       , 0);
    this.verbose     = new Argument("--verbose"      , Settings.ARGUMENT_VERBOSE      , 0  , "-v");
    this.loadProfile = new Argument("--load-data" , Settings.ARGUMENT_LOAD_DATA , 1  , "-lp");
    this.outputFile  = new Argument("--output-file"  , Settings.ARGUMENT_OUTPUT_FILE  , 1  , "-o");
    int id;

    id = noGui.getID();
    assertEquals(Settings.ARGUMENT_NO_GUI, id);

    id = verbose.getID();
    assertEquals(Settings.ARGUMENT_VERBOSE, id);

    id = loadProfile.getID();
    assertEquals(Settings.ARGUMENT_LOAD_DATA, id);

    id = outputFile.getID();
    assertEquals(Settings.ARGUMENT_OUTPUT_FILE, id);
  }
}
