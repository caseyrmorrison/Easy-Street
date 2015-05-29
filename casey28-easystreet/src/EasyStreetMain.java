/*
 * TCSS 305 - Homework 2: Easy Street Author: Marty Stepp Author: Daniel M.
 * Zimmerman Author: Alan Fowler
 */

/**
 * Runs the Easy Street program.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version Spring 2013
 */

public final class EasyStreetMain {
  /**
   * Private constructor to prevent construction of instances.
   */
  private EasyStreetMain() {
    // do nothing
  }

  /**
   * Constructs the main GUI window frame.
   * 
   * @param the_args Command line arguments (ignored).
   */
  public static void main(final String... the_args) {
    // initialize GUI frame
    final EasyStreetGUI gui = new EasyStreetGUI();
    gui.setVisible(true);
  }
}

// end of class EasyStreetMain
