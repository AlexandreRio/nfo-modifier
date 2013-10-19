package nfo.control;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import nfo.control.MainViewItemListener;

public class CloseWindowListener implements WindowListener {

  private static CloseWindowListener selfRef;

  /**
   * Get the instance of the listener, if it doesn't exist a new instance is
   * created.
   * @return The instance of the CloseWindowListener.
   */
  public static CloseWindowListener getInstance() {
    if ( selfRef == null)
      selfRef = new CloseWindowListener();
    return selfRef;
  }

  @Override
  public void windowActivated(WindowEvent e) { ; }

  @Override
  public void windowClosed(WindowEvent e) { ; }

  @Override
  public void windowClosing(WindowEvent e) {
    MainViewItemListener mainListener = MainViewItemListener.getInstance();
    mainListener.quitAction();
  }

  @Override
  public void windowDeactivated(WindowEvent e) { ; }

  @Override
  public void windowDeiconified(WindowEvent e) { ; }

  @Override
  public void windowIconified(WindowEvent e) { ; }

  @Override
  public void windowOpened(WindowEvent e) { ; }

}
