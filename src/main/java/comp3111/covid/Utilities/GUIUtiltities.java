package comp3111.covid.Utilities;



import java.awt.Checkbox;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

/**
 * This class is for the common utilities for Controller Handling.
 * It is produced by refactoring the code
 * @author Oscar Tse
 *
 */
public class GUIUtiltities {
	/**
	 * Set the warning of missing data to the screen
	 * @param label which shows the warning
	 */
	public static void setWarningMessage(Label label) {
		label.setText("Warning: One or more data entries from CSV is missing!");
		label.setTextFill(Color.RED);
		label.setVisible(true);
	}
	
	/**
	 * For table task reset.
	 * @param picker DatePicker for resetting to null
	 * @param list uncheck all checkboxes in the list
	 */
	public static void resetSelection(DatePicker picker, ListView<CheckBox> list) {
		picker.setValue(null);
		for (CheckBox i : list.getItems()) {
			i.setSelected(false);
		}
	}
	
	/**
	 * For chart task reset
	 * @param picker1 first datePicker
	 * @param picker2 second datePicker
	 * @param list The countryList to reset
	 */
	public static void resetSelection(DatePicker picker1,DatePicker picker2 ,ListView<CheckBox> list) {
		picker1.setValue(null);
		picker2.setValue(null);
		for (CheckBox i : list.getItems()) {
			i.setSelected(false);
		}
	}
}
