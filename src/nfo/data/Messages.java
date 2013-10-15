package nfo.data;

/**
 * Handle the messages displayed in the CLI, kind of dirty way, will think
 * of a better solution soon.
 *
 * @author Rio Alexandre
 * @version 1.0
 *
 */
public abstract class Messages {

  private static String UNKNOWN_ARGUMENT_USED     = "Unknown argument used: ";
  private static String INVALID_PROFILE_DATA_PATH = "Invalid profile data file path.";
  private static String PROFILE_NAME_NOT_FOUND    = "Profile name not found.";
  private static String[] ARGUMENT_MISSING        = {
    new String("Missing argument, argument: "),
    new String(" expected "),
    new String(" arguments.")
  };
  private static String[] PROFILE_CREATED = {
    new String("Pnofile: "),
    new String("successfully created.")
  };
  private static String PROFILE_NAME_TAKEN   = "Profile name already in use, please choose a different one.";
  private static String PROFILE_DELETED      = "Profile successfully deleted !";
  private static String DATA_PROFILE_CREATED = "Profile successfully created : ";
  private static String MISSING_PROFILE_FILE = "Missing profile data files, see --load-data and --help for more information";
  private static String CANT_FIND_PROFILE    = "Can't find the profile. Are you using the good profile file ? See --list";
  private static String NO_ACTION_FOUND      = "The argument exists but no action has been found! \nArgument id: ";
  private static String GLOBAL_HELP =
    "nfo-modifier " + Settings.version
    + "\nProject website: https://github.com/AlexandreRio/nfo-modifier Please report bug!\n"

    + "\nUsage:"
    + "\n\tnfo-modifier --no-gui [options]"

    + "\n\nTypical scenario:"
    + "\n\tnfo-modifier --create-data profile.data"
    + "\n\tnfo-modifier --load-data profile.data --create-profile MyProfile header.txt border.txt body.txt footer.txt"
    + "\n\tnfo-modifier --load-data profile.data -ls"
    + "\n\tnfo-modifier -ld profile.data --profile MyProfile --content content.txt --output-file myCoolNFO.nfo"

    + "\nOptions:"
    + "\n  --file,-f [file]\t\t\t"                   + "Open the specified [file] and display it."
    + "\n  --create-data, -cd [file]\t\t"            + "Create a new empty profile data file named [file]."
    + "\n  --load-data, -ld [file]\t\t"              + "Load the profile data file named [file]."
    + "\n  --profile,-p [option]\t\t\t"              + "Choose the profile to use."
    + "\n  --create-profile,-cp [option1..5] \t"     + "Create a new profile, the first option is the name, followed by 4 files in the following order: header, body, border and footer. Need to use --load-data before."
    + "\n  --delete-profile [option]\t\t"            + "Delete a profile matching [option] name. Need to use --load-data before."
    + "\n  --list-profiles,-ls\t\t"                  + "List all the profile names store. Need to use --load-data before."
    + "\n  --output-log [file]\t\t"                  + "Specify a [file] to store displayed messages, if not specified the default output is used. For a file to store cp437 data see --output-file"
    + "\n  --output-file,-o [file]\t\t"              + "Specify a [file] to store cp437 displayed data, if not specified the default output is used with default (readable) encoding."
    + "\n  --no-gui\t\t\t\t"                         + "Do not create the Graphical User Interface."
    + "\n  --silent\t\t\t\t"                         + "Turn off messages AND nfo output, you may need --output-log and --output-file."
    ;

  /**
   * Get the message displayed when an invalid argument is given to the parser.
   *
   * @param argument Argument given to the parser, for example --foo is not a valid argument.
   */
  public static String getUnknownArgumentUsed(String argument) {
    return UNKNOWN_ARGUMENT_USED + argument;
  }

  /**
   * Get the message displayed when the given path does not match an existing file.
   */
  public static String getInvalidProfileDataPath() {
    return INVALID_PROFILE_DATA_PATH;
  }

  /**
   * Get the message displayed when the given Profile name does not match a Profile in the file of profiles.
   */
  public static String getProfileNameNotFound() {
    return PROFILE_NAME_NOT_FOUND;
  }

  /**
   * Get the message displayed when a profile is successfully deleted from the ProfileList.
   */
  public static String getProfileDeleted() {
    return PROFILE_DELETED;
  }

  /**
   * Get the message displayed when an argument is used with too few or too
   * many options, like --load-profiles file1.data file2.data instead of
   * --load-profiles file.data
   *
   * @param argument Name of the Argument causing the error.
   * @param expectedNumber Number of options expected for the argument.
   */
  public static String getMissingArgument(String argument, int expectedNumber) {
    String ret = ARGUMENT_MISSING[0];
    ret += argument;
    ret += ARGUMENT_MISSING[1];
    ret += expectedNumber + ARGUMENT_MISSING[2];
    return ret;
  }

  /**
   * Get the message displayed when the profile creation is called with
   * an existing name causing a conflict.
   */
  public static String getProfileNameTaken() {
    return PROFILE_NAME_TAKEN;
  }

  /**
   * Get the message displayed when a profile is requested but profile file
   * has been loaded.
   */
  public static String getMissingProfileFile() {
    return MISSING_PROFILE_FILE;
  }

  /**
   * Get the message displayed when a profile name doesn't match any Profile
   * in the loaded profile data file.
   */
  public static String getCantFindProfile() {
    return CANT_FIND_PROFILE;
  }

  /**
   * Get the message displayed when a new Profile is successfully created.
   *
   * @param profile Name of the profile created.
   */
  public static String getProfileCreated(String profile) {
    return PROFILE_CREATED[0] + profile + PROFILE_CREATED[1];
  }

  /**
   * Get the message displayed when no action fit the given argumentID.
   *
   * @param argumentID ID of an argument with no action.
   */
  public static String getNoActionFound(int argumentID) {
    return NO_ACTION_FOUND + argumentID;
  }

  /**
   * Get the message displayed when a new profile data file is created.
   *
   * @param fileName Path of the created file.
   */
  public static String getCreateData(String fileName) {
    return DATA_PROFILE_CREATED + fileName + " !";
  }

  /**
   * Get the generic help message.
   */
  public static String getGlobalHelp() {
    return GLOBAL_HELP;
  }
}
