package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import data.*;
import view.NfoView;

/**
 * Handle menu item reactions.
 * @author Rio Alexandre
 * @version 0.1
 */
public class ItemListener implements ActionListener {

   private NfoView theView;

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

   private void openAction() {
      ArrayList<String> list = RWFile.readNfoFile("res/nfo/exemple.nfo");
      JTextArea textArea = theView.getTextArea();
      if (! textArea.getText().equals(""))
	 System.out.println("pop-up confirmation");
      for (String line : list) {
	 textArea.append(line);
      }
      System.out.println("open action");
   }

   private void clearAction() {
      JTextArea textArea = theView.getTextArea();
      if (! textArea.getText().equals(""))
	 System.out.println("pop-up confirmation");
      textArea.setText("");
      System.out.println("clear action");
   }

   private void quitAction() {
      System.out.println("Quit action");
   }

   private void helpAction() {
      System.out.println("Help action");
   }

   private void aboutAction() {
      System.out.println("About action");
   }
}
