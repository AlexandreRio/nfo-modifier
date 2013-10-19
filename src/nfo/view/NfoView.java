package nfo.view;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowListener;

import nfo.view.TextLineNumber;

import nfo.control.CloseWindowListener;

public class NfoView extends JFrame {

  /** */
  private static final long serialVersionUID = 42L;

  /** Self reference */
  private static NfoView selfRef;

  /** Panel for text area. */
  private JPanel panel;

  /** Panel for the tools button part. */
  private JPanel buttonPanel;

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

  // Profile menu
  /** Profile menu in the menu bar. */
  private JMenu profileMenu;
  /** Create a new profile from current text area. */
  private JMenuItem itemCreateProfile;
  /** Manage item in the profile menu. */
  private JMenuItem itemManage;
  /** Load Profiles item in the profile menu. */
  private JMenuItem itemLoadProfiles;
  /** Save Profiles item in the profile menu. */
  private JMenuItem itemSaveProfiles;

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
  /** Line number area. */
  private TextLineNumber tln;

  // Tools bar
  /** */
  private JButton previewButton;

  /**
   * Constuctor of the frame, create elements and attach the reaction.
   *
   */
  public NfoView() {
    super("Nfo-modifier");
    this.createInterface();

    ImageIcon img = new ImageIcon(getClass().getResource("/res/img/ico.png"));
    this.setIconImage(img.getImage());

    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  }

  /**
   * Get the instance of the view, if it doesn't exist a new instance is
   * created.
   * @return The instance of the NfoView.
   */
  public static NfoView getInstance() {
    if (selfRef == null)
      selfRef = new NfoView();
    return selfRef;
  }

  /**
   * Create the graphical interface.
   */
  private void createInterface() {
    this.setLayout(new BorderLayout());
    panel = new JPanel();
    buttonPanel = new JPanel();
    this.add(panel, BorderLayout.CENTER);
    JButton preview = new JButton("preview");
    buttonPanel.add(preview);
    //this.add(buttonPanel, BorderLayout.NORTH);

    menuBar     = new JMenuBar();
    fileMenu    = new JMenu("File");
    profileMenu = new JMenu("Profile");
    helpMenu    = new JMenu("?");

    itemNew    = new JMenuItem("New");
    itemOpen   = new JMenuItem("Open…");
    itemSave   = new JMenuItem("Save");
    itemSaveAs = new JMenuItem("Save as…");
    itemClear  = new JMenuItem("Clear");
    itemQuit   = new JMenuItem("Quit");
    fileMenu.add(itemNew);
    fileMenu.add(new JSeparator());
    fileMenu.add(itemOpen);
    fileMenu.add(itemSave);
    fileMenu.add(itemSaveAs);
    fileMenu.add(itemClear);
    fileMenu.add(new JSeparator());
    fileMenu.add(itemQuit);

    itemCreateProfile     = new JMenuItem("Create profile");
    itemManage            = new JMenuItem("Manage");
    itemSaveProfiles      = new JMenuItem("Save Profiles");
    itemLoadProfiles      = new JMenuItem("Load Profiles");
    profileMenu.add(itemCreateProfile);
    profileMenu.add(new JSeparator());
    profileMenu.add(itemManage);
    profileMenu.add(new JSeparator());
    profileMenu.add(itemSaveProfiles);
    profileMenu.add(itemLoadProfiles);

    itemHelp  = new JMenuItem("Help");
    itemAbout = new JMenuItem("About");
    //helpMenu.add(itemHelp);
    //helpMenu.add(new JSeparator());
    helpMenu.add(itemAbout);

    textArea = new JTextArea("", 40, 120);
    textArea.setLineWrap(true);
    textArea.setFont(new Font("MonoSpaced", Font.PLAIN, 12));

    MutableAttributeSet set = new SimpleAttributeSet();
    StyleConstants.setLineSpacing(set, 1.0f);

    scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    tln = new TextLineNumber(textArea);
    scrollPane.setRowHeaderView(tln);

    panel.add(scrollPane);
    menuBar.add(fileMenu);
    menuBar.add(profileMenu);
    menuBar.add(helpMenu);
    setJMenuBar(menuBar);
  }


