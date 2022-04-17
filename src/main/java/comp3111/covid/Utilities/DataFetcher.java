package comp3111.covid.Utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Map;
 
import org.springframework.web.client.RestTemplate;

/**
 * This class is used to downlaod the latest dataset from the GitHub repository hosted by OWID
 * @author Desmond
 * 
 */

public class DataFetcher {

	
	/**
	 * Downloads latest dataset to src/main/resources/dataset/dataset_new.csv
	 * @return 0 if the download is successful, 1 otherwise
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static int downloadData()  throws IOException, URISyntaxException {		
		String downloadUrl = getURL();
		String targetPath = "src/main/resources/dataset/dataset_new.csv";
		
		
		try {
			downloadUsingNIO(downloadUrl, targetPath);
			return 0;
		} catch (IOException e) {
			return -1;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getURL() throws IOException, URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		List<Map> response = restTemplate.getForObject(
				"https://api.github.com/repos/{owner}/{repo}/contents/public/data?ref={branch}", 
				List.class, 
				"owid", "covid-19-data", "master");
		
		for (Map fileMetaData : response) {
			 
			// Get file name & raw file download URL from response.
			String fileName = (String) fileMetaData.get("name");
			String downloadUrl = (String) fileMetaData.get("download_url");
			
			//Fetch the required data set
			if (downloadUrl != null && fileName.equals("owid-covid-data.csv")) {
				return downloadUrl;
			}
		}
		
		return null;
		
	}
	
	private static int downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        
        return 0;
    }
}
