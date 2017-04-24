package net.sourceforge.jfilecrypt;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class starts the whole program. It contains the basic settings which are
 * important for every class (e.g. language setting). It creates one instance of @see
 * Model and one instance of a
 * 
 * @see Controller which continues the program by processing the command line
 *      arguments or starting a GUI.
 */
public class Application {
	// Information about the application
	// They become extended and localized in the main() method
	// TODO: Update this information before every release or commit!
	public static String NAME = "jFileCrypt";
	public static String VERSION = "0.3.3";
	public static String RELEASE_DATE = "24. April 2017";

	// These codes are used when application exits with an error
	public static final byte EXIT_UNKNOWN_ALGORITHM = 10;
	public static final byte EXIT_PROCESSING_CMD_LINE_FAILED = 11;
	public static final byte EXIT_WRONG_CMP_LEVEL = 12;
	public static final byte EXIT_WRONG_KEY_LENGTH = 13;
	public static final byte EXIT_WRONG_RELEASE_DATE_FORMAT = 14;

	private static ResourceBundle bundle;
	private static Controller controller;

	/**
	 * All classes are expected to receive the ResourceBundle through this
	 * application class!
	 */
	public static ResourceBundle getResourceBundle() {
		return bundle;
	}

	/**
	 * All classes are expected to receive the Controller through this application
	 * class!
	 */
	public static Controller getController() {
		return controller;
	}

	/**
	 * The Controller who got called in Application's "Main" method should
	 * register here.
	 * 
	 * @return
	 */

	public static void setController(Controller contr) {
		controller = contr;
	}

	/**
	 * This method starts the application. It finds out the language, creates a
	 * new Model and starts a Controller. It depends on the given command line
	 * arguments which Controller is started, by default (no arguments) it is the
	 * GuiMainController.
	 */
	public static void main(String[] args) {
		try {
			bundle = ResourceBundle.getBundle(
			    "net/sourceforge/jfilecrypt/translations/jfilecrypt",
			    Locale.getDefault());
		} catch (MissingResourceException resEx) {
			bundle = ResourceBundle.getBundle(
			    "net/sourceforge/jfilecrypt/translations/jfilecrypt", Locale.ENGLISH);
		}

		// there should be only one Model for all controllers
		Model model = new Model();

		/*
		 * If no arguments available then just start the GUI. If there are some
		 * arguments then start the command line controller (which can start a GUI
		 * by himself).
		 */
		if (args.length <= 0) {
			new GuiMainController(model);
		} else {
			new CommandLineController(model, args);
		}
	}

	public static String getVersionString() {
		return NAME + " " + VERSION + " (" + Application.RELEASE_DATE + ")";
	}
}
