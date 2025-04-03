package newsMqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

	MqttClient client;
	int qos = 0;
	
	public Publisher(MqttClient client) {
		this.client = client;
	}
	
	public Boolean publish(String topic, String content) {
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		try {
			client.publish(topic, message);
		} catch (MqttException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
