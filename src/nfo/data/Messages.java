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
    new String("arguments.")
  };
  private static String GLOBAL_HELP = "nfo-modifier. Version: " + Settings.version + " Please report bug!";
  private static String[] PROFILE_CREATED = {
    new String("Pnofile: "),
    new String("successfully created.")
  };
  private static String PROFILE_NAME_TAKEN   = "Profile name already in use, please choose a different one.";
  private static String PROFILE_DELETED      = "Profile successfully deleted !";
  private static String MISSING_PROFILE_FILE = "Missing profile data files, see --load-profile and --help for more information";
  private static String CANT_FIND_PROFILE    = "Can't find the profile. Are you using the good profile file ? See --list";
  private static String NO_ACTION_FOUND      = "The argument exists but no action has been found! \nArgument id: ";




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
   * Get the message displayed when
   */
  public static String getMissingProfileFile() {
    return MISSING_PROFILE_FILE;
  }

  public static String getCantFindProfile() {
    return CANT_FIND_PROFILE;
  }

  public static String getProfileCreated(String profile) {
    return PROFILE_CREATED[0] + profile + PROFILE_CREATED[1];
  }

  public static String getNoActionFound(int argumentID) {
    return NO_ACTION_FOUND + argumentID;
  }

  public static String getGlobalHelp() {
    return GLOBAL_HELP;
  }
}
