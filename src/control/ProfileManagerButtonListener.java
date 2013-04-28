package control;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.ProfileManager;
import view.ProfileCreationView;

import data.Profile;
import data.ProfileList;

/**
 * Handle profile manager button reaction
 * @author Rio Alexandre
 * @version 0.1
 */
public class ProfileManagerButtonListener implements ActionListener {

  /** Frame containing the profile manager component. */
  private ProfileManager theView;

  private static ProfileManagerButtonListener selfRef;

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
    ProfileManager.getInstance().setVisible(false);
    //TODO fill main text editor area with template.
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
    System.out.println("edit action");
    ProfileManager.getInstance().setVisible(false);
    ProfileCreationView.getInstance().setVisible(true);
    ProfileCreationView.getInstance().getProfileNameField().setEditable(false);
    //TODO: fill the profile creation form
  }

  /**
   * Delete action, remove the selected profile from the list.
   */
  private void deleteAction() {
    System.out.println("delete action");
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
