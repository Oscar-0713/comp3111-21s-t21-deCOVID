package comp3111.covid.data;

import comp3111.covid.Utilities.CountryCode;

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
	
	public DeathObject getDeathObject(CountryCode code) {
		return new DeathObject(code, totalDeath, (float) ((double)(totalDeath) / ((double) (population/ONE_MILLION))));
	}
}
