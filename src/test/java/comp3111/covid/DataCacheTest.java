package comp3111.covid;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.data.DataCache;

public class DataCacheTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void inputInvalidTest() {
		assertEquals(null, DataCache.getCache().getData("999", null, null)); 
	}
	
}
