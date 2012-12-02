package view;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Font;

import java.net.URL;

import control.ItemListener;
import control.TextAreaListener;

public class NfoView extends JFrame {

   /** */
   private static final long serialVersionUID = 42L;

   /** */
   private JPanel panel;

   // Menu bar
   /** Menu bar of the frame. */
   private JMenuBar menuBar;

   // File menu
   /** File menu in the menu bar. */
   private JMenu fileMenu;
   /** New item in the menu bar. */
   private JMenuItem itemNew;
   /** Save item in the menu bar. */
   private JMenuItem itemSave;
   /** Save as item in the menu bar. */
   private JMenuItem itemSaveAs;
   /** Open item in the menu bar. */
   private JMenuItem itemOpen;
   /** Clear item in the menu bar. */
   private JMenuItem itemClear;
   /** Quit item in the menu bar. */
   private JMenuItem itemQuit;

   // Help menu
   /** Help menu in the menu bar. */
   private JMenu helpMenu;
   /** Help item in the menu bar. */
   private JMenuItem itemHelp;
   /** About item in the menu bar. */
   private JMenuItem itemAbout;

   // Content of the frame
   /** Text area, to view and edit file. */
   private JTextArea textArea;
   /** Used to handle a scroll bar. */
   private JScrollPane scrollPane;

   /**
    * Constuctor of the frame, create elements and attach the reaction.
    *
    */
   public NfoView() {
      super("Nfo-modifier");
      this.createInterface();
      this.attachReactions();

      ImageIcon img = new ImageIcon(getClass().getResource("/res/img/ico.png"));
      this.setIconImage(img.getImage());

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
      itemSave = new JMenuItem("Save");
      itemSaveAs = new JMenuItem("Save as…");
      itemClear = new JMenuItem("Clear");
      itemQuit = new JMenuItem("Quit");
      fileMenu.add(itemNew);
      fileMenu.add(new JSeparator());
      fileMenu.add(itemOpen);
      fileMenu.add(itemSave);
      fileMenu.add(itemSaveAs);
      fileMenu.add(itemClear);
      fileMenu.add(new JSeparator());
      fileMenu.add(itemQuit);

      itemHelp = new JMenuItem("Help");
      itemAbout = new JMenuItem("About");
      //helpMenu.add(itemHelp);
      //helpMenu.add(new JSeparator());
      helpMenu.add(itemAbout);

      textArea = new JTextArea("", 40, 80);
      textArea.setLineWrap(true);
      textArea.setFont(new Font("MonoSpaced", Font.PLAIN, 12));
      scrollPane = new JScrollPane(textArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
      itemSave.addActionListener(new ItemListener(this));
      itemSaveAs.addActionListener(new ItemListener(this));
      itemOpen.addActionListener(new ItemListener(this));
      itemClear.addActionListener(new ItemListener(this));
      itemQuit.addActionListener(new ItemListener(this));
      itemHelp.addActionListener(new ItemListener(this));
      itemAbout.addActionListener(new ItemListener(this));

      textArea.getDocument().addDocumentListener(new TextAreaListener(this));
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

   /**
    * Get itemSave.
    *
    * @return itemSave as JMenuItem.
    */
   public JMenuItem getItemSave() {
      return itemSave;
   }

   /**
    * Set itemSave.
    *
    * @param itemSave the value to set.
    */
   public void setItemSave(JMenuItem itemSave) {
      this.itemSave = itemSave;
   }
}
