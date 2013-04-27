package control;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import control.MainViewItemListener;

public class CloseWindowListener implements WindowListener {

  @Override
  public void windowActivated(WindowEvent e) { ; }

  @Override
  public void windowClosed(WindowEvent e) { ; }

  @Override
  public void windowClosing(WindowEvent e) {
    MainViewItemListener mainListener = new MainViewItemListener();
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
