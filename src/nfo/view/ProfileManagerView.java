package nfo.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.GridLayout;
import java.awt.Dimension;

import nfo.data.Profile;

public class ProfileManagerView extends JFrame {

  private static final long serialVersionUID = 42L;

  private static ProfileManagerView selfref;

  /** Main Panel of the frame. */
  private JPanel mainPanel;

  /** List of all the saved profile. */
  private JComboBox<Profile> profileList;

  /** Panel of the bottom buttons. */
  private JPanel buttonPanel;

  /** Create a new NFO from a profile. */
  private JButton createButton;

  /** Edit button in the button panel. */
  private JButton editButton;

  /** Delete button in the button panel. */
  private JButton deleteButton;

  /** Cancel button in the button panel. */
  private JButton cancelButton;

  /**
   * Create a new profile manager frame.
   */
  public ProfileManagerView()
  {
    super("Nfo-modifier : profile manager");
    this.createInterface();

    ImageIcon img = new ImageIcon(getClass().getResource("/res/img/ico.png"));
    this.setIconImage(img.getImage());

    this.setSize(500,200);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public static ProfileManagerView getInstance()
  {
    if (selfref == null)
      selfref = new ProfileManagerView();
    return selfref;
  }

  /**
   * Create the graphical interface.
   */
  private void createInterface()
  {
    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));


    profileList = new JComboBox();

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(0,4));

    createButton = new JButton("Create NFO");
    editButton   = new JButton("Edit");
    deleteButton = new JButton("Delete");
    cancelButton = new JButton("Cancel");

    buttonPanel.add(createButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(cancelButton);

    this.profileList.setMaximumSize(new Dimension(250,35));
    this.buttonPanel.setMaximumSize(new Dimension(480,35));
    this.mainPanel.add(Box.createVerticalStrut(30));
    this.mainPanel.add(profileList, this.mainPanel.CENTER_ALIGNMENT);
    this.mainPanel.add(Box.createVerticalStrut(30));
    this.mainPanel.add(buttonPanel, this.mainPanel.CENTER_ALIGNMENT);
    this.add(mainPanel);
  }


  /**
   * Get profileList.
   *
   * @return profileList as JComboBox.
   */
  public JComboBox getProfileList()
  {
    return profileList;
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
   * Get createButton.
   *
   * @return createButton as JButton.
   */
  public JButton getCreateButton()
  {
    return createButton;
  }

  /**
   * Get editButton.
   *
   * @return editButton as JButton.
   */
  public JButton getEditButton()
  {
    return editButton;
  }

  /**
   * Get deleteButton.
   *
   * @return deleteButton as JButton.
   */
  public JButton getDeleteButton()
  {
    return deleteButton;
  }

  /**
   * Get cancelButton.
   *
   * @return cancelButton as JButton.
   */
  public JButton getCancelButton()
  {
    return cancelButton;
  }
}
