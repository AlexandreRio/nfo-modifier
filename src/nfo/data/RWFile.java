package nfo.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import sun.io.CharToByteCp437;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a new RWFile object, allow to read / write nfo file and prevent
 * not wanted overwriting.
 *
 * @author Rio Alexandre
 * @version 1.0
 */
public abstract class RWFile {

  /** Path of the current. */
  private static String currentFile;

  /** True if the file has been modified, used to ask to save. */
  private static boolean isModified;

  /**
   * Reads a nfo file (i.e. encoded in cp437) and creates a list of each
   * lines in utf-8 encoding.
   *
   * @param file Path of the file to read.
   * @return List of each lines of the file.
   */
  public static ArrayList<String> readNfoFile(String file) {
    ArrayList<String> list = new ArrayList<String>();
    try {
      FileInputStream fis	 = new FileInputStream(file);
      InputStreamReader isr	 = new InputStreamReader(fis, "cp437");
      BufferedReader br	 = new BufferedReader(isr);

      String line = null;
      while ((line = br.readLine()) != null )
        list.add(line);
      br.close();
      isr.close();
      fis.close();
    }
    catch (UnsupportedEncodingException e) {
      System.err.println("Encoding error.");
      e.printStackTrace();
    }
    catch (FileNotFoundException e) {
      System.err.println("File not found.");
      e.printStackTrace();
    }
    catch (IOException e) {
      System.err.println("IO Exception.");
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Writes a nfo file (i.e. encoded in cp437) from a list of utf-8 lines.
   *
   * @param list List of utf-8 lines.
   * @param file Path of the output file. Can overwrites existing file.
   */
  public static void writeNfoFile(ArrayList<String> list, String file) {
    CharToByteCp437 Cp437 = new CharToByteCp437();
    try {
      FileOutputStream fos = new FileOutputStream(file);
      boolean unsuportedChar;
      char[] charToConvert;
      byte[] convertedBytes;

      for (String line : list) {
        unsuportedChar = false;
        charToConvert = line.toCharArray();
        for (int i=0; i < charToConvert.length; i++) {
          if (!Cp437.canConvert(charToConvert[i]))
            unsuportedChar = true;
        }
        if (! unsuportedChar) {
          convertedBytes = Cp437.convertAll(charToConvert);
          fos.write(convertedBytes);
        }
      }
    }
    catch (IOException e) {
      System.err.println("IO Exception.");
      e.printStackTrace();
    }
  }

  /**
   * Get currentFile.
   *
   * @return currentFile as String.
   */
  public static String getCurrentFile() {
    return currentFile;
  }

  /**
   * Set currentFile.
   *
   * @param currentFile the value to set.
   */
  public static void setCurrentFile(String currentFile) {
    RWFile.currentFile = currentFile;
  }

  /**
   * Get modified.
   *
   * @return modified as boolean.
   */
  public static boolean getIsModified() {
    return isModified;
  }

  /**
   * Set modified.
   *
   * @param modified the value to set.
   */
  public static void setIsModified(boolean modified) {
    RWFile.isModified = modified;
  }

  /**
   * Read a file line by line.
   *
   * @param path Path of the file to read
   * @return The content of the file in an array, each row of the array contains
   * a line of the file.
   */
  public static String[] readFile(String path) {
    ArrayList<String> list = new ArrayList<String>();
    String[] ret;

    try {
      FileInputStream fis   = new FileInputStream(path);
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br     = new BufferedReader(isr);

      String line = null;
      while ((line = br.readLine()) != null)
        list.add(line);
      br.close();
      isr.close();
      fis.close();
    }
    catch (UnsupportedEncodingException e) {
      System.err.println("Encoding error.");
      e.printStackTrace();
    }
    catch (FileNotFoundException e) {
      System.err.println("File not found.");
      e.printStackTrace();
    }
    catch (IOException e) {
      System.err.println("IO Exception.");
      e.printStackTrace();
    }
    ret = new String[list.size()];
    //Can't cast from list.toArray()
    for(int i=0; i<list.size(); i++)
      ret[i] = list.get(i);
    return ret;
  }

  /**
   * Read a file and put all its content into one String.
   *
   * @param path File to read.
   * @return The content of a file in one String.
   */
  public static String readFileInString(String path) {
    String ret = "";
    for(String line : readFile(path))
      ret += line + "\n";
    return ret;
  }

  /**
   * Write list of lines in a file.
   *
   * @param lines List of lines to write in a file.
   * @param path Path of the file to create.
   */
  public static void writeFile(List<String> lines, String path) {
    try {
      FileWriter fw     = new FileWriter(path);
      BufferedWriter bw = new BufferedWriter(fw);

      for (String line : lines)
        bw.write(line);

      bw.close();
    }
    catch (IOException e) {
      System.err.println("IO Exception.");
      e.printStackTrace();
    }
  }
}
