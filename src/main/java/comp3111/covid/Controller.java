package comp3111.covid;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import comp3111.covid.GUI.GUIPreventSelection;
import comp3111.covid.GUI.GUISelectTableHandler;
import comp3111.covid.GUI.GUISelectChartHandler;
import comp3111.covid.GUI.GUIShowHandler;
import comp3111.covid.Utilities.CountryCode;
import comp3111.covid.Utilities.DataFetcher;
import comp3111.covid.Utilities.DateUtilities;
import comp3111.covid.data.CaseDataAnalysis;
import comp3111.covid.data.CaseObject;
import comp3111.covid.data.DataCache;
import comp3111.covid.data.DayDataObject;
import comp3111.covid.data.DeathDataAnalysis;
import comp3111.covid.data.DeathObject;
import comp3111.covid.data.VaccineAnalysis;
import comp3111.covid.data.VaccineObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent; // not sure if this needed, it just show up in skeleton from scene builder, to be confirmed
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

/**
 * Building on the sample skeleton for 'ui.fxml' Controller Class generated by SceneBuilder 
 */
public class Controller {
	//Handler class
	private static HashMap<String, GUIShowHandler> handlerList = new HashMap<>();
	private static String defaultDataset;
	private static GUIShowHandler handler;
	
	
	/**
	 * This function will be triggered once the controller is being initialized
	 * Run once only
	 */
	@FXML
	public void initialize() {

		//Master
		taskB2Chart.setVisible(false);
		taskA2Chart.setVisible(false);
		taskC2Chart.setVisible(false);

    //Task B
// 		try {
// 			DataCache.getCache().initalizeData("COVID_Dataset_v1.0.csv");
// 		} catch (ParseException e1) {
// 			e1.printStackTrace();
// 		}
// 		taskB1Table.setVisible(false);
// 		taskA1Table.setVisible(false);
// 		taskC1Table.setVisible(false);
		
// 		taskB2Chart.setVisible(false);
// 		taskA2Chart.setVisible(false);
// 		taskC2Chart.setVisible(false);
		
// 		textfieldDataset.setEditable(true);
    //End of conflict
		//Try download new data
		try {
			DataFetcher.downloadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		//Fetch all available files from dataset directory, then initialize choicebox, GUIShowHandlers, DataCache and defaultDataset
		try {
	    	//Add choicebox for dataset
	    	File[] files = new File(new File("").getAbsolutePath() + "/src/main/resources/dataset").listFiles();
	    	//If this pathname does not denote a directory, then listFiles() returns null. 
	
	    	for (File file : files) {
	    	    if (file.isFile()) {
	    	    	String filename = file.getName();
	    	    	choicefieldDataset.getItems().add(filename);
	    			DataCache.getCache().initalizeData(filename);
	    			GUIShowHandler curHand = new GUIShowHandler(filename);
	    			handlerList.put(filename, curHand);
	    			//System.out.println(filename);
	    	    }
	    	}
	    	
	    	defaultDataset = "COVID_Dataset_v1.0.csv";
	    	choicefieldDataset.setValue("COVID_Dataset_v1.0.csv");
	    	handler = handlerList.get(defaultDataset);
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		
		//Initialize tasks with default datset
		try {
			
			for (CountryCode code : handler.getAvailableCountry()) {
					CheckBox box1 = new CheckBox(code.getName());
					CheckBox box2 = new CheckBox(code.getName());
					CheckBox box3 = new CheckBox(code.getName());
					taskB1DynamicListView.getItems().add(box1);
					taskA1DynamicListView.getItems().add(box2);
					taskC1DynamicListView.getItems().add(box3);
					
					CheckBox box4 = new CheckBox(code.getName());
					CheckBox box5 = new CheckBox(code.getName());
					CheckBox box6 = new CheckBox(code.getName());
					taskB2DynamicListView.getItems().add(box4);
					taskA2DynamicListView.getItems().add(box5);
					taskC2DynamicListView.getItems().add(box6);
			}
			taskB1ErrorLabel.setVisible(false);
			taskA1ErrorLabel.setVisible(false);
			taskC1ErrorLabel.setVisible(false);

		} catch (Exception e) {

			e.printStackTrace();
		}
    	
    	taskB1DynamicListView.setSelectionModel(new GUIPreventSelection<>());
    	taskC1DynamicListView.setSelectionModel(new GUIPreventSelection<>());
    	taskA1DynamicListView.setSelectionModel(new GUIPreventSelection<>());
    	
    	taskB2DynamicListView.setSelectionModel(new GUIPreventSelection<>());
    	taskC2DynamicListView.setSelectionModel(new GUIPreventSelection<>());
    	taskA2DynamicListView.setSelectionModel(new GUIPreventSelection<>());
	}
	
	//This element will NOT hook to fxml
	private ObservableList<DeathObject> taskB1TableList;
	private ObservableList<CaseObject> taskA1TableList;
	private ObservableList<VaccineObject> taskC1TableList;
	
	@FXML
	private DatePicker taskC2DatePicker1;
	
	@FXML
	private DatePicker taskC2DatePicker2;
	
	@FXML
	private ListView<CheckBox> taskC2DynamicListView;
	
	@FXML
	private LineChart<String, Number> taskC2Chart;
	
	@FXML
	private Label taskC2ErrorLabel;
	
	@FXML
	private DatePicker taskB2DatePicker1;
	
	@FXML
	private DatePicker taskB2DatePicker2;
	
	@FXML
	private ListView<CheckBox> taskB2DynamicListView;
	
	@FXML
	private LineChart<String, Number> taskB2Chart;
	
	@FXML
	private Label taskB2ErrorLabel;
	
	@FXML
	private DatePicker taskA2DatePicker1;
	
	@FXML
	private DatePicker taskA2DatePicker2;
	
	@FXML
	private ListView<CheckBox> taskA2DynamicListView;
	
	@FXML
	private LineChart<String, Number> taskA2Chart;
	
	@FXML
	private Label taskA2ErrorLabel;
	
	@FXML
	private DatePicker taskC1DatePicker;
	
	@FXML
	private ListView<CheckBox> taskC1DynamicListView;
	
	@FXML
	private TableView<VaccineObject> taskC1Table;
	
	@FXML
	private Label taskC1ErrorLabel;
	
	@FXML
	private DatePicker taskA1DatePicker;
	
	@FXML
	private Label taskA1ErrorLabel;
	
	@FXML
	private ListView<CheckBox> taskA1DynamicListView;
	
	@FXML
	private TableView<CaseObject> taskA1Table;
	
	@FXML
	private Label taskB1ErrorLabel;
	
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
    private Button buttonRateOfVaccination;

    @FXML
    private Button buttonConfirmedCases;
    
    @FXML
    private Button buttonSwitchData;
    
    @FXML
    private ChoiceBox<String> choicefieldDataset;

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

  
    @FXML
    void doSwitchData(ActionEvent event) {
    	
    	try {
	    	defaultDataset = choicefieldDataset.getValue();
	    	handler = handlerList.get(defaultDataset);
	    	taskB1DynamicListView.getItems().clear();
	    	taskA1DynamicListView.getItems().clear();
	    	taskC1DynamicListView.getItems().clear();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	    	
	    	
    	try {
			
			for (CountryCode code : handler.getAvailableCountry()) {
					CheckBox box1 = new CheckBox(code.getName());
					CheckBox box2 = new CheckBox(code.getName());
					CheckBox box3 = new CheckBox(code.getName());
					taskB1DynamicListView.getItems().add(box1);
					taskA1DynamicListView.getItems().add(box2);
					taskC1DynamicListView.getItems().add(box3);
			}
			taskB1ErrorLabel.setVisible(false);
			taskA1ErrorLabel.setVisible(false);
			taskC1ErrorLabel.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     *  Task Zero
     *  To be triggered by the "Confirmed Cases" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doConfirmedCases(ActionEvent event) {
    	String iDataset = choicefieldDataset.getValue();
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
    	String iDataset = choicefieldDataset.getValue();
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
    	String iDataset = choicefieldDataset.getValue();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getRateOfVaccination(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }  
    
    
    @FXML
    void taskB1OnLoadCountryList(ActionEvent event) {

    }
    
    @FXML
    void onGlobalEnter(ActionEvent event) {
    	
    }
    /**
     * Task A1: Reset button click event
     * @param event
     */
    @FXML
    void onTaskA1ResetClicked(ActionEvent event) {
    	taskA1DatePicker.setValue(null);
    	for (int i = 0; i < taskA1DynamicListView.getItems().size();i++) {
    		taskA1DynamicListView.getItems().get(i).setSelected(false);
    	}
    }
    
    /**
     * Task A1: Confirm button click event
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    void onTaskA1ConfirmClicked(ActionEvent event) {
    	taskA1ErrorLabel.setVisible(false);
    	//User doesn't pick a date
    	if (taskA1DatePicker.getValue() == null) {
    		taskA1ErrorLabel.setVisible(true);
    		taskA1ErrorLabel.setText("Please pick a date!");
    		taskA1ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Check picked date range
    	LocalDate localDate = taskA1DatePicker.getValue();
    	String formattedDates = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	
    	try {
    		Date selectedDate = DateUtilities.getDateFormat().parse(formattedDates);
    		if (selectedDate.compareTo(handler.getStartDate()) < 0 || selectedDate.compareTo(handler.getEndDate()) > 0) {

        		taskA1ErrorLabel.setVisible(true);
        		taskA1ErrorLabel.setText("Invalid date range!");
        		taskA1ErrorLabel.setTextFill(Color.RED);
    			return;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//Handle the selected date

    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskA1DynamicListView.getItems().size(); i++) {
    		if (taskA1DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskA1DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		taskA1ErrorLabel.setVisible(true);
    		taskA1ErrorLabel.setText("Please pick at least one country!");
    		taskA1ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Create handler
    	GUISelectTableHandler handler = new GUISelectTableHandler(selectedCountry, formattedDates);
    	CaseDataAnalysis analysis = new CaseDataAnalysis(defaultDataset, handler);
    	
    	//Handle output
    	
    	//Reset Table Column
    	TableColumn<CaseObject, String> country = new TableColumn<>("Country");
    	country.setCellValueFactory(new PropertyValueFactory<>("country"));
    	TableColumn<CaseObject, String> case1 = new TableColumn<>("Total Case");
    	case1.setCellValueFactory(new PropertyValueFactory<>("case"));
    	TableColumn<CaseObject, String> casePerMillion = new TableColumn<>("Total Case / 1M");
    	casePerMillion.setCellValueFactory(new PropertyValueFactory<>("casepermillion"));
    	taskA1Table.getColumns().setAll(country, case1, casePerMillion);
    	
    	//Reset Observable List
    	taskA1TableList = FXCollections.observableList(analysis.getResult());
    	taskA1Table.setItems(taskA1TableList);
    	//System.out.print(taskB1TableList.isEmpty());
    	taskA1Table.setVisible(true);
    }
    
    
    /**
     * Task B1: Reset button click event
     * Reset the checked country and date
     * @param event
     */
    @FXML
    void onTaskB1ResetClicked(ActionEvent event) {
    	taskB1DatePicker.setValue(null);
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
    	taskB1ErrorLabel.setVisible(false);
    	//User doesn't pick a date
    	if (taskB1DatePicker.getValue() == null) {
    		taskB1ErrorLabel.setVisible(true);
    		taskB1ErrorLabel.setText("Please pick a date!");
    		taskB1ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Check picked date range
    	LocalDate localDate = taskB1DatePicker.getValue();
    	String formattedDates = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	
    	try {
    		Date selectedDate = DateUtilities.getDateFormat().parse(formattedDates);
    		if (selectedDate.compareTo(handler.getStartDate()) < 0 || selectedDate.compareTo(handler.getEndDate()) > 0) {

        		taskB1ErrorLabel.setVisible(true);
        		taskB1ErrorLabel.setText("Invalid date range!");
        		taskB1ErrorLabel.setTextFill(Color.RED);
    			return;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//Handle the selected date

    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskB1DynamicListView.getItems().size(); i++) {
    		if (taskB1DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskB1DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		taskB1ErrorLabel.setVisible(true);
    		taskB1ErrorLabel.setText("Please pick at least one country!");
    		taskB1ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Create handler
    	GUISelectTableHandler handler = new GUISelectTableHandler(selectedCountry, formattedDates);
    	DeathDataAnalysis analysis = new DeathDataAnalysis(defaultDataset, handler);
    	
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
    	taskB1Table.setVisible(true);
    }
    
    /**
     * Task C1: Reset button event
     * @param event
     */
    @FXML
    void onTaskC1ResetClicked(ActionEvent event) {
    	taskC1DatePicker.setValue(null);
    	for (int i = 0; i < taskC1DynamicListView.getItems().size();i++) {
    		taskC1DynamicListView.getItems().get(i).setSelected(false);
    	}
    }
    
    /**
     * Task C1: Confirm button click event
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    void onTaskC1ConfirmClicked(ActionEvent event) {
    	taskC1ErrorLabel.setVisible(false);
    	//User doesn't pick a date
    	if (taskC1DatePicker.getValue() == null) {
    		taskC1ErrorLabel.setVisible(true);
    		taskC1ErrorLabel.setText("Please pick a date!");
    		taskC1ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Check picked date range
    	LocalDate localDate = taskC1DatePicker.getValue();
    	String formattedDates = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	
    	try {
    		Date selectedDate = DateUtilities.getDateFormat().parse(formattedDates);
    		if (selectedDate.compareTo(handler.getStartDate()) < 0 || selectedDate.compareTo(handler.getEndDate()) > 0) {

        		taskC1ErrorLabel.setVisible(true);
        		taskC1ErrorLabel.setText("Invalid date range!");
        		taskC1ErrorLabel.setTextFill(Color.RED);
    			return;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//Handle the selected date

    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskC1DynamicListView.getItems().size(); i++) {
    		if (taskC1DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskC1DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		taskC1ErrorLabel.setVisible(true);
    		taskC1ErrorLabel.setText("Please pick at least one country!");
    		taskC1ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Create handler
    	GUISelectTableHandler handler = new GUISelectTableHandler(selectedCountry, formattedDates);
    	VaccineAnalysis analysis = new VaccineAnalysis(defaultDataset, handler);
    	
    	//Handle output
    	
    	//Reset Table Column
    	TableColumn<VaccineObject, String> country = new TableColumn<>("Country");
    	country.setCellValueFactory(new PropertyValueFactory<>("country"));
    	TableColumn<VaccineObject, String> fullyVaccinated = new TableColumn<>("Fully Vaccinated");
    	fullyVaccinated.setCellValueFactory(new PropertyValueFactory<>("fullyvaccinated"));
    	TableColumn<VaccineObject, String> percentageVaccinated = new TableColumn<>("Rate of Vaccination");
    	percentageVaccinated.setCellValueFactory(new PropertyValueFactory<>("percentagevaccinated"));
    	taskC1Table.getColumns().setAll(country, fullyVaccinated, percentageVaccinated);
    	
    	//Reset Observable List
    	taskC1TableList = FXCollections.observableList(analysis.getResult());
    	taskC1Table.setItems(taskC1TableList);
    	//System.out.print(taskB1TableList.isEmpty());
    	taskC1Table.setVisible(true);
    }
    
    
    /**
     * Task A2: Reset button event
     * @param event
     */
    @FXML
    void onTaskA2ResetClicked(ActionEvent event) {
    	taskA2DatePicker1.getEditor().clear();
    	taskA2DatePicker2.getEditor().clear();
    	for (int i = 0; i < taskA2DynamicListView.getItems().size();i++) {
    		taskA2DynamicListView.getItems().get(i).setSelected(false);
    	}
    }
    
    /**
     * Task A2: Confirm button click event
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    void onTaskA2ConfirmClicked(ActionEvent event) {
    	taskA2ErrorLabel.setVisible(false);
    	//User doesn't pick a date
    	if (taskA2DatePicker1.getValue() == null || taskA2DatePicker2.getValue() == null) {
    		taskA2ErrorLabel.setVisible(true);
    		taskA2ErrorLabel.setText("Please pick a date!");
    		taskA2ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Check picked date range
    	LocalDate localDate1 = taskA2DatePicker1.getValue();
    	LocalDate localDate2 = taskA2DatePicker2.getValue();
    	String formattedDates1 = localDate1.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	String formattedDates2 = localDate2.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
  	
    	try {
    		Date selectedDate1 = DateUtilities.getDateFormat().parse(formattedDates1);
    		Date selectedDate2 = DateUtilities.getDateFormat().parse(formattedDates2);
    		if (selectedDate1.compareTo(handler.getStartDate()) < 0 || selectedDate1.compareTo(handler.getEndDate()) > 0
    			|| selectedDate2.compareTo(handler.getStartDate()) < 0 || selectedDate2.compareTo(handler.getEndDate()) > 0 || selectedDate1.compareTo(selectedDate2) > 0) {
        		taskA2ErrorLabel.setVisible(true);
        		taskA2ErrorLabel.setText("Invalid date range!");
        		taskA2ErrorLabel.setTextFill(Color.RED);
    			return;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//Handle the selected date

    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskA2DynamicListView.getItems().size(); i++) {
    		if (taskA2DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskA2DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		taskA2ErrorLabel.setVisible(true);
    		taskA2ErrorLabel.setText("Please pick at least one country!");
    		taskA2ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Create handler
    	GUISelectChartHandler handler = new GUISelectChartHandler(selectedCountry, formattedDates1, formattedDates2);
    	CaseDataAnalysis analysis = new CaseDataAnalysis("COVID_Dataset_v1.0.csv", handler);
    	
    	//Handle output
        //y-axis for the percentage (Number type), x-axis for the Date (String type)
        taskA2Chart.getData().clear(); // clear previous data first
        taskA2Chart.setTitle("Cumulative Confirmed COVID-19 Cases (per 1M)");
        taskA2Chart.getXAxis().setAutoRanging(true);
        taskA2Chart.getYAxis().setAutoRanging(true);
        for (String country : selectedCountry) {
        	XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        	series.setName(country);
        	ZoneId defaultZoneId = ZoneId.systemDefault();
        	CountryCode code = CountryCode.getByName(country);
        	String dataSet = analysis.getDataSet();
        	//loop through each day within the range and get corresponding data
        	for (LocalDate date = localDate1; date.isBefore(localDate2.plusDays(1)); date = date.plusDays(1)) {
        		DataCache.getCache();
				Date dateDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				DayDataObject data = DataCache.getCache().getData(dataSet, code, dateDate);
				if (data != null) {
					CaseObject object = data.getCaseObject(code);
					String formattedDateForX = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					series.getData().add(new XYChart.Data<String,Number>(formattedDateForX, Float.parseFloat(object.getCasepermillion())));
				}
        	}
        	taskA2Chart.getData().add(series);
        }
        taskA2Chart.setVisible(true);
    }
    
    @FXML
    void onTaskB2ResetClicked(ActionEvent event) {
    	taskB2DatePicker1.getEditor().clear();
    	taskB2DatePicker2.getEditor().clear();
    	for (int i = 0; i < taskB2DynamicListView.getItems().size();i++) {
    		taskB2DynamicListView.getItems().get(i).setSelected(false);
    	}
    }
    
    @SuppressWarnings("unchecked")
	@FXML
    void onTaskB2ConfirmClicked(ActionEvent event) {
    	taskB2ErrorLabel.setVisible(false);
    	//User doesn't pick a date
    	if (taskB2DatePicker1.getValue() == null || taskB2DatePicker2.getValue() == null) {
    		taskB2ErrorLabel.setVisible(true);
    		taskB2ErrorLabel.setText("Please pick a date!");
    		taskB2ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Check picked date range
    	LocalDate localDate1 = taskB2DatePicker1.getValue();
    	LocalDate localDate2 = taskB2DatePicker2.getValue();
    	String formattedDates1 = localDate1.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	String formattedDates2 = localDate2.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
  	
    	try {
    		Date selectedDate1 = DateUtilities.getDateFormat().parse(formattedDates1);
    		Date selectedDate2 = DateUtilities.getDateFormat().parse(formattedDates2);
    		if (selectedDate1.compareTo(handler.getStartDate()) < 0 || selectedDate1.compareTo(handler.getEndDate()) > 0
    			|| selectedDate2.compareTo(handler.getStartDate()) < 0 || selectedDate2.compareTo(handler.getEndDate()) > 0 || selectedDate1.compareTo(selectedDate2) > 0) {
        		taskB2ErrorLabel.setVisible(true);
        		taskB2ErrorLabel.setText("Invalid date range!");
        		taskB2ErrorLabel.setTextFill(Color.RED);
    			return;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//Handle the selected date

    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskB2DynamicListView.getItems().size(); i++) {
    		if (taskB2DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskB2DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		taskB2ErrorLabel.setVisible(true);
    		taskB2ErrorLabel.setText("Please pick at least one country!");
    		taskB2ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Create handler
    	GUISelectChartHandler handler = new GUISelectChartHandler(selectedCountry, formattedDates1, formattedDates2);
    	DeathDataAnalysis analysis = new DeathDataAnalysis("COVID_Dataset_v1.0.csv", handler);
    	
    	//Handle output
        //y-axis for the percentage (Number type), x-axis for the Date (String type)
        taskB2Chart.getData().clear(); // clear previous data first
        taskB2Chart.setTitle("Cumulative Confirmed COVID-19 Deaths (per 1M)");
        taskB2Chart.getXAxis().setAutoRanging(true);
        taskB2Chart.getYAxis().setAutoRanging(true);
        for (String country : selectedCountry) {
        	XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        	series.setName(country);
        	ZoneId defaultZoneId = ZoneId.systemDefault();
        	CountryCode code = CountryCode.getByName(country);
        	String dataSet = analysis.getDataSet();
        	//loop through each day within the range and get corresponding data
        	for (LocalDate date = localDate1; date.isBefore(localDate2.plusDays(1)); date = date.plusDays(1)) {
        		DataCache.getCache();
				Date dateDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				DayDataObject data = DataCache.getCache().getData(dataSet, code, dateDate);
				if (data != null) {
					DeathObject object = data.getDeathObject(code);
					String formattedDateForX = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					series.getData().add(new XYChart.Data<String,Number>(formattedDateForX, Float.parseFloat(object.getDeathpermillion())));
				}
        	}
        	taskB2Chart.getData().add(series);
        }
        taskB2Chart.setVisible(true);
    }
    
    @FXML
    void onTaskC2ResetClicked(ActionEvent event) {
    	taskC2DatePicker1.getEditor().clear();
    	taskC2DatePicker2.getEditor().clear();
    	for (int i = 0; i < taskC2DynamicListView.getItems().size();i++) {
    		taskC2DynamicListView.getItems().get(i).setSelected(false);
    	}
    }
    
    @SuppressWarnings("unchecked")
	@FXML
    void onTaskC2ConfirmClicked(ActionEvent event) {
    	taskC2ErrorLabel.setVisible(false);
    	//User doesn't pick a date
    	if (taskC2DatePicker1.getValue() == null || taskC2DatePicker2.getValue() == null) {
    		taskC2ErrorLabel.setVisible(true);
    		taskC2ErrorLabel.setText("Please pick a date!");
    		taskC2ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Check picked date range
    	LocalDate localDate1 = taskC2DatePicker1.getValue();
    	LocalDate localDate2 = taskC2DatePicker2.getValue();
    	String formattedDates1 = localDate1.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	String formattedDates2 = localDate2.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
  	
    	try {
    		Date selectedDate1 = DateUtilities.getDateFormat().parse(formattedDates1);
    		Date selectedDate2 = DateUtilities.getDateFormat().parse(formattedDates2);
    		if (selectedDate1.compareTo(handler.getStartDate()) < 0 || selectedDate1.compareTo(handler.getEndDate()) > 0
    			|| selectedDate2.compareTo(handler.getStartDate()) < 0 || selectedDate2.compareTo(handler.getEndDate()) > 0 || selectedDate1.compareTo(selectedDate2) > 0) {
        		taskC2ErrorLabel.setVisible(true);
        		taskC2ErrorLabel.setText("Invalid date range!");
        		taskC2ErrorLabel.setTextFill(Color.RED);
    			return;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	//Handle the selected date

    	//Add picked countries to the list
    	//Terminate the operation if the user has NOT picked any country
    	ArrayList<String> selectedCountry = new ArrayList<String>();
    	for (int i = 0; i < taskC2DynamicListView.getItems().size(); i++) {
    		if (taskC2DynamicListView.getItems().get(i).isSelected()) {
    			selectedCountry.add(taskC2DynamicListView.getItems().get(i).getText());
    		}
    	}
    	if (selectedCountry.isEmpty()) {
    		taskC2ErrorLabel.setVisible(true);
    		taskC2ErrorLabel.setText("Please pick at least one country!");
    		taskC2ErrorLabel.setTextFill(Color.RED);
    		return;
    	}
    	
    	//Create handler
    	GUISelectChartHandler handler = new GUISelectChartHandler(selectedCountry, formattedDates1, formattedDates2);
    	VaccineAnalysis analysis = new VaccineAnalysis("COVID_Dataset_v1.0.csv", handler);
    	
    	//Handle output
        //y-axis for the percentage (Number type), x-axis for the Date (String type)
        taskC2Chart.getData().clear(); // clear previous data first
        taskC2Chart.setTitle("Cumulative Rate of Vaccination against COVID-19");
        taskC2Chart.getXAxis().setAutoRanging(true);
        taskC2Chart.getYAxis().setAutoRanging(true);
        for (String country : selectedCountry) {
        	XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        	series.setName(country);
        	ZoneId defaultZoneId = ZoneId.systemDefault();
        	CountryCode code = CountryCode.getByName(country);
        	String dataSet = analysis.getDataSet();
        	//loop through each day within the range and get corresponding data
        	for (LocalDate date = localDate1; date.isBefore(localDate2.plusDays(1)); date = date.plusDays(1)) {
        		DataCache.getCache();
				Date dateDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				DayDataObject data = DataCache.getCache().getData(dataSet, code, dateDate);
				if (data != null) {
					VaccineObject object = data.getVaccineObject(code);
					String formattedDateForX = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					series.getData().add(new XYChart.Data<String,Number>(formattedDateForX, Float.parseFloat(object.getPercentagevaccinated())));
				} 
        	}
        	taskC2Chart.getData().add(series);
        }
        taskC2Chart.setVisible(true);
    }
}

