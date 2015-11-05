package cn.mbpaysdk.service;


import java.io.UnsupportedEncodingException;
import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.game.util.RandomUtil;

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
	private final static String CONNECTION_STRING = "tcp://mqtt.magicbirds.cn:1883";
	private final static boolean CLEAN_START = true;
	// Let's set the internal keep alive for MQTT to 15 mins. I haven't tested this value much. It could probably be increased.
	private final static short KEEP_ALIVE = 60 * 15;//1;// 
	private final static String CLIENT_BASE = "master";//client ID
	private static String CLIENT_ID = "";
	private final static int[] QOS_VALUES = { 0, 0, 0 };// QOS
	private final static String[] TOPICS = {
		"$SYS/broker/clients/#",
		"collect/#",
		"+/keepalive"};
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
		int r = RandomUtil.getIntRandom(10000);
		logger.info("connect to mqtt broker.");
		mqttClient = new MqttClient(CONNECTION_STRING);
		logger.info("***********register Simple Handler***********");
		mConnection = new SimpleCallbackHandler();
		mqttClient.registerSimpleHandler(mConnection);
		
		CLIENT_ID = CLIENT_BASE + r;
		mqttClient.connect(CLIENT_ID, CLEAN_START, KEEP_ALIVE);
		logger.info("***********subscribe receiver topics***********");
		mqttClient.subscribe(TOPICS, QOS_VALUES);

		logger.info("***********CLIENT_ID:" + CLIENT_ID);

		mqttClient.publish("wns_server/keepalive", CLIENT_ID.getBytes(), 0,
				true);
		
	}

	/**
	 * 
	 * 
	 * @param clientId
	 * @param messageId
	 */
	public void sendMessage(String clientId, String message, int Qos, boolean isRetain) {
		try {

			logger.info("send message to " + clientId + ", message is "
					+ message);
			try {
				//int Qos = 0;
				//boolean isRetain = false;
				mqttClient.publish(clientId, message.getBytes("utf-8"),
						Qos, isRetain);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
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
			
			//System.out.println("publishArrived------ ");
			System.out.print("TopicName:" + topicName);
			System.out.println("    VALUE:" + new String(payload, "utf-8"));
			//System.out.println("Qos      : " + Qos);
			//System.out.println("retained : " + retained);
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
		Timer timer = new Timer();
		TestTimerTask task = new TestTimerTask();
        task.setMqttBroker(MqttBroker.getInstance());
        timer.schedule(task, 500L, 300000L);
	}
}
