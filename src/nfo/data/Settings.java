package nfo.data;

import java.net.URI;

/**
 * Enumeration of various settings, the {@link nfo.data.ArgumentParser ArgumentParser}
 * may change these default values, depending on the command line interface arguments.
 *
 * @author Rio Alexandre
 * @version 1.0
 */
public abstract class Settings {

  /** If set to false no Graphical User Interface will be created. */
  private static boolean createGUI = true;
  /** URI of the file to open. */
  private URI fileToOpen = null;

  public static final int ARGUMENT_NO_GUI       = 0x0000;
  public static final int ARGUMENT_VERBOSE      = 0x0001;
  public static final int ARGUMENT_LOAD_PROFILE = 0x0002;
  public static final int ARGUMENT_OUTPUT_FILE  = 0x0003;
}
