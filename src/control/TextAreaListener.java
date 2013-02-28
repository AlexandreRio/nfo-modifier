package control;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data.RWFile;
import view.NfoView;

public class TextAreaListener implements DocumentListener {

   private NfoView theView;

   private static TextAreaListener selfRef;

   public static TextAreaListener getInstance() {
      if (selfRef == null)
	       selfRef = new TextAreaListener();
      return selfRef;
   }

   public TextAreaListener() {
      theView = NfoView.getInstance();
   }

   public void changedUpdate(DocumentEvent e) { }

   public void insertUpdate(DocumentEvent e) {
      this.textHasChanged();
   }

   public void removeUpdate(DocumentEvent e) {
      this.textHasChanged();
   }

   private void textHasChanged() {
      if (!RWFile.getIsModified())
	 theView.setTitle("*" + theView.getTitle());
      RWFile.setIsModified(true);
   }
}
