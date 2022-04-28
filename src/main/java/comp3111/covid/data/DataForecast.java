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

/**
 * This class uses the timeseries forecasting library developed by signaflo to perform prediction on daily COVID cases and deaths
 * https://github.com/signaflo/java-timeseries.git
 * @author Desmond Lau 
 * 
 */
public class DataForecast {
	
	
	/**
	 * Uses the ARIMA forecasting model to produce 7-day forecasts for the given data points
	 * @param numDays, the number of days ahead for prediction
	 * @param inputs, the data points used for forecasting
	 * @param p, the AR parameter for ARIMA
	 * @param d, the I parameter for ARIMA
	 * @param q, the MA parameter for ARIMA
	 * @return hashmap containing point estimates, 95% upperbound points and 95% lowerbound points
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
