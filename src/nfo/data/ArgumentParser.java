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
    new Argument("--no-gui"         , Settings.ARGUMENT_NO_GUI         , 0 , "-ng"),
    new Argument("--silent"         , Settings.ARGUMENT_SILENT         , 0),
    new Argument("--help"           , Settings.ARGUMENT_HELP           , 0 , "-h" ),
    new Argument("--verbose"        , Settings.ARGUMENT_VERBOSE        , 0 , "-v" ),
    new Argument("--create-data"    , Settings.ARGUMENT_CREATE_DATA    , 1 , "-cd"),
    new Argument("--load-data"      , Settings.ARGUMENT_LOAD_DATA      , 1 , "-ld"),
    new Argument("--create-profile" , Settings.ARGUMENT_CREATE_PROFILE , 5 , "-cp"),
    new Argument("--delete-profile" , Settings.ARGUMENT_DELETE_PROFILE , 1),
    new Argument("--profile"        , Settings.ARGUMENT_PROFILE        , 1 , "-p") ,
    new Argument("--content"        , Settings.ARGUMENT_CONTENT        , 1 , "-c") ,
    new Argument("--output-log"     , Settings.ARGUMENT_OUTPUT_LOG     , 1),
    new Argument("--output-file"    , Settings.ARGUMENT_OUTPUT_FILE    , 1 , "-o" ),
    new Argument("--file"           , Settings.ARGUMENT_FILE           , 1 , "-f" ),
    new Argument("--list-profiles"  , Settings.ARGUMENT_LIST_PROFILES  , 0 , "--list" , "-ls")
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
          Output.print(Messages.getMissingArgument( arguments[i], optionExpected));
          ret = false;
        }

      }
      else {
        Output.print(Messages.getUnknownArgumentUsed(arguments[i]));
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
        Output.print(Messages.getGlobalHelp());
        break;
      case Settings.ARGUMENT_CREATE_DATA:
        ProfileList.setFile(options[0]);
        ProfileList.writeData();
        Output.print(Messages.getCreateData(options[0]));
        break;
      case Settings.ARGUMENT_LOAD_DATA:
        Settings.usingDataFile = true;
        File profileDataFile = new File(options[0]);
        if (profileDataFile.exists()) {
          ProfileList.setFile(profileDataFile.getAbsolutePath());
          ProfileList.loadData();
        }
        else
          Output.print(Messages.getInvalidProfileDataPath());
        break;
      case Settings.ARGUMENT_CREATE_PROFILE:
        if(Settings.usingDataFile ) {
          if (ProfileList.contains(options[0]) == -1) {
            String header = RWFile.readFileInString(options[1]);
            String body   = RWFile.readFileInString(options[2]);
            String border = RWFile.readFileInString(options[3]);
            String footer = RWFile.readFileInString(options[4]);

            Profile newProfile = new Profile(options[0], header, body, border, footer);
            ProfileList.add(newProfile);
            ProfileList.writeData();

            Output.print(Messages.getProfileCreated(options[0]));
          }
          else
            Output.print(Messages.getProfileNameTaken());
        }
        else
          Output.print(Messages.getMissingProfileFile());
        break;
      case Settings.ARGUMENT_DELETE_PROFILE:
        if(ProfileList.getNumberProfile() >0 ) {
          int index = ProfileList.contains(options[0]);
          if (index != -1) {
            Profile profileToDelete = ProfileList.getElements().get(index);
            if (ProfileList.removeItem(profileToDelete)) {
              Output.print(Messages.getProfileDeleted());
              ProfileList.writeData();
            }
          }
          else
            Output.print(Messages.getProfileNameNotFound());
        }
        else
          Output.print(Messages.getMissingProfileFile());
        break;
      case Settings.ARGUMENT_PROFILE:
        int profileIndex = ProfileList.contains(options[0]);
        if (profileIndex != -1)
          Settings.profile = ProfileList.getElement(profileIndex);
        else
          Output.print(Messages.getCantFindProfile());
        break;
      case Settings.ARGUMENT_OUTPUT_FILE:
        Settings.outputNfo = options[0];
        break;
      case Settings.ARGUMENT_OUTPUT_LOG:
        Settings.output = options[0];
        break;
      case Settings.ARGUMENT_CONTENT:
        Settings.content = RWFile.readFile(options[0]);
        break;
      case Settings.ARGUMENT_FILE:
        if (Settings.createGUI)
          CreationEvent.openSpecifiedFileCreation(options[0]);
        else
          for (String line : RWFile.readNfoFile(options[0]))
            Output.printNFO(line);
        break;
      case Settings.ARGUMENT_LIST_PROFILES:
        if (ProfileList.getNumberProfile() > 0)
          for(Profile profile : ProfileList.getElements())
            Output.print("-" + profile.getProfileName());
        else
          Output.print(Messages.getMissingProfileFile());
        break;
      default:
        throw new RuntimeException(Messages.getNoActionFound(argumentID));
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