  /**
   * Get panel.
   *
   * @return panel as JPanel.
   */
  public JPanel getPanel()
  {
    return panel;
  }

  /**
   * Set panel.
   *
   * @param panel the value to set.
   */
  public void setPanel(JPanel panel)
  {
    this.panel = panel;
  }

  /**
   * Get buttonPanel.
   *
   * @return buttonPanel as JPanel.
   */
  public JPanel getButtonPanel()
  {
    return buttonPanel;
  }

  /**
   * Set buttonPanel.
   *
   * @param buttonPanel the value to set.
   */
  public void setButtonPanel(JPanel buttonPanel)
  {
    this.buttonPanel = buttonPanel;
  }

  /**
   * Get menuBar.
   *
   * @return menuBar as JMenuBar.
   */
  public JMenuBar getTheMenuBar()
  {
    return menuBar;
  }

  /**
   * Set menuBar.
   *
   * @param menuBar the value to set.
   */
  public void setTheMenuBar(JMenuBar menuBar)
  {
    this.menuBar = menuBar;
  }

  /**
   * Get fileMenu.
   *
   * @return fileMenu as JMenu.
   */
  public JMenu getFileMenu()
  {
    return fileMenu;
  }

  /**
   * Set fileMenu.
   *
   * @param fileMenu the value to set.
   */
  public void setFileMenu(JMenu fileMenu)
  {
    this.fileMenu = fileMenu;
  }

  /**
   * Get itemNew.
   *
   * @return itemNew as JMenuItem.
   */
  public JMenuItem getItemNew()
  {
    return itemNew;
  }

  /**
   * Set itemNew.
   *
   * @param itemNew the value to set.
   */
  public void setItemNew(JMenuItem itemNew)
  {
    this.itemNew = itemNew;
  }

  /**
   * Get itemSave.
   *
   * @return itemSave as JMenuItem.
   */
  public JMenuItem getItemSave()
  {
    return itemSave;
  }

  /**
   * Set itemSave.
   *
   * @param itemSave the value to set.
   */
  public void setItemSave(JMenuItem itemSave)
  {
    this.itemSave = itemSave;
  }

  /**
   * Get itemSaveAs.
   *
   * @return itemSaveAs as JMenuItem.
   */
  public JMenuItem getItemSaveAs()
  {
    return itemSaveAs;
  }

  /**
   * Set itemSaveAs.
   *
   * @param itemSaveAs the value to set.
   */
  public void setItemSaveAs(JMenuItem itemSaveAs)
  {
    this.itemSaveAs = itemSaveAs;
  }

  /**
   * Get itemOpen.
   *
   * @return itemOpen as JMenuItem.
   */
  public JMenuItem getItemOpen()
  {
    return itemOpen;
  }

  /**
   * Set itemOpen.
   *
   * @param itemOpen the value to set.
   */
  public void setItemOpen(JMenuItem itemOpen)
  {
    this.itemOpen = itemOpen;
  }

  /**
   * Get itemClear.
   *
   * @return itemClear as JMenuItem.
   */
  public JMenuItem getItemClear()
  {
    return itemClear;
  }

  /**
   * Set itemClear.
   *
   * @param itemClear the value to set.
   */
  public void setItemClear(JMenuItem itemClear)
  {
    this.itemClear = itemClear;
  }

  /**
   * Get itemQuit.
   *
   * @return itemQuit as JMenuItem.
   */
  public JMenuItem getItemQuit()
  {
    return itemQuit;
  }

  /**
   * Set itemQuit.
   *
   * @param itemQuit the value to set.
   */
  public void setItemQuit(JMenuItem itemQuit)
  {
    this.itemQuit = itemQuit;
  }

  /**
   * Get profileMenu.
   *
   * @return profileMenu as JMenu.
   */
  public JMenu getProfileMenu()
  {
    return profileMenu;
  }

  /**
   * Set profileMenu.
   *
   * @param profileMenu the value to set.
   */
  public void setProfileMenu(JMenu profileMenu)
  {
    this.profileMenu = profileMenu;
  }

  /**
   * Get itemCreateProfile.
   *
   * @return itemCreateProfile as JMenuItem.
   */
  public JMenuItem getItemCreateProfile()
  {
    return itemCreateProfile;
  }

