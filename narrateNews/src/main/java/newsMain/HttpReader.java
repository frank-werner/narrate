package newsMain;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HttpReader {

    static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
	public JSONObject read(String url) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
        	    .build();

        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        
        // System.out.println("Response: " + response);       
        InputStreamReader reader = new InputStreamReader((InputStream)response.body(),
					Charset.forName("utf-8"));
        String responseString = readerToString(reader);
        // System.out.println("Body: " + responseString);
        
        // JSONArray objects;
        JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(responseString);
		return json;
	}
	
	static String readerToString(InputStreamReader reader) throws Exception {
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();  
        return targetString;
    }


}
