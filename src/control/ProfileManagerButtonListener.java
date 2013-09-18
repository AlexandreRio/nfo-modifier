package control;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.NfoView;
import view.ProfileManager;
import view.ProfileCreationView;

import data.Profile;
import data.ProfileList;

/**
 * Handle profile manager button reactions
 *
 * @author Rio Alexandre
 * @version 0.1
 */
public class ProfileManagerButtonListener implements ActionListener {

  /** Frame containing the profile manager component. */
  private ProfileManager theView;

  private static ProfileManagerButtonListener selfRef;

  /** Margin between the border and the first character of the body. */
  private static final int BORDER_CONTENT_MARGIN = 2;
  /**
   * Default constructor.
   */
  public ProfileManagerButtonListener() {
    this.theView = ProfileManager.getInstance();
  }

  /**
   * Get the instance of the listener, if it doesn't exist a new instance is
   * created.
   * @return The instance of the ProfileManagerButtonListener.
   */
  public static ProfileManagerButtonListener getInstance() {
    if ( selfRef == null)
      selfRef = new ProfileManagerButtonListener();
    return selfRef;
  }

  public void actionPerformed(ActionEvent e) {
    JButton src = (JButton) e.getSource();
    if (src == theView.getEditButton())
      this.editAction();
    else if (src == theView.getCancelButton())
      this.cancelAction();
    else if (src == theView.getDeleteButton())
      this.deleteAction();
    else if (src == theView.getCreateButton())
      this.createAction();
  }

  /**
   * Create a new NFO from a profile.
   */
  private void createAction() {
    Profile profile;
    String[] headerRow;
    String[] borderRow;
    String[] bodyRow;
    String[] bodyRowRaw;
    String row;
    String border;
    int margin     = 0;
    int totalWidth = 0;
    int bodyWidth;
    JTextArea textArea = NfoView.getInstance().getTextArea();
    ProfileCreationView creationView = ProfileCreationView.getInstance();
    int index = theView.getProfileList().getSelectedIndex();

    if (index != -1) {
      ProfileManager.getInstance().setVisible(false);
      profile = (Profile) theView.getProfileList().getItemAt(index);

      // Start to append text on text area.
      textArea.append(profile.getHeader());
      textArea.append("\n\n");
      borderRow = profile.getBorder().split("\n");

      headerRow = profile.getHeader().split("\n");
      // Determine the longest line in the header
      for (int i=0; i<headerRow.length; i++)
        if (headerRow[i].length() > totalWidth)
          totalWidth = headerRow[i].length();

      // Determine the longest line in the border pattern
      for (int i=0; i<borderRow.length; i++)
        if (borderRow[i].length() > margin)
          margin = borderRow[i].length();

      // TODO adapt if the border is only on one side
      bodyWidth = totalWidth - 2*margin - 2*BORDER_CONTENT_MARGIN;

      bodyRow = profile.getBody().split("\n");
      for (int i=0; i<bodyRow.length; i++) {
        //if ()
      }

      // TODO if bodyRow[i].length() > space left in tine it will fail
      for (int i=0; i<bodyRow.length; i++) {
        // TODO if the modulo is ≠ 0 it will print a non-complete border pattern, not cool bro !
        border = borderRow[i%borderRow.length];
        row = border;
        row = this.appendSpaces(row, margin - border.length() + BORDER_CONTENT_MARGIN);
        row += bodyRow[i]; // TODO
        row = this.appendSpaces(row, totalWidth - 2*margin - row.length());
        //row = this.appendSpaces(row, margin - border.length());
        row += border;
        row += "\n";
        textArea.append(row);
      }
      textArea.append("\n" + profile.getFooter());
    }
  }

  /**
   * Append spaces at the end of a string.
   *
   * @param string String to add spaces.
   * @param num Number of spaces to add.
   * @return The new formed String.
   */
  private String appendSpaces(String string, int num) {
    if (string == null)
      string = "";
    for (int i=0; i<num; i++)
      string += " ";
    return string;
  }

  /**
   * Cancel action, simply hide the profile manager frame after reseting the
   * list.
   */
  private void cancelAction() {
    theView.setVisible(false);
  }

  /**
   * Edit action
   */
  private void editAction() {
    ProfileCreationView creationView = ProfileCreationView.getInstance();
    int index = theView.getProfileList().getSelectedIndex();
    if (index != -1) {
      ProfileManager.getInstance().setVisible(false);
      Profile profile = (Profile) theView.getProfileList().getItemAt(index);
      creationView.getProfileNameField().setEditable(false);
      creationView.getProfileNameField().setText(profile.getProfileName());
      creationView.getHeaderArea().setText(profile.getHeader());
      creationView.getBodyArea().setText(profile.getBody());
      creationView.getBorderArea().setText(profile.getBorder());
      creationView.getFooterArea().setText(profile.getFooter());
      creationView.setVisible(true);
    }
  }

  /**
   * Delete action, remove the selected profile from the list.
   */
  private void deleteAction() {
    Profile profile;
    JComboBox jCombo = ProfileManager.getInstance().getProfileList();
    int index = jCombo.getSelectedIndex();
    if (index != -1) {
      profile = (Profile)jCombo.getItemAt(index);
      if (ProfileList.removeItem(profile) )
        jCombo.removeItemAt(index);
    }
  }
}
