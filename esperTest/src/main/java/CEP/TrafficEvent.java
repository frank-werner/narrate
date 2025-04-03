package CEP;

public class TrafficEvent extends AbstractEvent {

  private String location;

  public TrafficEvent(String timestamp, String location) {
	  this.timestamp = timestamp;
	  this.location = location; 
  }

  public String getTimestamp() {
	  return timestamp;
  }

  public String getLocation() {
	  return location;
  }
  
  public String toString() {
	  return "TrafficEvent: " + timestamp + " " + location;
  }
}
