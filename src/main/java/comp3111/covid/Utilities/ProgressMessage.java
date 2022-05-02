package comp3111.covid.Utilities;

import javafx.application.Preloader.PreloaderNotification;
/**
 * This class is for handling message exchange between main application and starting screen.
 * @author Oscar Tse
 *
 */
public class ProgressMessage implements PreloaderNotification{
	
	/**
	 * Public object to indicate that the main application is ready to start.
	 */
	public static final ProgressMessage SUCESS = new ProgressMessage(true, false);
	/**
	 * Public object to indicate that the main application failed to start
	 */
	public static final ProgressMessage FAIL = new ProgressMessage(false, true);
	private double progress;
	private String message;
	private boolean done;
	private boolean failed;
	
	/**
	 * Constructor for user to inform the progress
	 * @param progress progress of opening the application
	 * @param message message that you want to show in the starting screen
	 */
	public ProgressMessage(double progress, String message) {
		this.progress = progress;
		this.message = message;
		this.done = false;
		this.failed = false;
	}
	
	private ProgressMessage(boolean done, boolean failed) {
		this.done = done;
		this.failed = failed;
	}
	/**
	 * Get the current progress
	 * @return progress of the main application
	 */
	public double getProgress() {
		return progress;
	}
	/**
	 * Get the message
	 * @return the message to be shown on GUI
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Get whether the main application is ready to start
	 * @return true if ready, false otherwise
	 */
	public boolean isDone() {
		return done;
	}
	/**
	 * Get whether the main application failed to start
	 * @return true if failed, false otherwise
	 */
	public boolean isFailed() {
		return failed;
	}
}
