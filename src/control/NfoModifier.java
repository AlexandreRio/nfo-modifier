package control;

import javax.swing.event.DocumentListener;

import view.NfoView;
import view.ProfileManager;

/**
 * Main controller of the application, create the view, the controller and
 * attach the reaction.
 * @author Rio Alexandre
 * @version 0.1
 */
public class NfoModifier {

   /**
    * Launch the application in graphical mode.
    * @param args If it contains a filename ( and only one ) the file is displayed.
    */
   public static void main (String[] args) {
      NfoModifier nfo = new NfoModifier();

      if ( args.length == 1)
	 CreationEvent.openSpecifiedFileCreation(args[0]);
   }

   /**
    * Simple constuctor, just call {@link #createInterface}.
    */
   public NfoModifier() {
      this.createInterface();
   }

   /**
    * Create the instance of all the view and listener and attach the reaction.
    */
   public void createInterface() {
      MainViewItemListener mainViewItemListener = MainViewItemListener.getInstance();
      TextAreaListener textAreaListener = TextAreaListener.getInstance();

      NfoView view = NfoView.getInstance();
      view.getItemNew().addActionListener(mainViewItemListener);
      view.getItemSave().addActionListener(mainViewItemListener);
      view.getItemSaveAs().addActionListener(mainViewItemListener);
      view.getItemOpen().addActionListener(mainViewItemListener);
      view.getItemClear().addActionListener(mainViewItemListener);
      view.getItemQuit().addActionListener(mainViewItemListener);

      view.getItemCreateFromProfile().addActionListener(mainViewItemListener);
      view.getItemManage().addActionListener(mainViewItemListener);
      view.getItemSaveProfiles().addActionListener(mainViewItemListener);
      view.getItemLoadProfiles().addActionListener(mainViewItemListener);

      view.getItemHelp().addActionListener(mainViewItemListener);
      view.getItemAbout().addActionListener(mainViewItemListener);

      view.getTextArea().getDocument().addDocumentListener(textAreaListener);

      ProfileManagerButtonListener pmbl = ProfileManagerButtonListener.getInstance();

      ProfileManager profileManager = ProfileManager.getInstance();
      profileManager.getCreateButton().addActionListener(pmbl);
      profileManager.getEditButton().addActionListener(pmbl);
      profileManager.getDeleteButton().addActionListener(pmbl);
      profileManager.getCancelButton().addActionListener(pmbl);

      view.setVisible(true);
   }

}
