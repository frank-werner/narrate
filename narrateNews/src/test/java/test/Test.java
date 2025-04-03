package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

import newsMain.NewsAPI;
import newsMain.TomtomAPI;
import newsMain.Weather;

public class Test {

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void testNewsAPI() throws Exception {
		NewsAPI na = new NewsAPI();
		ArrayList<String> keywords = na.read();
		assertFalse(keywords == null || keywords.isEmpty());
	}

	@org.junit.Test
	public void testTomtomAPI() throws Exception {
		TomtomAPI ta = new TomtomAPI();
		Long speed = ta.read();
		assertTrue(speed >= 0);
	}

	@org.junit.Test
	public void testWeather() throws Exception {
		Weather weather = new Weather();
		ArrayList<String> wetter = weather.read();
		String temeperaturString = wetter.get(0);
		Float temperatur = Float.parseFloat(temeperaturString);
		assertTrue(temperatur >= 0);
	}

}
