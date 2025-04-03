package fillMain;

public class SensorEntry {
	
	int activityID, supplierID;
	String orderID;
	Five five = null;
	
	public SensorEntry(int activityID, int orderNo) {
		this.activityID = activityID;
		this.orderID = "testOrder" + orderNo;
		this.supplierID = 0;
		if (activityID == 0) {
			createFive();
		}
	}
	
	public String toString() {
		return activityID + " " + orderID;
	}
	
	private void createFive() {
		this.five = new Five();
	}
	
	public String getKeyword() {
		if (five == null)
			return null;
		else return five.keyword;
	}
}
