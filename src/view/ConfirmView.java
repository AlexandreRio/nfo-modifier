package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ConfirmView extends JFrame implements ActionListener {

   /** */
   private static final long serialVersionUID = 42L;

   /** */
   private JButton confirmButton;

   /** */
   private JButton cancelButton;

   /** */
   private JLabel textLabel;

   /** */
   private JPanel textPanel;

   /** */
   private JPanel buttonsPanel;

   /**
    *
    */
   public ConfirmView() {
      super("");
      this.createInterface();
      this.pack();
      this.setDefaultCloseOperation(HIDE_ON_CLOSE);
   }

   /**
    *
    */
   public void createInterface() {
      this.setLayout(new BorderLayout());
      textPanel = new JPanel();
      buttonsPanel = new JPanel();

      textLabel = new JLabel("<html><h3><b>Quit without saving ?</b></h3> <br />If" +
	    " you don't save," + " changes will be permanently lost. ?</html>");
      cancelButton = new JButton("Cancel");
      confirmButton = new JButton("Confirm");

      textPanel.add(textLabel);
      buttonsPanel.setLayout(new FlowLayout());
      buttonsPanel.add(confirmButton);
      buttonsPanel.add(cancelButton);
      this.add(textPanel, BorderLayout.CENTER);
      this.add(buttonsPanel, BorderLayout.SOUTH);

      confirmButton.addActionListener(this);
      cancelButton.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == confirmButton) {
	 System.out.println("confirm");
      }
      else if (e.getSource() == cancelButton) {
	 System.out.println("cancel");
      }
   }
}
