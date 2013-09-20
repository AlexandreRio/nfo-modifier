package nfo.data;

import java.io.Serializable;

public class Profile implements Serializable {

  private static final long serialVersionUID = 42L;

  /**
   * Name of the profile, it should be unique (to be easily displayed by the
   * {@link nfo.data.ProfileList ProfileList}) but that's not necessary.
   */
  private String profileName;

  /** Banner of the file. */
  private String header;
  /** Body of the file. */
  private String body;
  /** Border of the file, the number of line is important. */
  private String border;
  /** Footer of the file. */
  private String footer;

  /**
   * Create a profile
   *
   * @param name Name of the profile.
   * @param header Header of the file.
   * @param body Body of the file.
   * @param border Border of the file.
   * @param footer Footer of the file.
   */
  public Profile (String name, String header, String body, String border, String footer) {
    this.profileName = name;
    this.header      = header;
    this.body        = body;
    this.border      = border;
    this.footer      = footer;
  }

  @Override
  public String toString() {
    return this.profileName;
  }

  /**
   * Get profileName.
   *
   * @return profileName as String.
   */
  public String getProfileName()
  {
    return profileName;
  }

  /**
   * Get body.
   *
   * @return body as String.
   */
  public String getBody()
  {
    return body;
  }

  /**
   * Get header.
   *
   * @return header as String.
   */
  public String getHeader()
  {
    return header;
  }

  /**
   * Get border.
   *
   * @return border as String.
   */
  public String getBorder()
  {
    return border;
  }

  /**
   * Get footer.
   *
   * @return footer as String.
   */
  public String getFooter()
  {
    return footer;
  }
}
