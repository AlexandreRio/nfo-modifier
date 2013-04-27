package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Dimension;

public class ProfileCreationView extends JFrame {

  private static final long serialVersionUID = 42L;

  private static ProfileCreationView selfref;

  /** Main Panel of the frame. */
  private JPanel mainPanel;

  private JPanel profileNamePanel;
  private JLabel profileNameLabel;
  private JTextField profileNameField;

  private JPanel headerPanel;
  private JLabel headerLabel;
  private JTextArea headerArea;
  private JScrollPane headerScrollPane;

  private JPanel bodyPanel;
  private JLabel bodyLabel;
  private JTextArea bodyArea;
  private JScrollPane bodyScrollPane;

  private JPanel borderPanel;
  private JLabel borderLabel;
  private JTextArea borderArea;
  private JScrollPane borderScrollPane;

  private JPanel footerPanel;
  private JLabel footerLabel;
  private JTextArea footerArea;
  private JScrollPane footerScrollPane;

  private JPanel buttonPanel;
  private JButton validateButton;
  private JButton cancelButton;

  public ProfileCreationView()
  {
    super("Nfo-modifier : profile creation");
    this.createInterface();

    ImageIcon img = new ImageIcon(getClass().getResource("/res/img/ico.png"));
    this.setIconImage(img.getImage());

    this.setSize(450,600);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public static ProfileCreationView getInstance()
  {
    if (selfref == null)
      selfref = new ProfileCreationView();
    return selfref;
  }

  private void createInterface()
  {
    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));

    this.profileNamePanel = new JPanel();
    this.profileNamePanel.setLayout(new GridLayout(0,2));
    this.profileNameLabel = new JLabel("Profile name: ");
    this.profileNameField = new JTextField();
    this.profileNamePanel.add(this.profileNameLabel);
    this.profileNamePanel.add(this.profileNameField);
    this.profileNamePanel.setMaximumSize(new Dimension(250, 25));

    this.headerPanel = new JPanel();
    this.headerPanel.setLayout(new GridLayout(2,0));
    this.headerLabel = new JLabel("Header: ");
    this.headerArea = new JTextArea(1, 80);
    this.headerArea.setLineWrap(true);
    this.headerArea.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    this.headerScrollPane = new JScrollPane(this.headerArea);
    this.headerPanel.add(this.headerLabel);
    this.headerPanel.add(this.headerScrollPane);
    this.headerPanel.setMaximumSize(new Dimension(400, 100));

    this.bodyPanel = new JPanel();
    this.bodyPanel.setLayout(new GridLayout(2,0));
    this.bodyLabel = new JLabel("Body: ");
    this.bodyArea = new JTextArea(1,80);
    this.bodyArea.setLineWrap(true);
    this.bodyArea.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
    this.bodyScrollPane = new JScrollPane(this.bodyArea);
    this.bodyPanel.add(bodyLabel);
    this.bodyPanel.add(bodyScrollPane);
    this.bodyPanel.setMaximumSize(new Dimension(400, 200));

    //TODO: add combo box both/left/right
    this.borderPanel = new JPanel();
    this.borderPanel.setLayout(new GridLayout(2,0));
    this.borderLabel = new JLabel("Border: ");
    this.borderArea = new JTextArea(1,80);
    this.borderArea.setLineWrap(true);
    this.borderArea.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
    this.borderScrollPane = new JScrollPane(this.borderArea);
    this.borderPanel.add(borderLabel);
    this.borderPanel.add(borderScrollPane);
    this.borderPanel.setMaximumSize(new Dimension(400, 100));

    this.footerPanel = new JPanel();
    this.footerPanel.setLayout(new GridLayout(2,0));
    this.footerLabel = new JLabel("footer: ");
    this.footerArea = new JTextArea(1,80);
    this.footerArea.setLineWrap(true);
    this.footerArea.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
    this.footerScrollPane = new JScrollPane(this.footerArea);
    this.footerPanel.add(footerLabel);
    this.footerPanel.add(footerScrollPane);
    this.footerPanel.setMaximumSize(new Dimension(400, 100));

    this.buttonPanel = new JPanel();
    this.buttonPanel.setLayout(new GridLayout(0,3));
    this.validateButton = new JButton("Validate");
    this.cancelButton = new JButton("Cancel");
    this.buttonPanel.add(this.validateButton);
    this.buttonPanel.add(new JPanel());
    this.buttonPanel.add(this.cancelButton);
    this.buttonPanel.setMaximumSize(new Dimension(400, 35));

