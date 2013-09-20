package nfo.data;

import java.util.List;
import java.util.ArrayList;

/**
 * Representation of one command line interface. It specifie its name
 * ( including its shortcut alias ) and its number of option.
 *
 * @author Rio Alexandre
 * @version 1.0
 */
public class Argument {

  /** List of the alias of the argument. */
  private List<String> alias;

  /** Number of option expected for the argument. */
  private int numberOption;

  /**
   * Create a new Argument defined by its name and eventually a list of alias
   * and the number of expected option for this argument.
   *
   * @param name Full name of the argument, it usually begins with "--"
   * @param numberOption Number of option expected.
   * @param alias List of all the alias of the argument. An alias usually
   * begins with "-"
   */
  public Argument(String name, int numberOption, String... alias) {
    this.alias = new ArrayList<String>();

    if (name != null)
      this.alias.add(name);

    for (String argumentName : alias)
      this.alias.add(argumentName);
    this.numberOption = numberOption;
  }

  /**
   * Check if the string is a valid name or alias for this argument.
   *
   * @param name Value to test if it's a valid argument name.
   * @return True if this is a valid alias for this argument.
   */
  public boolean isAValidAlias(String name) {
    return this.alias.contains(name);
  }

  /**
   * Get the number of expected option for this Argument.
   *
   * @return Number of expected option for this Argument.
   */
  public int getNumberOption() {
    return this.numberOption;
  }
}
