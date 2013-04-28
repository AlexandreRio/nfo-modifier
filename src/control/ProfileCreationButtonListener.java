package control;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.ProfileCreationView;

/**
 * Handle profile creation button reaction.
 * @author Rio Alexandre
 * @version 0.1
 */
public class ProfileCreationButtonListener implements ActionListener {

  /** Frame containing the profile creation component. */
  private ProfileCreationView theView;

  private static ProfileCreationButtonListener selfRef;

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
    theView.setVisible(false);
    System.out.println(name + header + body + border + footer);
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
