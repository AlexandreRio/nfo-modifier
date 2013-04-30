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
   * @return the element at the specified position in this list
   */
  @Deprecated
    public Profile getElement(int index) {
      return profileList.get(index);
    }

  /**
   * Add a new Profile to the list.
   *
   * @param profile The Profile to add.
   */
  public static void add(Profile profile) {
    profileList.add(profile);
  }

  /**
   * Update the Profile in the list.
   *
   * @param profile Profile to update.
   */
  public static void update(Profile profile) {
    int index = contains(profile.getProfileName());
    if (index != -1)
      profileList.set(index, profile);
  }

  /**
   * Get the profile list.
   *
   * @return The profile list as an ArrayList of Profile.
   */
  public static ArrayList<Profile> getElements() {
    return profileList;
  }

  /**
   * Check the list contains a profile with the given name.
   *
   * @param profileName Name of the profile to look for.
   * @return The index of the profile in the list, -1 if it's not in it.
   */
  public static int contains(String profileName) {
    for (Profile profile : profileList) {
      if (profileName.equals(profile.getProfileName()) )
        return profileList.indexOf(profile);
    }
    return -1;
  }

  public static boolean removeItem(Object item) {
    return profileList.remove(item);
  }

  /**
   * Creates a File instance from the absolute path of the save file located
   * in the jar archive.
   * @return File instance of the save file.
   */
  public static File getFile() {
    File retValue = null;
    if (saveFileLocation != null) {
      retValue = new File(saveFileLocation);
    }
    return retValue;
  }

  /**
   * Set the path of the current profile file.
   * @param path Path of the save file.
   */
  public static void setFile(String path) {
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
