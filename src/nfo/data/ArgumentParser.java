package nfo.data;

import java.util.List;
import java.util.ArrayList;

/**
 * Parse the arguments given to the program.
 *
 * @author Rio Alexandre
 * @version 1.0
 */
public class ArgumentParser {

  /** Self reference. */
  private static ArgumentParser selfRef;

  /**
   * List of all the available {@link nfo.data.Argument arguments}.
   */
  private final Argument[] arguments = {
    new Argument("--no-gui"       , Settings.ARGUMENT_NO_GUI       , 0) ,
    new Argument("--verbose"      , Settings.ARGUMENT_VERBOSE      , 0  , "-v")  ,
    new Argument("--load-profile" , Settings.ARGUMENT_LOAD_PROFILE , 1  , "-lp") ,
    new Argument("--output-file"  , Settings.ARGUMENT_OUTPUT_FILE  , 1  , "-o")
  };

  /**
   * Get the instance of the ArgumentParser, if it doesn't exist a new instance is
   * created.
   *
   * @return The instance of the ArgumentParser.
   */
  public static ArgumentParser getInstance() {
    if (selfRef == null)
      selfRef = new ArgumentParser();
    return selfRef;
  }

  /**
   * Create a new argument parser with no argument to parse.
   */
  private ArgumentParser() {
  }

  /**
   *  Set the list of arguments.
   *
   *  @param arguments List of all the arguments to parse.
   *  @return True if everything went well, false in any other case.
   */
  public boolean setArguments(String[] arguments) {
    boolean ret = true;
    int optionExpected;
    Argument currentArgument;
    String[] options;

    for (int i=0; i<arguments.length; i++) {
      currentArgument = this.getArgument(arguments[i]);

      // Check if the arguement is valid
      if (currentArgument != null) {
        optionExpected = currentArgument.getNumberOption();

        // Check if the arguement is called with the correct number of option
        if (arguments.length - (i+1) >= optionExpected) {

          options = new String[optionExpected];
          for (int j=0; j<optionExpected; j++)
            options[j] = arguments[i+j+1];
          this.process(currentArgument.getID(), options);
          i += optionExpected;
        }
        else {
          System.err.println("Missing argument, argument: " + arguments[i] + " expected " + optionExpected + " arguments.");
          ret = false;
        }

      }
      else {
        System.err.println("Unknown argument used: " + arguments[i]);
        ret = false;
      }
    }
    return ret;
  }

  /**
   * Process the action corresponding to the argument ID.
   *
   * @param argumentID Unique ID of an argument.
   * @param options List of options for the argument ID, no verification are
   * done here, see {@link nfo.data.ArgumentParser#setArguments setArguements} for the number of option expected.
   */
  private void process(int argumentID, String[] options) {
    switch (argumentID) {
      case Settings.ARGUMENT_NO_GUI:
        Settings.createGUI = false;
        break;
      case Settings.ARGUMENT_VERBOSE:
        Settings.verbose = true;
        break;
      case Settings.ARGUMENT_LOAD_PROFILE:
        String dataFile = options[0];
        System.out.println("Load data file: " + dataFile);
        break;
      case Settings.ARGUMENT_OUTPUT_FILE:
        String outFile = options[0];
        System.out.println("Output file: " + outFile);
        break;
      default:
        System.err.println("Unknown id!");
        break;
    }
  }

  /**
   * Get the Argument instance of an argument based on an alias.
   *
   * @param argumentName Argument name to look for.
   * @return The instance of the argument if the alias exists, null in other
   * case.
   */
  protected Argument getArgument(String argumentName) {
    Argument ret = null;
    for(int i=0; i<arguments.length; i++) {
      if (arguments[i].isAValidAlias(argumentName)){
        ret = arguments[i];
        break;
      }
    }
    return ret;
  }
}
