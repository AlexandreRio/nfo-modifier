import java.util.ArrayList;

/**
 * Exemple : read a nfo file, converts it in unicode(utf-8) then
 * writes it as a new nfo file, encoded in cp437.
 * @author Rio Alexandre
 * @version 0.1
 */
public class Main {

   public static void main (String[] args) {
      ArrayList<String> list = RWFile.readNfoFile("res/nfo/exemple.nfo");
      try {
	 RWFile.writeNfoFile(list, "res/out/sortie.nfo");
      }
      catch (Exception e) {
	 System.err.println("Unsuported char !");
      }
   }
}
