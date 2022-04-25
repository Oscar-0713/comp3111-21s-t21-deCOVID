package comp3111.covid;

import comp3111.covid.Utilities.ProgressMessage;
import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreLoader extends Preloader{
	
	private Stage preLoaderStage;
	private Scene scene;
	private static final String SPLASH_UI_FILE = "/SplashScreen.fxml";
	
	@FXML
	private Label loadingLabel;
	
	/**
	 * Constructor
	 */
	public MyPreLoader() {
		
	}
	
	@Override
	public void init() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(SPLASH_UI_FILE));
		loader.setController(this);
		Parent root1 = loader.load();
		scene = new Scene(root1);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.preLoaderStage = primaryStage;
		
		//Set preloader scene and show stages
		preLoaderStage.setScene(scene);
		preLoaderStage.initStyle(StageStyle.UNDECORATED);
		preLoaderStage.show();
	}

	@Override
	public void handleApplicationNotification(Preloader.PreloaderNotification info) {

		if (info instanceof ProgressMessage) {
			if (((ProgressMessage) info).isDone()) {
				preLoaderStage.hide();
			}
			if (((ProgressMessage) info).isFailed()) {
				preLoaderStage.hide();
			}
			if (((ProgressMessage) info).getMessage() != null) {
				loadingLabel.setText(((ProgressMessage) info).getMessage());
			}
		}
	}
	
	@Override
	public void handleStateChangeNotification(Preloader.StateChangeNotification info) {

	}
}
