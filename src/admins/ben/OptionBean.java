package admins.ben;
/**
 * 下拉列表数据Bean
 * @author LuZhiYong
 * @Date 2012-7-4
 */
public class OptionBean {

	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getUrlValue(){
		return value.substring(value.indexOf("/"));
	}
	
}
