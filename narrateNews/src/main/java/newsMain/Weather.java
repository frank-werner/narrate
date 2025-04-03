package newsMain;


import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Weather {

	private static String url = "https://www.dwd.de/DE/leistungen/beobachtung/beobachtung.html";
	
	public ArrayList<String> read() throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements content = doc.select("div[class=content data] > div[id=wettertab] > "
				+ "table > tbody > tr:contains(Frankfurt)");
		
		ArrayList<String> result = new ArrayList<String>();
		for (Element item : content) {
			result.add(item.child(3).text());
			result.add(item.child(9).text());
			result.add(item.child(10).text());
		}
		return result;
	}
}
