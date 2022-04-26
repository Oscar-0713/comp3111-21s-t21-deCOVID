package comp3111.covid;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class testGUI extends ApplicationTest {
	private Controller controller;
	@Override
	public void start(Stage stage) throws Exception{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		controller = loader.getController();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.show();
   		stage.toFront();
	}
	

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Test
	public void testButton() {
		 controller.onTaskC2ResetClicked(null);
		 controller.onTaskA1ResetClicked(null);
		 Platform.runLater(new Runnable() {

			@Override
			public void run() {
				 LocalDate date = LocalDate.of(2020, 9, 13);
				 controller.taskB1DatePicker.setValue(date);
				 for (CheckBox i : controller.taskB1DynamicListView.getItems()) {
					 i.setSelected(true);
				 }
				 controller.onTaskB1ConfirmClicked(null);
			}
			 
		 });
		 
		 Platform.runLater(new Runnable() {

			@Override
			public void run() {
				 controller.taskB1DatePicker.setValue(null);
				 for (CheckBox i : controller.taskB1DynamicListView.getItems()) {
					 i.setSelected(true);
				 }
				 controller.onTaskB1ConfirmClicked(null);
			}
			 
		 });
		 
		 Platform.runLater(new Runnable() {

			@Override
			public void run() {
				 LocalDate date = LocalDate.of(2020, 9, 13);
				 controller.taskB2DatePicker1.setValue(date);
				 date = LocalDate.of(2020, 9, 14);
				 controller.taskB2DatePicker2.setValue(date);
				 for (CheckBox i : controller.taskB2DynamicListView.getItems()) {
					 i.setSelected(true);
				 }
				 controller.onTaskB2ConfirmClicked(null);
			}
			 
		 });
	}

}
