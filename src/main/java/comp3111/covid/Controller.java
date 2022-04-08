package comp3111.covid;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.GUI.GUIShowHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.data.DeathDataAnalysis;
import comp3111.covid.data.DeathObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Building on the sample skeleton for 'ui.fxml' Controller Class generated by SceneBuilder 
 */
public class Controller {
	
	/**
	 * This function will be triggered once the controller is being initialized
	 * Run once only
	 */
	@FXML
	public void initialize() {
    	try {
			GUIShowHandler handler = new GUIShowHandler("COVID_Dataset_v1.0.csv");
			for (CountryCode code : handler.getAvailableCountry()) {
					CheckBox box = new CheckBox(code.getName());
					taskB1DynamicListView.getItems().add(box);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//This element will NOT hook to fxml
	private ObservableList<DeathObject> taskB1TableList;
	
	@FXML
	private TableView<DeathObject> taskB1Table;
	
	@FXML
	private TabPane globalTabPane;
	
	@FXML
	private ListView<CheckBox> taskB1DynamicListView;

	@FXML
	private DatePicker taskB1DatePicker;
	
    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldISO;

    @FXML
    private Button buttonConfirmedDeaths;

    @FXML
    private TextField textfieldDataset;

    @FXML
    private Button buttonRateOfVaccination;

    @FXML
    private Button buttonConfirmedCases;

    @FXML
    private Tab tabReport1;

    @FXML
    private Tab tabReport2;

    @FXML
    private Tab tabReport3;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextArea textAreaConsole;

  

    /**
     *  Task Zero
     *  To be triggered by the "Confirmed Cases" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doConfirmedCases(ActionEvent event) {
    	String iDataset = textfieldDataset.getText();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getConfirmedCases(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Confirmed Deaths" button on the Task Zero Tab
     *  
     */
    @FXML
    void doConfirmedDeaths(ActionEvent event) {
    	String iDataset = textfieldDataset.getText();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getConfirmedDeaths(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rate of Vaccination" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRateOfVaccination(ActionEvent event) {
    	String iDataset = textfieldDataset.getText();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getRateOfVaccination(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }  
    
    
    @FXML
    void taskB1OnLoadCountryList(MouseEvent event) {

    }
    
    @FXML
    void onGlobalEnter(ActionEvent event) {
    	
    }
     
    /**
     * Task B1: Reset button click event
     * Reset the checked country and date
     * @param event
     */
    @FXML
    void onTaskB1ResetClicked(ActionEvent event) {
    	taskB1DatePicker.getEditor().clear();
    	for (int i = 0; i < taskB1DynamicListView.getItems().size();i++) {
    		taskB1DynamicListView.getItems().get(i).setSelected(false);
    	}
    }
    
    /**
     * Task B1: Confirm button click event.
     * Confirm and generate table
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    void onTaskB1ConfirmClicked(ActionEvent event) {
    	//User doesn't pick a date
    	if (taskB1DatePicker.getValue() == null) {
    		return;
    	}
    	
    	//Handle the selected date
    	LocalDate localDate = taskB1DatePicker.getValue();
    	String formattedDates = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskB1DynamicListView.getItems().size(); i++) {
    		if (taskB1DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskB1DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		return;
    	}
    	
    	//Create handler
    	GUISelectTableHandler handler = new GUISelectTableHandler(selectedCountry, formattedDates);
    	DeathDataAnalysis analysis = new DeathDataAnalysis("COVID_Dataset_v1.0.csv", handler);
    	
    	//Handle output
    	
    	//Reset Table Column
    	TableColumn<DeathObject, String> country = new TableColumn<>("Country");
    	country.setCellValueFactory(new PropertyValueFactory<>("country"));
    	TableColumn<DeathObject, String> death = new TableColumn<>("Total Death");
    	death.setCellValueFactory(new PropertyValueFactory<>("death"));
    	TableColumn<DeathObject, String> deathPerMillion = new TableColumn<>("Total Death / 1M");
    	deathPerMillion.setCellValueFactory(new PropertyValueFactory<>("deathpermillion"));
    	taskB1Table.getColumns().setAll(country, death, deathPerMillion);
    	
    	//Reset Observable List
    	taskB1TableList = FXCollections.observableList(analysis.getResult());
    	taskB1Table.setItems(taskB1TableList);
    	//System.out.print(taskB1TableList.isEmpty());
    	
    }
    
}

