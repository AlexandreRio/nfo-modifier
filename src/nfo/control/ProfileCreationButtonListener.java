package nfo.control;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import nfo.view.ProfileCreationView;

import nfo.data.ProfileList;
import nfo.data.Profile;

/**
 * Handle profile creation button reaction.
 * @author Rio Alexandre
 * @version 0.1
 */
public class ProfileCreationButtonListener implements ActionListener {

  /** Frame containing the profile creation component. */
  private ProfileCreationView theView;

  private static ProfileCreationButtonListener selfRef;

  /** Text displayed if the profile name is already used by a different profile. */
  private static String NAME_TAKEN_MESSAGE = "<html><h2>Warning</h2>This name" +
    " is already used by another profile, please use a different one.</html>";

  /**
   * Default constructor.
   */
  public ProfileCreationButtonListener() {
    this.theView = ProfileCreationView.getInstance();
  }

  /**
   * Get the instance of the listener, if it doesn't exist a new instance is
   * created.
   * @return The instance of the ProfileCreationButtonListener.
   */
  public static ProfileCreationButtonListener getInstance() {
    if ( selfRef == null)
      selfRef = new ProfileCreationButtonListener();
    return selfRef;
  }

  public void actionPerformed(ActionEvent e) {
    JButton src = (JButton) e.getSource();
    if (src == theView.getValidateButton())
      this.validateAction();
    else if (src == theView.getCancelButton())
      this.cancelAction();
  }

  private void validateAction() {
    String name   = theView.getProfileNameField().getText();
    String header = theView.getHeaderArea().getText();
    String body   = theView.getBodyArea().getText();
    String border = theView.getBorderArea().getText();
    String footer = theView.getFooterArea().getText();
    if (ProfileList.contains(name) != -1 && theView.getProfileNameField().
        isEditable()) {
      JOptionPane.showMessageDialog(theView, NAME_TAKEN_MESSAGE);
    }
    else {
      //System.out.println(name + header + body + border + footer);
      Profile profile = new Profile(name, header, body, border, footer);

      if (theView.getProfileNameField().isEditable())
        ProfileList.add(profile);
      else
        ProfileList.update(profile);

      theView.getProfileNameField().setText("");
      theView.getHeaderArea().setText("");
      theView.getBodyArea().setText("");
      theView.getBorderArea().setText("");
      theView.getFooterArea().setText("");
      theView.setVisible(false);
    }
  }

  private void cancelAction() {
    theView.setVisible(false);
    theView.getProfileNameField().setText("");
    theView.getHeaderArea().setText("");
    theView.getBodyArea().setText("");
    theView.getBorderArea().setText("");
    theView.getFooterArea().setText("");
  }
}
