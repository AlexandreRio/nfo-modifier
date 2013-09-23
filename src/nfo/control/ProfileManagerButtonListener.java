package nfo.control;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import nfo.view.NfoView;
import nfo.view.ProfileManagerView;
import nfo.view.ProfileCreationView;

import nfo.data.Profile;
import nfo.data.ProfileList;
import nfo.data.ProfileCreator;

/**
 * Handle profile manager button reactions
 *
 * @author Rio Alexandre
 * @version 0.1
 */
public class ProfileManagerButtonListener implements ActionListener {

  /** Frame containing the profile manager component. */
  private ProfileManagerView theView;

  private static ProfileManagerButtonListener selfRef;

  /**
   * Default constructor.
   */
  public ProfileManagerButtonListener() {
    this.theView = ProfileManagerView.getInstance();
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

  @Override
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
    JTextArea textArea = NfoView.getInstance().getTextArea();
    ProfileCreationView creationView = ProfileCreationView.getInstance();
    int index = theView.getProfileList().getSelectedIndex();

    if (index != -1) {
      ProfileManagerView.getInstance().setVisible(false);
      profile = (Profile) theView.getProfileList().getItemAt(index);

      textArea.append(ProfileCreator.create(profile));
    }
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
      ProfileManagerView.getInstance().setVisible(false);
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
    JComboBox jCombo = ProfileManagerView.getInstance().getProfileList();
    int index = jCombo.getSelectedIndex();
    if (index != -1) {
      profile = (Profile)jCombo.getItemAt(index);
      if (ProfileList.removeItem(profile) )
        jCombo.removeItemAt(index);
    }
  }
}
