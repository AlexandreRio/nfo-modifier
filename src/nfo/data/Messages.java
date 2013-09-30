package nfo.data;

/**
 * Handle the messages displayed in the CLI, kind of dirty way, will think
 * of a better solution soon.
 *
 * @author Rio Alexandre
 * @version 1.0
 *
 */
public class Messages {

  private static String UNKNOWN_ARGUMENT_USED     = "Unknown argument used: ";
  private static String INVALID_PROFILE_DATA_PATH = "Invalid profile data file path.";
  private static String PROFILE_NAME_NOT_FOUND    = "Profile name not found.";

  public static String getUnknownArgumentUsed(String argument) {
    return UNKNOWN_ARGUMENT_USED + argument;
  }

  public static String getInvalidProfileDataPath() {
    return INVALID_PROFILE_DATA_PATH;
  }

  public static String getProfileNameNotFound() {
    return PROFILE_NAME_NOT_FOUND;
  }
}
