package nfo.data;

import java.io.File;

import java.util.List;
import java.util.ArrayList;

import nfo.control.CreationEvent;

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
    new Argument("--no-gui"       , Settings.ARGUMENT_NO_GUI       , 0),
    new Argument("--silent"       , Settings.ARGUMENT_SILENT       , 0),
    new Argument("--help"         , Settings.ARGUMENT_HELP         , 0 , "-h" ),
    new Argument("--verbose"      , Settings.ARGUMENT_VERBOSE      , 0 , "-v" ),
    new Argument("--load-profile" , Settings.ARGUMENT_LOAD_PROFILE , 1 , "-lp"),
    new Argument("--profile"      , Settings.ARGUMENT_PROFILE      , 1 , "-p"),
    new Argument("--content"      , Settings.ARGUMENT_CONTENT      , 1 , "-c"),
    new Argument("--output-log"   , Settings.ARGUMENT_OUTPUT_LOG   , 1),
    new Argument("--output-file"  , Settings.ARGUMENT_OUTPUT_FILE  , 1 , "-o" ),
    new Argument("--file"         , Settings.ARGUMENT_FILE         , 1 , "-f" ),
    new Argument("--list-profiles", Settings.ARGUMENT_LIST_PROFILES, 0 , "--list" , "-ls")
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

    //TODO it could be good to look for certain argument first, to make possible «reverse order»
    //like --profile Film --load-profiles file.data
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
          Output.print("Missing argument, argument: " + arguments[i] + " expected " + optionExpected + " arguments.");
          ret = false;
        }

      }
      else {
        Output.print("Unknown argument used: " + arguments[i]);
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
      case Settings.ARGUMENT_SILENT:
        Settings.silent = true;
        break;
      case Settings.ARGUMENT_HELP:
        Output.print("Help message, will need external class logger");
        break;
      case Settings.ARGUMENT_LOAD_PROFILE:
        File profileDataFile = new File(options[0]);
        if (profileDataFile.exists()) {
          ProfileList.setFile(profileDataFile.getAbsolutePath());
          ProfileList.loadData();
        }
        else
          Output.print("Invalid profile data file path.");
        break;
      case Settings.ARGUMENT_PROFILE:
        int profileIndex = ProfileList.contains(options[0]);
        if (profileIndex != -1) {
          Profile p = ProfileList.getElement(profileIndex);
          Output.printNFO(ProfileCreator.create(p, null));

        } else
          Output.print("Can't find the profile. Are you using the good profile file ? See --list");
        break;
      case Settings.ARGUMENT_OUTPUT_FILE:
        Settings.outputNfo = options[0];
        break;
      case Settings.ARGUMENT_OUTPUT_LOG:
        Settings.output = options[0];
        break;
      case Settings.ARGUMENT_CONTENT:
        String[] lines = RWFile.readFile(options[0]);
        //TODO call ProfileCreator with
        break;
      case Settings.ARGUMENT_FILE:
        if (Settings.createGUI)
          CreationEvent.openSpecifiedFileCreation(options[0]);
        else
          for (String line : RWFile.readNfoFile(options[0]))
            System.out.print(line);
        break;
      case Settings.ARGUMENT_LIST_PROFILES:
        if (ProfileList.getNumberProfile() > 0)
          for(Profile profile : ProfileList.getElements())
            Output.print("-" + profile.getProfileName());
        else
          Output.print("Missing profile data files, see --load-profile and --help for more information");
        break;
      default:
        throw new RuntimeException("The argument exists but no action has been found !"
            + "\nArgument id: " + argumentID);
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
