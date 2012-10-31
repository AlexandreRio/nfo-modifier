package control;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import data.RWFile;
import view.NfoView;

public class TextAreaListener implements DocumentListener {

   private NfoView theView;

   public TextAreaListener(NfoView view) {
      theView = view;
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
