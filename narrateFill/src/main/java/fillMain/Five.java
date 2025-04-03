package fillMain;

import java.util.Random;

public class Five {

	String[] cloudArray = {"---","wolkenlos","bewölkt"};
	String[] keywordArray = {"---","krieg","gewalt"};
	long speed, temperature;
	String clouds, serious, keyword;
	
	Five() {
		this.speed = generateRandomLong(5L,25L);
		this.temperature = generateRandomLong(-5L,30L);
		this.clouds = cloudArray[generateRandomInt(0,2)];
		this.serious = "---";
		this.keyword = keywordArray[generateRandomInt(0,2)];
	}
	
	private Long generateRandomLong(long leftLimit, long rightLimit) {
	    return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}
	
	public int generateRandomInt(int leftLimit, int rightLimit) {
	    return leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
	}
}
