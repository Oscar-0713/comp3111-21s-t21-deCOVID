package comp3111.covid.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.Arima.FittingStrategy;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.forecast.Forecaster;

public class DataForecast {
	public static void test() {
		ArrayList<Float> inputs = new ArrayList<Float>(Arrays.asList(0.0f, 0.0f, 2.0f, 3.0f, 5.0f, 8.0f, 9.0f, 8.0f));
		List<Double> outputs = predictValues(5, inputs, 2, 1, 2).get('P');
		
		System.out.println("Inputs: " + inputs.toString());
		System.out.println("Outputs: " + outputs.toString());
		
	}
	
	/**
	 * Uses the ARIMA forecasting model to produce point estimate forecasts for the given data
	 * @param numDays, the number of days ahead for prediction
	 * @param inputs, the data points used for forecasting
	 * @param p, the AR parameter for ARIMA
	 * @param d, the I parameter for ARIMA
	 * @param q, the MA parameter for ARIMA
	 * @return the list containing forecast results
	 */
	public static HashMap<Character, List<Double>> predictValues(int numDays, ArrayList<Float> inputs, int p, int d, int q){
		
		TimeSeries historical = TimeSeries.from(tofloat(inputs));
		Arima aModel = Arima.model(historical, ArimaOrder.order(p,d,q), FittingStrategy.CSS);
		Forecast fc = aModel.forecast(numDays);
		
		HashMap<Character, List<Double>> results = new HashMap<>();
		
		List<Double> points = fc.pointEstimates().asList();
		List<Double> upperBound = fc.upperPredictionInterval().asList();
		List<Double> lowerBound = fc.lowerPredictionInterval().asList();
		
		results.put('P', points);
		results.put('U', upperBound);
		results.put('L', lowerBound);
		
		return results;
	}
	
	
	/**
	 * A private method to convert ArrayList<Double> into double[]. For internal development use only
	 * @param list, your ArrayList<Double> as input
	 * @return result, the converted double[]
	 */
	private static double[] tofloat(ArrayList<Float> list) {
		double[] result = new double[list.size()];
		for(int i = 0; i < list.size(); i++) {
			result[i] = (double) list.get(i).floatValue();
		}
		
		return result;
	}
}
