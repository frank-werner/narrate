package newsMqtt;

public class local_EM_Docker implements Broker {

	private String broker = "tcp://localhost:1883";
	// TLS/SSL
	// String broker = "ssl://broker.emqx.io:8883";
	private String username = "emqx";
	private String password = "public";

	public String getBrokerUrl() {
		return broker;
	}
	public String getUser() {
		return username;
	}
	public String getPwd() {
		return password;
	}
	public String getClient() {
		return "alert_client";
	}
}
