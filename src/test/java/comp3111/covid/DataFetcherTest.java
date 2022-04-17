package comp3111.covid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3111.covid.Utilities.DataFetcher;
import edu.duke.FileResource;

public class DataFetcherTest {
	private static String downloadUrl;
	private static int downloaded;
	private static final String RESOURCE_PATH = "/src/main/resources/dataset/";
	private static final String absPath = new File("").getAbsolutePath();
	
	@Before
	public void setUp() throws Exception {
		downloadUrl = DataFetcher.getURL();
		downloaded = DataFetcher.downloadData();
	}
	
	@After
	public void tearDown() throws Exception {
		Path path = Paths.get(absPath + RESOURCE_PATH + "dataset_new.csv");
		Files.deleteIfExists(path);
	}

	@Test
	public static void testGetURL() throws Exception {
		assertNotEquals(downloadUrl, null);
	}
	
	@Test
	public static void testDownload() throws Exception {
		assertEquals(downloaded, 0);
	}
	
	@Test
	public static void testFileExists() throws Exception {
		FileResource resource = new FileResource(absPath + RESOURCE_PATH + "dataset_new.csv");
		assertNotEquals(resource, null);
	}

}
