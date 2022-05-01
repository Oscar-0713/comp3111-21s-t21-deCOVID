package comp3111.covid.Utilities;



import java.awt.Checkbox;

import org.assertj.core.annotations.NonNull;

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
	 * For table task reset button operation
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
	 * For chart task reset button operation
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
	
	/**
	 * Set the error label
	 * @param label of showing error message
	 * @param error ErrorType of input
	 */
	public static void setErrorLabelO(Label label,ErrorLabel error) {
		String text = "";
		switch(error) {
			case ERROR_INVALID_DATE:
			case ERROR_INVALID_RANGE:
				text = "Invalid date range!";
				break;
			case ERROR_NO_COUNTRY:
				text = "Please select a country!";
				break;
			case ERROR_MISSING_DATE:
				text = "Please pick a date!";
				break;
			case ERROR_TOO_MANY_COUNTRY:
				text = "Please pick only one country!";
				break;
		}
		label.setVisible(true);
		label.setTextFill(Color.RED);
		label.setText(text);
	}
}
