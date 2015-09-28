package cn.mbpaysdk.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttSimpleCallback;

/**
 * 
 * @author fzcheng
 *
 */
public class MqttBroker {
	private final static Log logger = LogFactory.getLog(MqttBroker.class);// logger
	// TCP port
	//private final static String CONNECTION_STRING = "tcp://192.168.137.1:1883";
	//private final static String CONNECTION_STRING = "tcp://192.168.137.1:1883";
	private final static String CONNECTION_STRING = "tcp://121.41.73.198:1883";
	private final static boolean CLEAN_START = true;
	// Let's set the internal keep alive for MQTT to 15 mins. I haven't tested this value much. It could probably be increased.
	private final static short KEEP_ALIVE = 60 * 15;//1;// 
	private final static String CLIENT_ID = "master";//client ID
	private final static int[] QOS_VALUES = { 0, 0, 0, 2 };// QOS
	private final static String[] TOPICS = { "Test/TestTopics/Topic1",
			"Test/TestTopics/Topic2", "+/keepalive",
			"tokudu/keepalive" };
	private static MqttBroker instance = new MqttBroker();

	private MqttClient mqttClient;
	SimpleCallbackHandler mConnection;
	
	/**
	 * getInstance
	 * 
	 * @return
	 */
	public static MqttBroker getInstance() {
		return instance;
	}

	/**
	 * connect
	 */
	public void connect() throws MqttException {
		logger.info("connect to mqtt broker.");
		mqttClient = new MqttClient(CONNECTION_STRING);
		logger.info("***********register Simple Handler***********");
		mConnection = new SimpleCallbackHandler();
		mqttClient.registerSimpleHandler(mConnection);
		mqttClient.connect(CLIENT_ID, CLEAN_START, KEEP_ALIVE);
		logger.info("***********subscribe receiver topics***********");
		mqttClient.subscribe(TOPICS, QOS_VALUES);

		logger.info("***********CLIENT_ID:" + CLIENT_ID);

		mqttClient.publish("tokudu/keepalive", "keepalive".getBytes(), QOS_VALUES[3],
				true);
		
	}

	/**
	 * 
	 * 
	 * @param clientId
	 * @param messageId
	 */
	public void sendMessage(String clientId, String message) {
		try {

			logger.info("send message to " + clientId + ", message is "
					+ message);
			mqttClient.publish("tokudu/"+clientId, message.getBytes(),
					0, false);
			
		} catch (MqttException e) {
			logger.error(e.getCause());
			e.printStackTrace();
		}
	}
	

	/**
	 * 
	 * @author Join
	 * 
	 */
	class SimpleCallbackHandler implements MqttSimpleCallback {

		/**
		 * 
		 */
		@Override
		public void connectionLost() throws Exception {
			System.out.println("Loss of connection" + " connection downed");
			
			//stopKeepAlives();
			// null itself
			mConnection = null;
			if (mConnection == null) {
				System.out.println("Reconnecting...");
				try{
					connect();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					//TODO 发送报警
				}
			}
		}

		/**
		 * publishArrived
		 */
		@Override
		public void publishArrived(String topicName, byte[] payload, int Qos,
				boolean retained) throws Exception {
			System.out.println("publishArrived------ ");
			System.out.println("topicName: " + topicName);
			System.out.println("payload: " + new String(payload));
			System.out.println("Qos(0,1,2): " + Qos);
			System.out.println("retained(false=true=): " + retained);
		}

	}

	public static void main(String[] args) {
		//MqttBroker mqttBroker = new MqttBroker();
		
		try {
			//mqttBroker.connect();
			MqttBroker.getInstance().connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
//		Timer timer = new Timer();
//		TestTimerTask task = new TestTimerTask();
//        task.setMqttBroker(mqttBroker);
//        timer.schedule(task, 500L, 20000L);
	}
}
