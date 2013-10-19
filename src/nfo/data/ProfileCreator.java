package nfo.data;

import java.util.ArrayList;

/**
 * Profile manipulator, creates nfo from a template ( profile ) and a content
 *
 * @author Rio Alexandre
 * @version 1.0
 */
public abstract class ProfileCreator {

  /** Margin between the border and the first character of the body. */
  private static final int BORDER_CONTENT_MARGIN = 2;

  /**
   * Create a nfo from a profile, the content is still in the profile.
   *
   * @param profile Profile used as a template
   * @param content Array of String to use as content of the nfo, if null the
   * attribute of the {@link nfo.data.Profile Profile} is used.
   * @return String containing the whole nfo
   */
  public static String create(Profile profile, String[] content) {
    String[] headerRow  = profile.getHeader().split("\n");
    String[] bodyRow;
    int totalWidth  = 0;
    int bodyWidth   = 0;
    int borderWidth = 0;

    if (content == null)
      bodyRow = profile.getBody().split("\n");
    else
      bodyRow = content;

    String borderInOneLine = profile.getBorder();
    // Don't lose empty line when we split
    if (borderInOneLine.contains("\n\n"))
      borderInOneLine = borderInOneLine.replaceAll("\n\n", "\n \n \n");
    String[] borderRow = borderInOneLine.split("\n");

    String row;
    String border;
    String total = "";

    total += profile.getHeader();
    total += "\n";

    totalWidth  = determineLongestLine(headerRow);
    borderWidth = determineLongestLine(borderRow);

    // TODO adapt if the border is only on one side
    // it should be added as an attribute of the Profile object
    bodyWidth = totalWidth - 2*borderWidth - 2*BORDER_CONTENT_MARGIN;

    bodyRow = trimLongLine(bodyRow, bodyWidth);
    int endingBlankLine = borderRow.length - (bodyRow.length%borderRow.length);
    bodyRow = appendLines(bodyRow, endingBlankLine);

    for (int i=0; i<bodyRow.length; i++) {
      border = borderRow[i%borderRow.length];

      row    = border;
      row    = appendSpaces(row, borderWidth - border.length() + BORDER_CONTENT_MARGIN);
      row   += bodyRow[i];
      row    = appendSpaces(row, totalWidth - borderWidth - row.length());
      row   += border;
      row   += "\n";

      total += row;
    }
    total += profile.getFooter();

    return total;
  }

  /**
   * Determine the longest line in an array of string.
   *
   * @param rows Array of string.
   * @return Number of character in the longest line.
   */
  protected static int determineLongestLine(String[] rows) {
    if (rows == null)
      return -1;
    int longestWidth = 0;
    for (int i=0; i<rows.length; i++)
      if (rows[i].length() > longestWidth)
        longestWidth = rows[i].length();
    return longestWidth;
  }

  /**
   * Reduce the line longer than a specific length, split the line in a smaller
   * one and place the rest as the following line.
   *
   * @param rows Array of string to check.
   * @param maxLength Length of the line to use.
   * @return An array of string shorter than the specific length.
   */
  protected static String[] trimLongLine(String[] rows, int maxLength) {
    ArrayList<String> ret   = new ArrayList<String>();
    String tmp;

    for (String row : rows) {
      while (row.length() > maxLength) {
        ret.add(row.substring(0, maxLength));
        row = row.substring(maxLength);
      }
      ret.add(row);
    }

    return ret.toArray(new String[ret.size()]);
  }

  /**
   * Append spaces at the end of a string.
   *
   * @param string String to add spaces.
   * @param number Number of spaces to add.
   * @return The new formed String.
   */
  protected static String appendSpaces(String string, int number) {
    if (string == null)
      string = "";
    for (int i=0; i<number; i++)
      string += " ";
    return string;
  }

  /**
   * Append empty lines at the end of a String array.
   *
   * @param rows Array of String
   * @param number Number of empty lines to append.
   * @return New array with a certain number of empty lines at the end.
   */
  protected static String[] appendLines(String[] rows, int number) {
    String[] ret;
    if (rows != null && number >= 0) {
      ret = new String[rows.length + number];
      for (int i=0; i<rows.length; i++)
        ret[i] = rows[i];
      for (int i=rows.length; i<ret.length; i++)
        ret[i] = "";
    }
    else
      ret = rows;
    return ret;
  }

}
