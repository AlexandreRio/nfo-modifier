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
}
