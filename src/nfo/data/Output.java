package nfo.data;

import java.io.File;

import java.util.ArrayList;

/**
 * Manage the messages printed by the program, can print on the standard
 * output or in a file. All the message are stored THEN printed when all
 * the argument of the command line interface has been parsed.
 *
 * @author Rio Alexandre
 * @version 1.0
 */
public abstract class Output {

  /** List of all the string to display. */
  private static ArrayList<String> bufferedLines = new ArrayList<String>();
  private static ArrayList<String> bufferedNFO   = new ArrayList<String>();

  /**
   * Add message to the list of messages to print.
   *
   * @param line Message to print.
   */
  public static void print(String line) {
    bufferedLines.add(line);
  }

  /**
   * Add messages to the list of messages to print.
   *
   * @param lines Array of messages to print.
   */
  public static void print(String[] lines) {
    for (String line : lines)
      bufferedLines.add(line);
  }

  /**
   * Add a line of NFO the list of lines of NFO to print.
   *
   * @param line Line of a NFO file to print.
   */
  public static void printNFO(String line) {
    bufferedNFO.add(line);
  }

  /**
   * Add lines of NFO to the list of lines of NFO to print.
   *
   * @param lines Array of lines of a NFO file to print.
   */
  public static void printNFO(String[] lines) {
    for (String line : lines)
      bufferedNFO.add(line);
  }

  /**
   * Print the messages and the nfo on the right output, standard or in a file.
   */
  public static void process() {
    if (bufferedLines.size() > 0) {
      if (Settings.output == null)
        for (String line : bufferedLines)
          System.out.println(line);
      else
        RWFile.writeFile(bufferedLines, Settings.output);
    }

    if (bufferedNFO.size() > 0) {
      if (Settings.outputNfo == null )
        for(String line : bufferedNFO)
          System.out.println(line);
      else
        RWFile.writeNfoFile(bufferedNFO, Settings.outputNfo);
    }
  }

}
