package comp3111.covid;



import comp3111.covid.Utilities.DataFetcher;
import comp3111.covid.Utilities.ProgressMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/** 
 * 
 * The entry point of the entire program. It is quite unlikely you need to change this file. But you can change it if you want :)
 * 
 * GUI
 * ---
 * 
 * If you are aiming at some very basic features, you might not even need to edit the GUI. But you are always encouraged to understand the
 * entire package and you are always allowed to change the base code to suit what you need. 
 * 
 * Your GUI is described in the file ui.fxml which is located in src/main/java/ui.fxml . To edit your GUI go straight to edit the ui.fxml.
 * I generate this ui.fxml by javafx builder (http://gluonhq.com/products/scene-builder/). You can learn how to use it with tips from 
 * this youtube video for a real fast bootstrap (https://www.youtube.com/watch?v=Z1W4E2d4Yxo). Or, alternatively try to understand the ui.fxml
 * and edit it by trial and error with Google! 
 *
 * Entry Point
 * -----------
 * 
 * The program will start with the function public static void main and it will eventually trigger the function public void start. What this
 * function will do is to load this ui.fxml into a GUI application. The ui.fxml has specified that "all event will be handled by the class - 
 * Controller". Let's trace the function void start which will be called by the framework shortly after the program start.  
 *
 * 
 */
public class MyApplication extends Application {
	private Thread progressThread = new Thread() {
		public void run() {
			for (int i = 0; i <COUNT_LIMIT;i++) {
				if (isDownlaoded) {
					return;
				}
				double progress = (100*i) / COUNT_LIMIT;
				notifyPreloader(new ProgressMessage(progress, "Finalizing data...\t" + Double.toString(progress) + "%"));
			}
		}
	};
	private static boolean isDownlaoded = false;
    private static final String UI_FILE = "/ui.fxml";  //file in the folder of src/main/resources/
	private static final String CSS_SCENE_FILE = "/custom_theme.css"; //file in the folder of src/main/resources/
	private static final int COUNT_LIMIT = 1000000;
	/**
	 * This is the initiation method for the whole application
	 */
	public void init() throws Exception {
		//notifyPreloader(new ProgressMessage(0.0, "Fetching newest data from Internet...."));
		//Try download new data
		try {
			progressThread.start();
			DataFetcher.downloadData();
			isDownlaoded = true;
			notifyPreloader(new ProgressMessage(100, "Finalizing data...\t100%"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}
	
	/** 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * 
	 * This function will be called by the framework shortly after the program started. You are not required to change any line of code below.
	 */
	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource(UI_FILE));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		scene.getStylesheets().add(CSS_SCENE_FILE);
   		stage.setScene(scene);
   		notifyPreloader(ProgressMessage.SUCESS);
   		stage.setTitle("Mirror T-21: Data Explorer on COVID-19 (Desmond Task A) Trivia PR");
   		stage.show();
	}

	/**
	 * Entry point of the program. No argument should be supplied
	 * @param args - not used.
	 */
	public static void main(String args[]) {
		System.setProperty("javafx.preloader",MyPreLoader.class.getCanonicalName());
		launch(args);
	}


}
