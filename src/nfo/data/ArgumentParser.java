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
    new Argument("--no-gui", 0),
    new Argument("--verbose", 0),
    new Argument("--load-profile", 1, "-lp"),
    new Argument("--output-file", 1, "-o")
  };

  /** List of the argument to parse. */
  private List<String> argumentList;

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
    this.argumentList = new ArrayList<String>();
  }

  /**
   *  Set the list of arguments.
   *
   *  @param arguments List of all the arguments to parse.
   */
  public void setArguments(String[] arguments) {
    int optionExpected;
    Argument currentArgument;

    for (int i=0; i<arguments.length; i++) {
      currentArgument = this.getArgument(arguments[i]);
      if (currentArgument != null) {
        optionExpected = currentArgument.getNumberOption();
        if (arguments.length - (i+1) < optionExpected)
          System.err.println("Missing argument, argument: " + arguments[i] + " expected " + optionExpected + " arguments.");
        else {
          for (int j=0; j<optionExpected; j++) {
            System.out.println("Option: " + arguments[i+j+1]);
          }
          i += optionExpected;
        }

      }
      else
        System.err.println("Unknown argument used: " + arguments[i]);
    }
  }

  /**
   * Check wether the argument parser got argument to parse.
   *
   * @return True if the parser doesn't have argument to parse.
   */
  public boolean gotArgument() {
    return this.argumentList.isEmpty();
  }

  /**
   * Get the Argument instance of an argument based on an alias.
   *
   * @return The instance of the argument if the alias exists, null in other
   * case.
   */
  private Argument getArgument(String argumentName) {
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
