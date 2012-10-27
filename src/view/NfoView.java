package view;

import javax.swing.*;
import java.awt.*;

import control.ItemListener;

public class NfoView extends JFrame {

   /** */
   private static final long serialVersionUID = 42L;

   /** */
   private JPanel panel;

   // Menu bar
   /** */
   private JMenuBar menuBar;

   // File menu
   /** */
   private JMenu fileMenu;
   /** */
   private JMenuItem itemNew;
   /** */
   private JMenuItem itemSaveAs;
   /** */
   private JMenuItem itemOpen;
   /** */
   private JMenuItem itemClear;
   /** */
   private JMenuItem itemQuit;

   // Help menu
   /** */
   private JMenu helpMenu;
   /** */
   private JMenuItem itemHelp;
   /** */
   private JMenuItem itemAbout;

   private JTextArea textArea;
   private JScrollPane scrollPane;

   public NfoView() {
      super("Nfo-modifier");
      this.createInterface();
      this.attachReactions();
      //this.setSize(600,600);
      this.pack();
      this.setVisible(true);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   /**
    * Create the user interface.
    */
   private void createInterface() {
      this.setLayout(new BorderLayout());
      panel = new JPanel();
      this.add(panel, BorderLayout.CENTER);

      menuBar = new JMenuBar();
      fileMenu = new JMenu("File");
      helpMenu = new JMenu("?");

      itemNew = new JMenuItem("New");
      itemOpen = new JMenuItem("Open…");
      itemSaveAs = new JMenuItem("Save as…");
      itemClear = new JMenuItem("Clear");
      itemQuit = new JMenuItem("Quit");
      fileMenu.add(itemNew);
      fileMenu.add(new JSeparator());
      fileMenu.add(itemOpen);
      fileMenu.add(itemSaveAs);
      fileMenu.add(itemClear);
      fileMenu.add(new JSeparator());
      fileMenu.add(itemQuit);

      itemHelp = new JMenuItem("Help");
      itemAbout = new JMenuItem("About");
      helpMenu.add(itemHelp);
      helpMenu.add(new JSeparator());
      helpMenu.add(itemAbout);

      textArea = new JTextArea("", 40, 80);
      textArea.setLineWrap(true);
      textArea.setFont(new Font("MonoSpaced", Font.PLAIN, 12));
      scrollPane = new JScrollPane(textArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      panel.add(scrollPane);
      menuBar.add(fileMenu);
      menuBar.add(helpMenu);
      setJMenuBar(menuBar);
   }

   /**
    * Attach the reactions on the interface components.
    */
   private void attachReactions() {
      itemNew.addActionListener(new ItemListener(this));
      itemSaveAs.addActionListener(new ItemListener(this));
      itemOpen.addActionListener(new ItemListener(this));
      itemClear.addActionListener(new ItemListener(this));
      itemQuit.addActionListener(new ItemListener(this));
      itemHelp.addActionListener(new ItemListener(this));
      itemAbout.addActionListener(new ItemListener(this));
   }


   /**
    * Get panel.
    *
    * @return panel as JPanel.
    */
   public JPanel getPanel() {
      return panel;
   }

   /**
    * Get menuBar.
    *
    * @return menuBar as JMenuBar.
    */
   public JMenuBar getJMenuBar() {
      return this.getJMenuBar();
   }

   /**
    * Get fileMenu.
    *
    * @return fileMenu as JMenu.
    */
   public JMenu getFileMenu() {
      return fileMenu;
   }

   /**
    * Get itemNew.
    *
    * @return itemNew as JMenuItem.
    */
   public JMenuItem getItemNew() {
      return itemNew;
   }

   /**
    * Get itemSaveAs.
    *
    * @return itemSaveAs as JMenuItem.
    */
   public JMenuItem getItemSaveAs() {
      return itemSaveAs;
   }

   /**
    * Get itemOpen.
    *
    * @return itemOpen as JMenuItem.
    */
   public JMenuItem getItemOpen() {
      return itemOpen;
   }

   /**
    * Get itemClear.
    *
    * @return itemClear as JMenuItem.
    */
   public JMenuItem getItemClear() {
      return itemClear;
   }

   /**
    * Get itemQuit.
    *
    * @return itemQuit as JMenuItem.
    */
   public JMenuItem getItemQuit() {
      return itemQuit;
   }

   /**
    * Get helpMenu.
    *
    * @return helpMenu as JMenu.
    */
   public JMenu getHelpMenu() {
      return helpMenu;
   }

   /**
    * Get itemHelp.
    *
    * @return itemHelp as JMenuItem.
    */
   public JMenuItem getItemHelp() {
      return itemHelp;
   }

   /**
    * Get itemAbout.
    *
    * @return itemAbout as JMenuItem.
    */
   public JMenuItem getItemAbout() {
      return itemAbout;
   }

   /**
    * Get textArea.
    *
    * @return textArea as JTextArea.
    */
   public JTextArea getTextArea() {
       return textArea;
   }
}
