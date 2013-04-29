package data;

import java.util.ArrayList;

import java.net.URL;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Handles a list of {@link data.Profile Profile} and read/write method.
 * @author Rio Alexandre
 * @version 1.0
 */
public abstract class ProfileList implements Serializable {

  private static final long serialVersionUID = 42L;

  /** List of all the saved Profile. */
  private static ArrayList<Profile> profileList = new ArrayList<Profile>();

  /** Location of the save file. */
  private static String saveFileLocation = null;

  /**
   * Creates a new instance of ProfileList, if its the first application
   * launch it creates an empty save file otherwise it loads the saved data.
   */
  public ProfileList() {
  }

  /**
   * Get the number profile stored in the list.
   * @return The number of profile stored in the list.
   */
  public int getProfileNumber() {
    return profileList.size();
  }

  /**
   * Get a particular element stored in the list.
   * @param index Index of the profile in the list.
   */
  public Profile getElement(int index) {
    Profile ret = null;
    //if (profileList.contains)
    return null;
  }

  /**
   * Check the list contains a profile with the given name.
   *
   * @param profileName Name of the profile to look for.
   * @return True if the profile name already exists in the list.
   */
  public static boolean contains(String profileName) {
    for (Profile profile : profileList) {
      if (profileName.equals(profile.getProfileName()) )
        return true;
    }
    return false;
  }

  public static boolean removeItem(Object item) {
    return profileList.remove(item);
  }

  /*
     public boolean checkFile() {
     URL url = getClass().getResource(this.saveFileLocation);
     System.out.println(url);
     File saveFile = null;
     boolean retValue = false;
     try {
     if ( url != null) {
     saveFile = new File(url.getFile());
     System.out.println("–––");
     System.out.println(saveFile.exists());
     System.out.println(saveFile.createNewFile());
     System.out.println(saveFile.exists());
     System.out.println("–––");
     }
     }
     catch (IOException e) {
     System.out.println("error");
     e.getMessage();
     }
//if (saveFile != null)
// retValue = saveFile.exists();
return retValue;
     }
     */
  /**
   * Creates a File instance from the absolute path of the save file located
   * in the jar archive.
   * @return File instance of the save file.
   */
  public static File getFile() {
    File retValue = null;
    if (saveFileLocation != null)
      if (retValue.exists())
        retValue = new File(saveFileLocation);
    // In case the file has been deleted
    return retValue;
  }

  /**
   * Set the path of the current profile file.
   * @param path Path of the save file.
   */
  public static void setFile(String path) {
    File tmpFile = new File(path);
    if (!tmpFile.exists())
      saveFileLocation = path;
  }

  /**
   * Loads the serialized profiles from the save file.
   * cause a warning when compiling the class because of the
   * cast.
   */
  public static void loadData() {
    try {
      File profileFile = getFile();
      if (profileFile != null) {
        FileInputStream fis	  = new FileInputStream(profileFile);
        ObjectInputStream ois = new ObjectInputStream(fis);

        profileList = (ArrayList<Profile>) ois.readObject();

        ois.close();
      }
    }
    catch (FileNotFoundException e) {
      System.err.println("Missing save file, something went wrong during"
          + " initialization.");
      e.printStackTrace();
    }
    catch (IOException e) {
      System.err.println("Something went wrong during the file reading.");
      e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
      System.err.println("Corrupted file.");
      e.printStackTrace();
    }
  }

  /**
   * Writes the profile list in a file.
   */
  public static void writeData() {
    try {
      FileOutputStream fos	 = new FileOutputStream(getFile());
      ObjectOutputStream oos	 = new ObjectOutputStream(fos);

      oos.writeObject(profileList);

      oos.close();
    }
    catch (IOException e) {
      System.err.println("Something went wrong during the file writing.");
      e.printStackTrace();
    }
  }
}