  /**
   * Set itemCreateProfile.
   *
   * @param itemCreateProfile the value to set.
   */
  public void setItemCreateProfile(JMenuItem itemCreateProfile)
  {
    this.itemCreateProfile = itemCreateProfile;
  }

  /**
   * Get itemManage.
   *
   * @return itemManage as JMenuItem.
   */
  public JMenuItem getItemManage()
  {
    return itemManage;
  }

  /**
   * Set itemManage.
   *
   * @param itemManage the value to set.
   */
  public void setItemManage(JMenuItem itemManage)
  {
    this.itemManage = itemManage;
  }

  /**
   * Get itemLoadProfiles.
   *
   * @return itemLoadProfiles as JMenuItem.
   */
  public JMenuItem getItemLoadProfiles()
  {
    return itemLoadProfiles;
  }

  /**
   * Set itemLoadProfiles.
   *
   * @param itemLoadProfiles the value to set.
   */
  public void setItemLoadProfiles(JMenuItem itemLoadProfiles)
  {
    this.itemLoadProfiles = itemLoadProfiles;
  }

  /**
   * Get itemSaveProfiles.
   *
   * @return itemSaveProfiles as JMenuItem.
   */
  public JMenuItem getItemSaveProfiles()
  {
    return itemSaveProfiles;
  }

  /**
   * Set itemSaveProfiles.
   *
   * @param itemSaveProfiles the value to set.
   */
  public void setItemSaveProfiles(JMenuItem itemSaveProfiles)
  {
    this.itemSaveProfiles = itemSaveProfiles;
  }

  /**
   * Get helpMenu.
   *
   * @return helpMenu as JMenu.
   */
  public JMenu getHelpMenu()
  {
    return helpMenu;
  }

  /**
   * Set helpMenu.
   *
   * @param helpMenu the value to set.
   */
  public void setHelpMenu(JMenu helpMenu)
  {
    this.helpMenu = helpMenu;
  }

  /**
   * Get itemHelp.
   *
   * @return itemHelp as JMenuItem.
   */
  public JMenuItem getItemHelp()
  {
    return itemHelp;
  }

  /**
   * Set itemHelp.
   *
   * @param itemHelp the value to set.
   */
  public void setItemHelp(JMenuItem itemHelp)
  {
    this.itemHelp = itemHelp;
  }

  /**
   * Get itemAbout.
   *
   * @return itemAbout as JMenuItem.
   */
  public JMenuItem getItemAbout()
  {
    return itemAbout;
  }

  /**
   * Set itemAbout.
   *
   * @param itemAbout the value to set.
   */
  public void setItemAbout(JMenuItem itemAbout)
  {
    this.itemAbout = itemAbout;
  }

  /**
   * Get textArea.
   *
   * @return textArea as JTextArea.
   */
  public JTextArea getTextArea()
  {
    return textArea;
  }

  /**
   * Set textArea.
   *
   * @param textArea the value to set.
   */
  public void setTextArea(JTextArea textArea)
  {
    this.textArea = textArea;
  }

  /**
   * Get scrollPane.
   *
   * @return scrollPane as JScrollPane.
   */
  public JScrollPane getScrollPane()
  {
    return scrollPane;
  }

  /**
   * Set scrollPane.
   *
   * @param scrollPane the value to set.
   */
  public void setScrollPane(JScrollPane scrollPane)
  {
    this.scrollPane = scrollPane;
  }

  /**
   * Get tln.
   *
   * @return tln as TextLineNumber.
   */
  public TextLineNumber getTln()
  {
    return tln;
  }

  /**
   * Set tln.
   *
   * @param tln the value to set.
   */
  public void setTln(TextLineNumber tln)
  {
    this.tln = tln;
  }

  /**
   * Get previewButton.
   *
   * @return previewButton as JButton.
   */
  public JButton getPreviewButton()
  {
    return previewButton;
  }

  /**
   * Set previewButton.
   *
   * @param previewButton the value to set.
   */
  public void setPreviewButton(JButton previewButton)
  {
    this.previewButton = previewButton;
  }

}
