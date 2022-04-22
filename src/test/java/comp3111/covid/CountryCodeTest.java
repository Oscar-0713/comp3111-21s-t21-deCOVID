package comp3111.covid;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.Utilities.CountryCode;

public class CountryCodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetByCodeAlpha3() {
		assertEquals(CountryCode.HK, CountryCode.getByCode("HKG"));
		assertEquals(null, CountryCode.getByCode("AAA"));
	}
	
	@Test
	public void testGetByCodeOWID() {
		assertEquals(CountryCode.OWID_AFR, CountryCode.getByCode("OWID_AFR"));
		assertEquals(null, CountryCode.getByCode("OWID_FFF"));
	}
	
	@Test
	public void testGetByName() {
		assertEquals(null, CountryCode.getByName(null));
		assertEquals(CountryCode.HK, CountryCode.getByName("Hong Kong"));
	}
	
	@Test
	public void testGetByInt() {
		assertEquals(CountryCode.WS, CountryCode.getByCode(882));
		assertEquals(null, CountryCode.getByCode(-99999));
	}
	
	@Test
	public void testInvalidInput() {
		assertEquals(null, CountryCode.getByCode("sdajugfopadsjugfapds0nvadsu"));
		assertEquals(null, CountryCode.getByCode("FF"));
		assertEquals(null,CountryCode.getByCode("882"));
	}
}
