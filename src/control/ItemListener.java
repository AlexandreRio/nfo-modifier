package control;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
      System.out.println("new action");

   }

   private void saveAction() {
      System.out.println("Save action");
   }

   private void saveAsAction() {
      System.out.println("Save as action");
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
	 confirm = JOptionPane.showConfirmDialog (null, confirmText);

      if (confirm == JOptionPane.YES_OPTION) {
	 textArea.setText("");
	 chooser = new JFileChooser();
	 filter = new FileNameExtensionFilter("NFO Files", "nfo");
	 chooser.setFileFilter(filter);
	 returnVal = chooser.showOpenDialog(theView);
	 if(returnVal == JFileChooser.APPROVE_OPTION) {
	    System.out.println(chooser.getSelectedFile().getAbsolutePath());
	    lineList = RWFile.readNfoFile(chooser.getSelectedFile().getAbsolutePath());
	    for (String line : lineList) {
	       textArea.append(line);
	    }
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
	 confirm = JOptionPane.showConfirmDialog (null, confirmText);

      if (confirm == JOptionPane.YES_OPTION)
	 textArea.setText("");
   }

   /**
    * Exit the application after confirmation if the text area is not empty.
    */
   private void quitAction() {
      System.out.println("Quit action");
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
