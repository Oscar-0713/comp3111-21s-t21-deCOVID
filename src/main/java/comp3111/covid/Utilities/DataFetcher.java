package comp3111.covid.Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
	
	private static final String RESOURCE_PATH = "/src/main/resources/dataset/";
	private static final String absPath = new File("").getAbsolutePath();
	private static final String oldname = "dataset_pre.csv";
	private static final String newname = "dataset_new.csv";
	
	public static void main(String arges[]) {
		try {
			processData("dataset_new.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int downloadData()  throws IOException, URISyntaxException {		
		String downloadUrl = getURL();
		String targetPath = absPath + RESOURCE_PATH + oldname;
		
		try {
			downloadUsingNIO(downloadUrl, targetPath);
			processData(oldname);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public static void processData(String dataset) throws Exception{
		Reader reader = Files.newBufferedReader(Paths.get(absPath + RESOURCE_PATH + dataset));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader().withIgnoreHeaderCase(false));
        CSVPrinter printer = new CSVPrinter(new FileWriter(absPath + RESOURCE_PATH + newname), 
        		CSVFormat.DEFAULT.withRecordSeparator('\n'));
        
		Map<String, Integer> headers = csvParser.getHeaderMap();
		ArrayList<String> headerList = new ArrayList<>();
        
        for (Map.Entry<String,Integer> entry : headers.entrySet()) {
        	headerList.add(entry.getKey());
        }
        int dateInd = headerList.indexOf("date");
        csvprint(printer, headerList);
		
		DateFormat ndf = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat odf = new SimpleDateFormat("yyyy-MM-dd");
     
        for (CSVRecord csvRecord : csvParser) {
        	String date = csvRecord.get("date");
        	//System.out.println(date);
        	
        	Date d = odf.parse(date);
        	date = ndf.format(d);
        	//System.out.println(date);
        	
        	ArrayList<String> row = new ArrayList<>();
        	for (int i = 0; i < csvRecord.size(); i++) {
        		if (i == dateInd) {
        			row.add(date);
        		} else {
        			row.add(csvRecord.get(i));
        		}
        	}
        	
        	csvprint(printer, row);
        }
     
        printer.close();
        File oldfile = new File(absPath + RESOURCE_PATH + oldname); 
        oldfile.delete();
	}
	
	private static void csvprint(CSVPrinter printer, ArrayList<String> s) throws Exception {
        for (String val : s) {
            printer.print(val != null ? String.valueOf(val) : "");
        }
        printer.println();
	}
    
}
