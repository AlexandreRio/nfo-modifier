package control;

import data.RWFile;
import view.NfoView;

import javax.swing.JTextArea;
import java.io.File;
import java.util.ArrayList;

/**
 * Reaction class called at the creation of the application.
 * @author Rio Alexandre
 * @version 0.1
 */
public abstract class CreationEvent {

   /**
    * Open and display a file, without asking for saving.
    * @param filePath Path of the file.
    */
   public static void openSpecifiedFileCreation(String filePath) {
      NfoView theView = NfoView.getInstance();
      JTextArea textArea = theView.getTextArea();
      File file;
      ArrayList<String> lineList;
      int longestLine = 0;
      if ( filePath != null ) {
	 file = new File (filePath);
	 if ( file.exists() ) {
	    lineList = RWFile.readNfoFile(file.getAbsolutePath());
	    for (String line : lineList) {
	       if (line.length() > longestLine)
		  longestLine = line.length();
	       textArea.append(line);
	    }
	    if ( longestLine > 80) {
	       textArea.setColumns(longestLine);
	       theView.pack();
	    }
	    RWFile.setCurrentFile(file.getAbsolutePath());
	    RWFile.setIsModified(false);
	    theView.setTitle("Nfo-modifier : " + file.getName());
	 }
      }
   }

}
