package control;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;

import data.*;
import view.NfoView;
import view.ConfirmView;

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
    * Create an ItemListener to handle events on the menu items.
    * @param view
    */
   public ItemListener(NfoView view) {
      this.theView = view;
   }

   public void actionPerformed(ActionEvent e) {
      JMenuItem src = (JMenuItem) e.getSource();
      if (src == theView.getItemNew())
	 this.newAction();
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

      if (RWFile.getModified())
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 textArea.setText("");
	 RWFile.setCurrentFile(null);
	 RWFile.setModified(false);
	 theView.setTitle("Nfo-modifier");
      }
   }

   /**
    *
    */
   private void saveAsAction() {
      File file = null;
      JFileChooser chooser;
      FileNameExtensionFilter filter;
      int returnVal;
      String text;
      String[] lines;
      ArrayList<String> list;

      if (RWFile.getCurrentFile() == null) {
	 chooser = new JFileChooser();
	 filter = new FileNameExtensionFilter("NFO Files", "nfo");
	 chooser.setFileFilter(filter);
	 returnVal = chooser.showOpenDialog(theView);
	 if(returnVal == JFileChooser.APPROVE_OPTION) {
	    file = chooser.getSelectedFile();
	    RWFile.setCurrentFile(file.getPath());
	    RWFile.setModified(false);
	    theView.setTitle("Nfo-modifier : " + file.getName());
	 }
      }
      else
	 file = new File(RWFile.getCurrentFile());

      if (file != null) {
	 // Creates an ArrayList from the JTextArea
	 list = new ArrayList<String>();
	 text = theView.getTextArea().getText();
	 lines = text.split("\n");
	 for (int i=0; i<lines.length; i++)
	    list.add(lines[i] + "\n");

	 RWFile.writeNfoFile(list, file.getPath());
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
	       textArea.append(line);
	    }
	    RWFile.setCurrentFile(chooser.getSelectedFile().getAbsolutePath());
	    RWFile.setModified(false);
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

      if (! textArea.getText().equals(""))
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 textArea.setText("");
	 RWFile.setModified(true);
      }
   }

   /**
    * Exit the application after confirmation if the text area is not empty.
    */
   private void quitAction() {
      int confirm = JOptionPane.YES_OPTION;

      if (RWFile.getModified())
	 confirm = JOptionPane.showConfirmDialog (theView, confirmText);

      if (confirm == JOptionPane.YES_OPTION)
	 theView.dispose();
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
      System.out.println("About action");
   }
}
