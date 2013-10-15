package nfo.data;

import org.junit.*;
import junit.framework.TestCase;

public class ProfileCreatorTest extends TestCase {

  @Test
  public void testTrimLongLine() {
    String[] linesBlock    = new String[4];
    String[] expectedBlock = new String[5];

    linesBlock[0] = "@@@@@@@@@";
    linesBlock[1] = "@@@@@";
    linesBlock[2] = "@@@@@@@@@@@@@@@@@@";
    linesBlock[3] = "@@@@@@@@@@@";

    expectedBlock[0] = "@@@@@@@@@";
    expectedBlock[1] = "@@@@@";
    expectedBlock[2] = "@@@@@@@@@@@@";
    expectedBlock[3] = "@@@@@@";
    expectedBlock[4] = "@@@@@@@@@@@";

    String[] result = ProfileCreator.trimLongLine(linesBlock, 12);
    for (int i=0; i<expectedBlock.length; i++)
      assertTrue(expectedBlock[i].equals(result[i]));
  }

  @Test
  public void testDeterminelongestLine() {
    String[] linesBlock = new String[5];
    linesBlock[0] = "&";
    linesBlock[1] = "&&";
    linesBlock[2] = "&&&";
    linesBlock[3] = "&&";
    linesBlock[4] = "&";
    int longestLine = ProfileCreator.determineLongestLine(linesBlock);
    assertEquals(longestLine, 3);

    longestLine = ProfileCreator.determineLongestLine(null);
    assertEquals(longestLine, -1);
  }

  @Test
  public void testAppendSpaces() {
    String shortLine = "foo";
    String longLine  = ProfileCreator.appendSpaces(shortLine, 2);
    assertEquals(shortLine.length() + 2, longLine.length());
  }

  @Test
  public void testAppendLines() {
    String[] linesBlock    = new String[4];
    String[] expectedBlock = new String[6];
    String[] result;

    linesBlock[0] = "@";
    linesBlock[1] = "@@";
    linesBlock[2] = "@@@";
    linesBlock[3] = "@@@@";

    expectedBlock[0] = "@";
    expectedBlock[1] = "@@";
    expectedBlock[2] = "@@@";
    expectedBlock[3] = "@@@@";
    expectedBlock[4] = "";
    expectedBlock[5] = "";

    result = ProfileCreator.appendLines(linesBlock, 2);
    for (int i=0; i<expectedBlock.length; i++)
      assertTrue(expectedBlock[i].equals(result[i]));
  }
}
