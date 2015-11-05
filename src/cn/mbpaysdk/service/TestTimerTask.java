package cn.mbpaysdk.service;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.game.util.RandomUtil;

import net.sf.json.JSONObject;

public class TestTimerTask extends TimerTask {
	private final static Log logger = LogFactory.getLog(TestTimerTask.class);// logger
	MqttBroker mqttBroker;
	public void setMqttBroker(MqttBroker mqttBroker) {
		this.mqttBroker = mqttBroker;
	}
	int flag = 0;
	@Override
	public void run() {
		logger.info("TestTimerTask run");
		System.out.println("TestTimerTask run");
		int r = RandomUtil.getIntRandom(10000);
		if(flag == 0)
		{
			//String comd = "category=1&show=1&icon=http://pay.91y.com/images/logo.png&click=1&title=first&content=已YY人抢购，数量有限，速来！";
			//String comd = "category=1&show=1&icon=http://pay.91y.com/images/logo.png&title=title&content=content&click=4&phone=13073369125";
			
			//String comd = "category=1&show=notify&icon=http://pay.91y.com/images/logo.png&click=start2&title=游戏"+r+"&package=com.game.sweetheart&url=http://mm.10086.cn/download/android/300008248557?from=www&content=已YY人抢购，数量有限，速来！";
			//mqttBroker.sendMessage("appid_channel_110001_10002", comd, 0, false);
			
			String comd = "category=1&show=collect&icon=http://pay.91y.com/images/logo.png&click=allapp&title=游戏"+r+"&package=com.game.sweetheart&url=http://mm.10086.cn/download/android/300008248557?from=www&content=已YY人抢购，数量有限，速来！";
			mqttBroker.sendMessage("appid_channel_123456_10003", comd, 0, false);
			
			flag = 1;
		}
		else
		{
			//String comd = "category=1&show=1&icon=http://pay.91y.com/images/logo.png&click=1&title=速来！"+r+"&content=已YY人抢购，数量有限，速来！";
			//String comd = "category=1&show=1&icon=http://pay.91y.com/images/logo.png&title=title&content=content&click=4&phone=13073369125";
			//String comd = "category=1&show=notify&icon=http://pay.91y.com/images/logo.png&click=start2&title=游戏"+r+"&package=com.game.sweetheart&url=http://mm.10086.cn/download/android/300008248557?from=www&content=已YY人抢购，数量有限，速来！";
			
			//mqttBroker.sendMessage("appid_channel_123456_10001", comd, 0, false);
			flag = 1;
		}
	}
}
