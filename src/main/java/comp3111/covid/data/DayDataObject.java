package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;
/**
 * This is a data object class, inherited with the dataCache.
 * This object is designed to return ALL required object to show data.
 * 
 * @author oscar
 *
 */
public class DayDataObject {
	private long newCase;
	private long newDeath; 
	private long fullyVanccinated;
	private long population;
	private long totalCase;
	private long totalDeath;
	private static double ONE_MILLION = 1000000;
	
	public DayDataObject(long newCase, long newDeath, long fullyVanccinated, long population, long totalCase, long totalDeath) {
		this.newCase = newCase;
		this.newDeath = newDeath;
		this.fullyVanccinated = fullyVanccinated;
		this.population = population;
		this.totalCase = totalCase;
		this.totalDeath = totalDeath;
	}
	
	/**
	 * compatibility interface to get DeathObject
	 * @param code
	 * @return DeathObject
	 */
	public DeathObject getDeathObject(CountryCode code) {
		return new DeathObject(code, totalDeath, (float) ((float)(totalDeath) / ((float) (population/ONE_MILLION))));
	}
	
	/**
	 * compatibility interface to get CaseObject
	 * @param code
	 * @return The targeted CaseObject for display
	 */
	public CaseObject getCaseObject(CountryCode code) {
		return new CaseObject(code, totalCase, (float) ((float)(totalCase) / ((float) (population/ONE_MILLION))));
	}
	
	/**
	 * compatibility interface to get VaccineObject
	 * @param code
	 * @return The targeted VaccineObject for display
	 */
	public VaccineObject getVaccineObject(CountryCode code) {
		return new VaccineObject(code, fullyVanccinated, (float) ((float)(fullyVanccinated) / ((float) (population)) *100));
	}
}
