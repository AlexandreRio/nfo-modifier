package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import sun.io.CharToByteCp437;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

/**
 * Create a new RWFile object, allow to read / write nfo file and prevent
 * not wanted overwriting.
 *
 * @author Rio Alexandre
 * @version 0.1
 */
public class RWFile {

   /** Path of the current. */
   private static String currentFile;

   /** True if the file has been modified, used to ask to save. */
   private static boolean isModified;

   /**
    * Reads a nfo file (i.e. encoded in cp437) and creates a list of each
    * lines in utf-8 encoding.
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
	    list.add(line + "\n");
	 br.close();
	 isr.close();
	 fis.close();
      }
      catch (UnsupportedEncodingException e) {
	 System.err.println("Encoding error.");
      }
      catch (FileNotFoundException e) {
	 System.err.println("File not found.");
      }
      catch (IOException e) {
	 System.err.println("IO Exception.");
      }
      return list;
   }

   /**
    * Writes a nfo file (i.e. encoded in cp437) from a list of utf-8 lines.
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
}
