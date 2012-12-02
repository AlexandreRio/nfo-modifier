package control;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.io.File;

import data.RWFile;
import view.NfoView;

/**
 * Handle menu item reactions.
 * @author Rio Alexandre
 * @version 0.1
 */
public class ItemListener implements ActionListener {

   /** Frame containing the Component having listener. */
   private NfoView theView;

   /**
    * Text displayed if the text area is not empty and have to be cleared.
    */
   private static String confirmText = "<html><h3><b>Continue without saving"
      + " ?</b></h3> <br />If you don't save, changes will be permanently"
      + " lost.</html>";

   /**
    * Text displayed if the file lines are longer than 80 columns ( this is
    * deprecated).
    */
   private static String sizeWarningText = "<html><h2>Warning</h2><p>The text"
      + " contains line longer than 80 columns, this is deprecated.</p></html>";

   /**
    * Text displayed in the about dialog, after clicking on about in the menu
    * bar.
    */
   private static String aboutText = "<html><center><p><b>nfo-modifier</p></b><br \\>" +
      "<p>0.1</p><br \\><p>nfo-modifier is a small and lightweiqht nfo editor written" +
      " in Java</p><p>See the project page at <a href=''>www.github.com/AlexandreRio/" +
      "nfo-modifier</a></p><br \\><p>This program comes with ABSOLUTELY NO WARRANTY" +
      "</p></center></html>";

   /**
    * Text displayed in the help dialog, after clicking on help in the menu
    * bar.
    */
   private static String helptext = "";

   /**
    * Create an ItemListener to handle events on the menu items.
    * @param view Frame where the items are.
    */
   public ItemListener(NfoView view) {
      this.theView = view;
   }

   public void actionPerformed(ActionEvent e) {
      JMenuItem src = (JMenuItem) e.getSource();
      if (src == theView.getItemNew())
	 this.newAction();
      else if (src == theView.getItemSave())
	 this.saveAction();
      else if ( src == theView.getItemSaveAs())
	 this.saveAsAction();
      else if ( src == theView.getItemOpen())
	 this.openAction();
      else if ( src == theView.getItemClear())
	 this.clearAction();
      else if ( src == theView.getItemQuit())
	 this.quitAction();
      else if ( src == theView.getItemHelp())
	 this.helpAction();
      else if ( src == theView.getItemAbout())
	 this.aboutAction();
   }

   private void newAction() {
      int confirm = JOptionPane.YES_OPTION;
      JTextArea textArea = theView.getTextArea();

      if (RWFile.getIsModified())
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 textArea.setText("");
	 textArea.setColumns(80);
	 theView.pack();
	 RWFile.setCurrentFile(null);
	 RWFile.setIsModified(false);
	 theView.setTitle("Nfo-modifier");
      }
   }

   /**
    * Write the content of the text area in the current file, if its not
    * defined call saveAsAction()
    */
   private void saveAction() {
      File file;
      if (RWFile.getCurrentFile() == null)
	 this.saveAsAction();
      else {
	 this.save();
	 RWFile.setIsModified(false);
	 file = new File(RWFile.getCurrentFile());
	 theView.setTitle("Nfo-modifier : " + file.getName());
      }
   }

   /**
    * Ask to choose the name of the file to save.
    */
   private void saveAsAction() {
      File file = null;
      JFileChooser chooser;
      FileNameExtensionFilter filter;
      int returnVal;

      chooser = new JFileChooser();
      filter = new FileNameExtensionFilter("NFO Files", "nfo");
      chooser.setFileFilter(filter);
      returnVal = chooser.showOpenDialog(theView);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
	 file = chooser.getSelectedFile();
	 RWFile.setCurrentFile(file.getPath());
	 RWFile.setIsModified(false);
	 theView.setTitle("Nfo-modifier : " + file.getName());
	 this.save();
      }

   }

   /**
    * Write the content of the textArea in the currentFile.
    */
   private void save() {
      String text;
      String[] lines;
      ArrayList<String> list;

      if (RWFile.getCurrentFile() != null) {
	 list = new ArrayList<String>();
	 text = theView.getTextArea().getText();
	 lines = text.split("\n");
	 for (int i=0; i<lines.length; i++)
	    list.add(lines[i] + "\n");
	 RWFile.writeNfoFile(list, RWFile.getCurrentFile());
      }
   }

   /**
    * Open a file and display it, if a file is already displayed ask to confirm
    * the action.
    */
   private void openAction() {
      JFileChooser chooser;
      FileNameExtensionFilter filter;
      int returnVal;
      ArrayList<String> lineList;
      JTextArea textArea = theView.getTextArea();
      int confirm = JOptionPane.YES_OPTION;
      int longestLine = 0;

      if (! textArea.getText().equals(""))
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 textArea.setText("");
	 chooser = new JFileChooser();
	 filter = new FileNameExtensionFilter("NFO Files", "nfo");
	 chooser.setFileFilter(filter);
	 returnVal = chooser.showOpenDialog(theView);
	 if(returnVal == JFileChooser.APPROVE_OPTION) {
	    lineList = RWFile.readNfoFile(chooser.getSelectedFile().getAbsolutePath());
	    for (String line : lineList) {
	       if (line.length() > longestLine)
		  longestLine = line.length();
	       textArea.append(line);
	    }
	    if (longestLine > 80) {
	       textArea.setColumns(longestLine);
	       theView.pack();
	       JOptionPane.showMessageDialog(theView, sizeWarningText);
	    }
	    RWFile.setCurrentFile(chooser.getSelectedFile().getAbsolutePath());
	    RWFile.setIsModified(false);
	    theView.setTitle("Nfo-modifier : " + chooser.getSelectedFile().getName());
	 }
      }
   }

   /**
    * Clear the text area after confirmation if the text area is not empty.
    */
   private void clearAction() {
      JTextArea textArea = theView.getTextArea();
      int confirm = JOptionPane.YES_OPTION;

      if (RWFile.getIsModified())
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 textArea.setText("");
	 RWFile.setIsModified(true);
      }
   }

   /**
    * Exit the application after confirmation if the text area is not empty.
    */
   private void quitAction() {
      int confirm = JOptionPane.YES_OPTION;

      if (RWFile.getIsModified())
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 theView.dispose();
	 System.exit(0);
      }
   }

   /**
    * Display help window.
    */
   private void helpAction() {
      System.out.println("Help action");
   }

   /**
    * Display about window.
    */
   private void aboutAction() {
      JPanel aboutPanel = new JPanel();
      JLabel aboutLabel = new JLabel(aboutText);
      aboutPanel.add(aboutLabel);
      JOptionPane.showMessageDialog(theView,aboutPanel);
   }
}
