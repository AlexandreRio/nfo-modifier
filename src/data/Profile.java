package data;

import java.io.Serializable;

public class Profile implements Serializable {

   private static final long serialVersionUID = 42L;

   /**
    * Name of the profile, it should be unique (to be easily displayed by the
    * {@link data.ProfileList ProfileList}) but that's not necessary.
    */
   private String profileName;

   /** Banner of the file. */
   private String header;
   /** Border of the file, the number of line is important. */
   private String border;
   /** Footer of the file. */
   private String footer;

   /**
    * Create a profile
    */
   public Profile (String header, String border, String footer) {
      this.header = header;
      this.border = border;
      this.footer = footer;
   }


   /**
    * Get {@link #header header}.
    *
    * @return header as String.
    */
   public String getHeader() {
      return header;
   }

   /**
    * Set {@link #header header}.
    *
    * @param header the value to set.
    */
   public void setHeader(String header) {
      this.header = header;
   }

   /**
    * Get {@link #border border}.
    *
    * @return border as String.
    */
   public String getBorder() {
      return border;
   }

   /**
    * Set {@link #border border}.
    *
    * @param border the value to set.
    */
   public void setBorder(String border) {
      this.border = border;
   }

   /**
    * Get {@link #footer footer}.
    *
    * @return footer as String.
    */
   public String getFooter() {
      return footer;
   }

   /**
    * Set {@link #footer footer}.
    *
    * @param footer the value to set.
    */
   public void setFooter(String footer) {
      this.footer = footer;
   }
}
