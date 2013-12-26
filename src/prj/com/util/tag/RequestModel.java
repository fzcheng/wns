package prj.com.util.tag;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装请求参数
 * @author hhj
 * Created on 2008-8-15 下午03:01:19
 */
public class RequestModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String url;
    private Map<String, Object> params = new HashMap<String, Object>();

	public RequestModel(String url) {
		super();
		this.url = url;
	}
	public RequestModel(String url,Map params) {
		super();
		this.url = url;
		this.params = params;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public void addParam(String name,Object value){
		params.put(name, value);
	}
   
	public Object getParam(String name){
		return params.get(name);
	}
}
