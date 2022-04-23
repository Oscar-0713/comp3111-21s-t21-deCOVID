package comp3111.covid.Utilities;

import javafx.application.Preloader.PreloaderNotification;

public class ProgressMessage implements PreloaderNotification{
	
	public static final ProgressMessage SUCESS = new ProgressMessage(true, false);
	public static final ProgressMessage FAIL = new ProgressMessage(false, true);
	private double progress;
	private String message;
	private boolean done;
	private boolean failed;
	
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
	
	public double getProgress() {
		return progress;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public boolean isFailed() {
		return failed;
	}
}
