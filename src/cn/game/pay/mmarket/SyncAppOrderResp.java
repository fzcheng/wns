/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package cn.game.pay.mmarket;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 响应MMIAP支付服务器bean
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
@XmlRootElement(name = "SyncAppOrderResp")
public class SyncAppOrderResp implements Serializable {

	/**
	 * @author kimi
	 * @dateTime 2013-4-28 上午11:38:03
	 */
	private static final long serialVersionUID = 7123473855192948887L;

	private String MsgType;//消息类型

	private String Version;//版本号

	private Integer hRet;//返回值

	public String getMsgType() {
		return MsgType;
	}
	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getVersion() {
		return Version;
	}
	@XmlElement(name = "Version")
	public void setVersion(String version) {
		Version = version;
	}

	public Integer gethRet() {
		return hRet;
	}
	@XmlElement(name = "hRet")
	public void sethRet(Integer hRet) {
		this.hRet = hRet;
	}
}
