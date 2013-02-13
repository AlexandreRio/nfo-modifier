import view.NfoView;

import control.CreationEvent;

/**
 * @author Rio Alexandre
 * @version 0.1
 */
public class Main {

   /**
    * Launch the application, if a file is given in parameter it displays it.
    * @param args One file path can be given.
    */
   public static void main (String[] args) {
      NfoView nfoView = new NfoView();

      if ( args.length == 1)
	 CreationEvent.openSpecifiedFileCreation(args[0], nfoView);
   }
}
