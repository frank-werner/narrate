package fillMain;

import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import fillMqtt.Broker;
import fillMqtt.Publisher;
import fillMqtt.local_EM_Docker;

public class Main {

	static MqttClient client;
	static Publisher publisher;	
	static JsonCreator creator;

	public static void main(String[] args) throws Exception {
		
		Broker broker = new local_EM_Docker();
		client = new MqttClient(broker.getBrokerUrl(),broker.getClient(),new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		// options.setUserName(broker.getUser());
		// options.setPassword(broker.getPwd().toCharArray());
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(60);
        
		try {
			MqttConnectOptions coptions = new MqttConnectOptions();
			client.connect(coptions);
		} catch (MqttException ex) {
			System.out.println("fillMain.Main:not connected: " + ex.getMessage());
		}

		publisher = new Publisher(client);	
		creator = new JsonCreator();
		
		for (int orderNo = 0; orderNo < 10; orderNo++) {
			SensorEntry last0 = null;
			for (int activityID = 0; activityID < 5; activityID++) {
				SensorEntry sensorEntry = new SensorEntry(activityID,orderNo);
				publishSensorEntry(sensorEntry);
				switch (activityID) {
				case 0:
					last0 = sensorEntry;
					TimeUnit.SECONDS.sleep(4);
					break;
				case 2:
					if (last0.getKeyword().equals("krieg")) {
						TimeUnit.SECONDS.sleep(15);
					} else {
						TimeUnit.SECONDS.sleep(9);
					}
					break;
				default:
					TimeUnit.SECONDS.sleep(4);
				}
			}
			TimeUnit.MINUTES.sleep(2);
		}
	}

	static private void publishSensorEntry(SensorEntry sensorEntry) {
		String json = creator.createJson(sensorEntry);
		Boolean result = publisher.publish("mqtt/sensors",json);
		if (!result) {
			System.out.println(json + " not published");
		} else {
			System.out.println(json + " published");
		}
	}
	

}