    this.mainPanel.add(Box.createVerticalStrut(15));
    this.mainPanel.add(profileNamePanel);
    this.mainPanel.add(Box.createVerticalStrut(15));
    this.mainPanel.add(headerPanel);
    this.mainPanel.add(Box.createVerticalStrut(15));
    this.mainPanel.add(bodyPanel);
    this.mainPanel.add(Box.createVerticalStrut(15));
    this.mainPanel.add(borderPanel);
    this.mainPanel.add(Box.createVerticalStrut(15));
    this.mainPanel.add(footerPanel);
    this.mainPanel.add(Box.createVerticalStrut(15));
    this.mainPanel.add(buttonPanel);
    this.mainPanel.add(Box.createVerticalStrut(15));
    this.add(mainPanel);
  }


  /**
   * Get mainPanel.
   *
   * @return mainPanel as JPanel.
   */
  public JPanel getMainPanel()
  {
    return mainPanel;
  }

  /**
   * Get profileNamePanel.
   *
   * @return profileNamePanel as JPanel.
   */
  public JPanel getProfileNamePanel()
  {
    return profileNamePanel;
  }

  /**
   * Get profileNameLabel.
   *
   * @return profileNameLabel as JLabel.
   */
  public JLabel getProfileNameLabel()
  {
    return profileNameLabel;
  }

  /**
   * Get profileNameField.
   *
   * @return profileNameField as JTextField.
   */
  public JTextField getProfileNameField()
  {
    return profileNameField;
  }

  /**
   * Get headerPanel.
   *
   * @return headerPanel as JPanel.
   */
  public JPanel getHeaderPanel()
  {
    return headerPanel;
  }

  /**
   * Get headerLabel.
   *
   * @return headerLabel as JLabel.
   */
  public JLabel getHeaderLabel()
  {
    return headerLabel;
  }

  /**
   * Get headerArea.
   *
   * @return headerArea as JTextArea.
   */
  public JTextArea getHeaderArea()
  {
    return headerArea;
  }

  /**
   * Get headerScrollPane.
   *
   * @return headerScrollPane as JScrollPane.
   */
  public JScrollPane getHeaderScrollPane()
  {
    return headerScrollPane;
  }

  /**
   * Get bodyPanel.
   *
   * @return bodyPanel as JPanel.
   */
  public JPanel getBodyPanel()
  {
    return bodyPanel;
  }

  /**
   * Get bodyLabel.
   *
   * @return bodyLabel as JLabel.
   */
  public JLabel getBodyLabel()
  {
    return bodyLabel;
  }

  /**
   * Get bodyArea.
   *
   * @return bodyArea as JTextArea.
   */
  public JTextArea getBodyArea()
  {
    return bodyArea;
  }

  /**
   * Get bodyScrollPane.
   *
   * @return bodyScrollPane as JScrollPane.
   */
  public JScrollPane getBodyScrollPane()
  {
    return bodyScrollPane;
  }

  /**
   * Get borderPanel.
   *
   * @return borderPanel as JPanel.
   */
  public JPanel getBorderPanel()
  {
    return borderPanel;
  }

  /**
   * Get borderLabel.
   *
   * @return borderLabel as JLabel.
   */
  public JLabel getBorderLabel()
  {
    return borderLabel;
  }

  /**
   * Get borderArea.
   *
   * @return borderArea as JTextArea.
   */
  public JTextArea getBorderArea()
  {
    return borderArea;
  }

  /**
   * Get borderScrollPane.
   *
   * @return borderScrollPane as JScrollPane.
   */
  public JScrollPane getBorderScrollPane()
  {
    return borderScrollPane;
  }

  /**
   * Get footerPanel.
   *
   * @return footerPanel as JPanel.
   */
  public JPanel getFooterPanel()
  {
    return footerPanel;
  }

  /**
   * Get footerLabel.
   *
   * @return footerLabel as JLabel.
   */
  public JLabel getFooterLabel()
  {
    return footerLabel;
  }

  /**
   * Get footerArea.
   *
   * @return footerArea as JTextArea.
   */
  public JTextArea getFooterArea()
  {
    return footerArea;
  }

  /**
   * Get footerScrollPane.
   *
   * @return footerScrollPane as JScrollPane.
   */
  public JScrollPane getFooterScrollPane()
  {
    return footerScrollPane;
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
   * Get validateButton.
   *
   * @return validateButton as JButton.
   */
  public JButton getValidateButton()
  {
    return validateButton;
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
