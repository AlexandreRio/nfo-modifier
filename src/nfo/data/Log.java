package nfo.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Reports the program activity to the log files.
 *
 * @author IUT of Vannes - Student project
 * @author Alexandre Rio
 * @author Sebastien Le Corre
 * @author Julien Cadic
 * @version s1.1
 */
public class Log {

  private static int debug = 4;
  /**
   * Path to the error log file (plain text).
   */
  private static final String PATH_ERROR = "log" + File.separator + "error.log";

  /**
   * Path to the access log file (plain text).
   */
  private static final String PATH_ACCESS = "log" + File.separator + "access.log";

  /**
   * Format of the severe level.
   */
  public static final String SEVERE = "SEVERE";

  /**
   * Format of the warning level.
   */
  public static final String WARNING = "WARNING";

  /**
   * Format of the info level.
   */
  public static final String INFO = "INFO";

  /**
   * <p>Reports a log</p>
   *
   * <p>Log syntax: DATE [LEVEL] (TRACE) MESSAGE</p>
   *
   * <p>
   * <b>Usage:</b><br />
   *
   * <pre>
   * Log.report(Log.SEVERE, &quot;Fatal error&quot;);
   * </pre>
   *
   * Output example:<br />
   *
   * <i> 25/05/2012-8: 09:21 [SEVERE] (juanJoal.ihm.juanJoal :: hand: 10)
   * Fatal error </i>
   * </p>
   *
   * @param level Severity of the report (look at the attributes)
   * @param message The message to display.
   */
  public static synchronized void report(String level, String message) {
    if (authorized(level)) {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();

      String stackTrace = stack[2].getClassName() + "::"
        + stack[2].getMethodName() + ":"
        + stack[2].getLineNumber();

      String levelst = String.format(" %1$-10s", "[" + level + "]");

      String logOut = levelst + "(" + stackTrace + ")  " + message;
      Log.writeTextLog(level, logOut, PATH_ERROR);
    }
  }

  /**
   * Check if debug level is sufficient to see the message. Debug level is
   * defined in the Global interface.
   *
   * @param level The level to check
   *
   * @return if it is allowed
   */
  public static boolean authorized(String level) {
    boolean authorized = false;

    switch (debug) {
      case 1:
        if (level.equals(SEVERE))
          authorized = true;
        break;
      case 2:
        if (level.equals(SEVERE) || level.equals(WARNING))
          authorized = true;
        break;
      case 3:
        authorized = true;
        break;
      case 4:
        authorized = true;
        break;
      default:
        authorized = false;
    }

    return authorized;
  }

  /**
   * Report accesses
   *
   * @param user the nick of the current user
   * @param action the action the user performs
   * @param subject the subject the user is acting on
   */
  public static void access(String user, String action, String subject) {
    if (authorized(WARNING)) {
      user = String.format(" %1$-20s", user);
      String logOut = new String(user + " " + action + " " + subject);
      Log.writeTextLog(WARNING, logOut, PATH_ACCESS);
    }
  }
  /**
   * Write the log to the file
   *
   * @param log full log without date
   * @param path the output file path
   * @since 1.2
   */
  @SuppressWarnings("unused")
    private static void writeTextLog(String level, String log, String path) {
      FileWriter out = null;
      BufferedWriter flux = null;

      SimpleDateFormat formatDate = new SimpleDateFormat(
          "dd/MM/yyyy-HH:mm:ss");
      String currentDate = formatDate.format(new Date());

      log = currentDate + log;

      switch(level) {
        case SEVERE:
          System.out.println("\u001B[31m" + log + "\u001B[0m");
          System.err.println(log);
          break;
        case WARNING:
          System.out.println("\u001B[33m" + log + "\u001B[0m");
          break;
        case INFO:
          if(debug > 3)
            System.out.println("\u001B[32m" + log + "\u001B[0m");
          break;
        default:
          System.out.println("\u001B[34m" + log + "\u001B[0m");
      }

      try {
        out = new FileWriter(path, true);
        flux = new BufferedWriter(out);
        flux.write(log);
        flux.newLine();
        flux.flush();
      } catch (IOException ioe) {
        System.out.println(currentDate + " [SEVERE] "
            + "(nfo.data.Log::writeTextLog)" + ioe.getMessage());
      } finally {
        try {
          flux.close();
        } catch (IOException ioe) {
          System.out.println(currentDate + " [SEVERE] "
              + "(nfo.data.Log::writeTextLog)"
              + ioe.getMessage());
        } finally {
          try {
            out.close();
          } catch (IOException ioe) {
            System.out.println(currentDate + " [SEVERE] "
                + "(nfo.data.Log::writeTextLog)"
                + ioe.getMessage());
          }
        }
      }
    }
}
