package view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.GridLayout;
import java.awt.Dimension;

public class ProfileManager extends JFrame {

   private static final long serialVersionUID = 42L;

   private static ProfileManager selfref;

   /** Main Panel of the frame. */
   private JPanel mainPanel;

   /** List of all the saved profile. */
   private JComboBox profileList;

   /** Panel of the bottom buttons. */
   private JPanel buttonPanel;

   /** Edit button in the button panel. */
   private JButton editButton;

   /** Delete button in the button panel. */
   private JButton deleteButton;

   /** Cancel button in the button panel. */
   private JButton cancelButton;

   /**
    * Create a new profile manager frame.
    */
   public ProfileManager() {
      super("profile manager");
      this.createInterface();
      this.attachReactions();

      ImageIcon img = new ImageIcon(getClass().getResource("/res/img/ico.png"));
      this.setIconImage(img.getImage());

      //this.pack();
      this.setSize(300,200);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }

   public static ProfileManager getInstance() {
      if (selfref == null)
	       selfref = new ProfileManager();
      return selfref;
   }

   /**
    * Create the graphical interface.
    */
   private void createInterface() {
      this.mainPanel = new JPanel();
      this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));


      profileList = new JComboBox();

      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(0,3));

      editButton = new JButton("Edit");
      deleteButton = new JButton("Delete");
      cancelButton = new JButton("Cancel");

      buttonPanel.add(editButton);
      buttonPanel.add(deleteButton);
      buttonPanel.add(cancelButton);

      this.profileList.setMaximumSize(new Dimension(250,35));
      this.buttonPanel.setMaximumSize(new Dimension(250,35));
      this.mainPanel.add(Box.createVerticalStrut(30));
      this.mainPanel.add(profileList, this.mainPanel.CENTER_ALIGNMENT);
      this.mainPanel.add(Box.createVerticalStrut(30));
      this.mainPanel.add(buttonPanel, this.mainPanel.CENTER_ALIGNMENT);
      this.add(mainPanel);
   }

   /**
    * Attach the reactions on the interface components.
    */
   private void attachReactions() {
   }
}
