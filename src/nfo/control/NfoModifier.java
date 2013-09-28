package nfo.control;

import javax.swing.event.DocumentListener;

import nfo.view.NfoView;
import nfo.view.ProfileManagerView;
import nfo.view.ProfileCreationView;

import nfo.data.ArgumentParser;
import nfo.data.ProfileCreator;
import nfo.data.Settings;
import nfo.data.Output;

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
    ArgumentParser parser = ArgumentParser.getInstance();
    parser.setArguments(args);

    if(Settings.createGUI) {
      NfoModifier nfo = new NfoModifier();
    }

    if(Settings.profile != null)
      Output.printNFO(ProfileCreator.create(Settings.profile, Settings.content));

    if(! Settings.silent)
      Output.process();
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
    // Instanciate all the view
    NfoView view                        = NfoView.getInstance();
    ProfileManagerView profileManager   = ProfileManagerView.getInstance();
    ProfileCreationView profileCreation = ProfileCreationView.getInstance();

    // Instanciate all the listener
    MainViewItemListener mainViewItemListener = MainViewItemListener.getInstance();
    TextAreaListener textAreaListener         = TextAreaListener.getInstance();
    ProfileManagerButtonListener pmbl         = ProfileManagerButtonListener.getInstance();
    ProfileCreationButtonListener pcbl        = ProfileCreationButtonListener.getInstance();

    // Attach all the reaction
    view.getItemNew().addActionListener(mainViewItemListener);
    view.getItemSave().addActionListener(mainViewItemListener);
    view.getItemSaveAs().addActionListener(mainViewItemListener);
    view.getItemOpen().addActionListener(mainViewItemListener);
    view.getItemClear().addActionListener(mainViewItemListener);
    view.getItemQuit().addActionListener(mainViewItemListener);

    view.getItemCreateProfile().addActionListener(mainViewItemListener);
    view.getItemManage().addActionListener(mainViewItemListener);
    view.getItemSaveProfiles().addActionListener(mainViewItemListener);
    view.getItemLoadProfiles().addActionListener(mainViewItemListener);

    view.getItemHelp().addActionListener(mainViewItemListener);
    view.getItemAbout().addActionListener(mainViewItemListener);

    view.getTextArea().getDocument().addDocumentListener(textAreaListener);

    profileManager.getCreateButton().addActionListener(pmbl);
    profileManager.getEditButton().addActionListener(pmbl);
    profileManager.getDeleteButton().addActionListener(pmbl);
    profileManager.getCancelButton().addActionListener(pmbl);

    profileCreation.getValidateButton().addActionListener(pcbl);
    profileCreation.getCancelButton().addActionListener(pcbl);

    view.setVisible(true);
  }

}
