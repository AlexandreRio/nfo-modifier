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

    String[] borderRow;
    String[] borderRowRaw  = profile.getBorder().split("\n");
    borderRow = new String[borderRowRaw.length + 2];
    for (int i=0; i<borderRowRaw.length; i++)
      borderRow[i] = borderRowRaw[i];
    borderRow[borderRow.length-2] = " ";
    borderRow[borderRow.length-1] = " ";


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

    for (int i=0; i<bodyRow.length; i++) {
      row = "";
      if (((bodyRow.length - i) >= borderRow.length) || (i%borderRow.length != 0)) {
        border = borderRow[i%borderRow.length];
        row    = border;
        row    = appendSpaces(row, borderWidth - border.length() + BORDER_CONTENT_MARGIN);
        row   += bodyRow[i];
        row    = appendSpaces(row, totalWidth - borderWidth - row.length());
        row   += border;
      } else {
        row  = appendSpaces(row, borderWidth + BORDER_CONTENT_MARGIN);
        row += bodyRow[i];
        row  = appendSpaces(row, totalWidth - row.length());
      }
      row   += "\n";
      total += row;
    }
    total += "\n" + profile.getFooter();

    return total;
  }

  /**
   * Determine the longest line in an array of string.
   *
   * @param rows Array of string.
   * @return Number of character in the longest line.
   */
  private static int determineLongestLine(String[] rows) {
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
  private static String[] trimLongLine(String[] rows, int maxLength) {
    String[] ret;
    int numberRow = 0;
    int index = 0;
    for (String row : rows)
      numberRow += (int)(row.length() / maxLength) + 1;
    ret = new String[numberRow];

    for (int i=0; i<rows.length; i++) {
      if (rows[i].length() > maxLength) {
        ret[index] = rows[i].substring(0, maxLength);
        rows[i] = rows[i].substring(maxLength+1, rows[i].length());
        index++;
        i--;
      }
      else {
        ret[index] = rows[i];
        index++;
      }
    }
    return ret;
  }

  /**
   * Append spaces at the end of a string.
   *
   * @param string String to add spaces.
   * @param number Number of spaces to add.
   * @return The new formed String.
   */
  private static String appendSpaces(String string, int number) {
    if (string == null)
      string = "";
    for (int i=0; i<number; i++)
      string += " ";
    return string;
  }

}
