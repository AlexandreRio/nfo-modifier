package view;

import javax.swing.JFrame;

public class ProfileCreationView extends JFrame {

  private static final long serialVersionUID = 42L;

  private static ProfileCreationView selfref;

  public ProfileCreationView()
  {
    super("Nfo-modifier : profile creation");
    this.createInterface();

    this.setSize(400,300);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public static ProfileCreationView getInstance()
  {
    if (selfref == null)
      selfref = new ProfileCreationView();
    return selfref;
  }

  private void createInterface() {
  }

}
