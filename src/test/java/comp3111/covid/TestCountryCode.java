package comp3111.covid;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.Utilities.CountryCode;

public class TestCountryCode {
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void nullCountryCode() {
		assertEquals(CountryCode.getByCode(null), null);
	}
	
	@Test
	public void invalidCountryName() {
		assertEquals(CountryCode.getByName("abcdefg"), null);
	}
	
	@Test
	public void validGetByAlpha2Code() {
		assertEquals(CountryCode.getByCode("AD").getName(), "Andorra");
	}
	
}
