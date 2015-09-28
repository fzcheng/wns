package cn.mbpaysdk.vo.basic;

import cn.game.vo.basic.BasicVO;

public class MentranceVO extends BasicVO implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6444738888351491346L;
	// Fields
    private java.lang.String businesscode;//
    private java.lang.String businessshowcode;//
    private java.lang.String changename;//
    private java.lang.String configpath;//
    private java.lang.String edesc;//
    private java.lang.Integer entranceid;//
    private java.lang.String entrancename;//
    private java.lang.String etype;//
    private java.lang.Double fee;//
    private java.lang.String gamedescription;//
    private java.lang.String gamename;//
    private java.lang.Integer isofficial;//
    private java.lang.Integer issupportnew;//
    private java.lang.Integer issupportold;//
    private java.lang.String newareadesc;//
    private java.lang.String oldareadesc;//
    private java.lang.String onlinedate;//
    private java.lang.String onlinedescription;//
    private java.lang.String operator;//
    private java.lang.Double percent;//
    private java.lang.String period;//
    private java.lang.Integer spid;//
    private java.lang.String spname;//
    private java.lang.Integer sts;//
    private java.lang.String terminal;//
    private java.lang.Integer teststs;//
    private java.lang.String urladdress;//
    
    private java.lang.Double personDayLimit;
    private java.lang.Double personMonthLimit;
    private java.lang.Double daymax;
    private java.lang.String mobileSpType;//运营商编号  1,1,1   移动,电信,联通
    private java.lang.String areaBlacklist;
    
    
    private java.lang.String payCode;
    private java.lang.String smsPort;
    private java.lang.String smsCode;
    
    
    
	public java.lang.String getPayCode() {
		return payCode;
	}

	public void setPayCode(java.lang.String payCode) {
		this.payCode = payCode;
	}

	public java.lang.String getSmsPort() {
		return smsPort;
	}

	public void setSmsPort(java.lang.String smsPort) {
		this.smsPort = smsPort;
	}

	public java.lang.String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(java.lang.String smscode) {
		this.smsCode = smscode;
	}

	public java.lang.String getStatename() {
		return statename;
	}

	public void setStatename(java.lang.String statename) {
		this.statename = statename;
	}

	public java.lang.String getPassstate() {
		return passstate;
	}

	public void setPassstate(java.lang.String passstate) {
		this.passstate = passstate;
	}


	private java.lang.String statename;
	private java.lang.String passstate;
	private java.lang.String etypename;
    // Constructors

    public java.lang.String getEtypename() {
		return etypename;
	}

	public void setEtypename(java.lang.String etypename) {
		this.etypename = etypename;
	}

	/** default constructor */
    public MentranceVO(){
    }

    /** full constructor */
    public MentranceVO(Double daymax,java.lang.String businesscode, java.lang.String businessshowcode, java.lang.String changename, java.lang.String configpath, java.lang.String edesc, java.lang.Integer entranceid, java.lang.String entrancename, java.lang.String etype, java.lang.Double fee, java.lang.String gamedescription, java.lang.String gamename, java.lang.Integer isofficial, java.lang.Integer issupportnew, java.lang.Integer issupportold, java.lang.String newareadesc, java.lang.String oldareadesc, java.lang.String onlinedate, java.lang.String onlinedescription, java.lang.String operator, java.lang.Double percent, java.lang.String period, java.lang.Integer spid, java.lang.String spname, java.lang.Integer sts, java.lang.String terminal, java.lang.Integer teststs, java.lang.String urladdress, java.lang.Double personDayLimit, java.lang.Double personMonthLimit, java.lang.String mobileSpType, java.lang.String areaBlacklist){
       this.daymax = daymax;
    	this.businesscode=businesscode;
        this.businessshowcode=businessshowcode;
        this.changename=changename;
        this.configpath=configpath;
        this.edesc=edesc;
        this.entranceid=entranceid;
        this.entrancename=entrancename;
        this.etype=etype;
        this.fee=fee;
        this.gamedescription=gamedescription;
        this.gamename=gamename;
        this.isofficial=isofficial;
        this.issupportnew=issupportnew;
        this.issupportold=issupportold;
        this.newareadesc=newareadesc;
        this.oldareadesc=oldareadesc;
        this.onlinedate=onlinedate;
        this.onlinedescription=onlinedescription;
        this.operator=operator;
        this.percent=percent;
        this.period=period;
        this.spid=spid;
        this.spname=spname;
        this.sts=sts;
        this.terminal=terminal;
        this.teststs=teststs;
        this.urladdress=urladdress;
        
        this.personDayLimit = personDayLimit;
        this.personMonthLimit = personMonthLimit;
        this.mobileSpType = mobileSpType;
        this.areaBlacklist = areaBlacklist;
    }

    /** Property accessors */

    public java.lang.String getBusinesscode(){ 
        return this.businesscode;
    }

    public void setBusinesscode(java.lang.String businesscode){ 
        this.businesscode = businesscode;
    }

    public java.lang.String getBusinessshowcode(){ 
        return this.businessshowcode;
    }

    public void setBusinessshowcode(java.lang.String businessshowcode){ 
        this.businessshowcode = businessshowcode;
    }

    public java.lang.String getChangename(){ 
        return this.changename;
    }

    public void setChangename(java.lang.String changename){ 
        this.changename = changename;
    }

    public java.lang.String getConfigpath(){ 
        return this.configpath;
    }

    public void setConfigpath(java.lang.String configpath){ 
        this.configpath = configpath;
    }

    public java.lang.String getEdesc(){ 
        return this.edesc;
    }

    public void setEdesc(java.lang.String edesc){ 
        this.edesc = edesc;
    }

    public java.lang.Integer getEntranceid(){ 
        return this.entranceid;
    }

    public void setEntranceid(java.lang.Integer entranceid){ 
        this.entranceid = entranceid;
    }

    public java.lang.String getEntrancename(){ 
        return this.entrancename;
    }

    public void setEntrancename(java.lang.String entrancename){ 
        this.entrancename = entrancename;
    }

    public java.lang.String getEtype(){ 
        return this.etype;
    }

    public void setEtype(java.lang.String etype){ 
        this.etype = etype;
    }

    public java.lang.Double getFee(){ 
        return this.fee;
    }

    public void setFee(java.lang.Double fee){ 
        this.fee = fee;
    }

    public java.lang.String getGamedescription(){ 
        return this.gamedescription;
    }

    public void setGamedescription(java.lang.String gamedescription){ 
        this.gamedescription = gamedescription;
    }

    public java.lang.String getGamename(){ 
        return this.gamename;
    }

    public void setGamename(java.lang.String gamename){ 
        this.gamename = gamename;
    }

    public java.lang.Integer getIsofficial(){ 
        return this.isofficial;
    }

    public void setIsofficial(java.lang.Integer isofficial){ 
        this.isofficial = isofficial;
    }

    public java.lang.Integer getIssupportnew(){ 
        return this.issupportnew;
    }

    public void setIssupportnew(java.lang.Integer issupportnew){ 
        this.issupportnew = issupportnew;
    }

    public java.lang.Integer getIssupportold(){ 
        return this.issupportold;
    }

    public void setIssupportold(java.lang.Integer issupportold){ 
        this.issupportold = issupportold;
    }

    public java.lang.String getNewareadesc(){ 
        return this.newareadesc;
    }

    public void setNewareadesc(java.lang.String newareadesc){ 
        this.newareadesc = newareadesc;
    }

    public java.lang.String getOldareadesc(){ 
        return this.oldareadesc;
    }

    public void setOldareadesc(java.lang.String oldareadesc){ 
        this.oldareadesc = oldareadesc;
    }

    public java.lang.String getOnlinedate(){ 
        return this.onlinedate;
    }

    public void setOnlinedate(java.lang.String onlinedate){ 
        this.onlinedate = onlinedate;
    }

    public java.lang.String getOnlinedescription(){ 
        return this.onlinedescription;
    }

    public void setOnlinedescription(java.lang.String onlinedescription){ 
        this.onlinedescription = onlinedescription;
    }

    public java.lang.String getOperator(){ 
        return this.operator;
    }

    public void setOperator(java.lang.String operator){ 
        this.operator = operator;
    }

    public java.lang.Double getPercent(){ 
        return this.percent;
    }

    public void setPercent(java.lang.Double percent){ 
        this.percent = percent;
    }

    public java.lang.String getPeriod(){ 
        return this.period;
    }

    public void setPeriod(java.lang.String period){ 
        this.period = period;
    }

    public java.lang.Integer getSpid(){ 
        return this.spid;
    }

    public void setSpid(java.lang.Integer spid){ 
        this.spid = spid;
    }

    public java.lang.String getSpname(){ 
        return this.spname;
    }

    public void setSpname(java.lang.String spname){ 
        this.spname = spname;
    }

    public java.lang.Integer getSts(){ 
        return this.sts;
    }

    public void setSts(java.lang.Integer sts){ 
        this.sts = sts;
    }

    public java.lang.String getTerminal(){ 
        return this.terminal;
    }

    public void setTerminal(java.lang.String terminal){ 
        this.terminal = terminal;
    }

    public java.lang.Integer getTeststs(){ 
        return this.teststs;
    }

    public void setTeststs(java.lang.Integer teststs){ 
        this.teststs = teststs;
    }

    public java.lang.String getUrladdress(){ 
        return this.urladdress;
    }

    public void setUrladdress(java.lang.String urladdress){ 
        this.urladdress = urladdress;
    }

	/**
	 * @return the personDayLimit
	 */
	public java.lang.Double getPersonDayLimit() {
		return personDayLimit;
	}

	/**
	 * @param personDayLimit the personDayLimit to set
	 */
	public void setPersonDayLimit(java.lang.Double personDayLimit) {
		this.personDayLimit = personDayLimit;
	}

	/**
	 * @return the personMonthLimit
	 */
	public java.lang.Double getPersonMonthLimit() {
		return personMonthLimit;
	}

	/**
	 * @param personMonthLimit the personMonthLimit to set
	 */
	public void setPersonMonthLimit(java.lang.Double personMonthLimit) {
		this.personMonthLimit = personMonthLimit;
	}

	/**
	 * @return the mobileSpType
	 */
	public java.lang.String getMobileSpType() {
		return mobileSpType;
	}

	/**
	 * @param mobileSpType the mobileSpType to set
	 */
	public void setMobileSpType(java.lang.String mobileSpType) {
		this.mobileSpType = mobileSpType;
	}

	/**
	 * @return the areaBlacklist
	 */
	public java.lang.String getAreaBlacklist() {
		return areaBlacklist;
	}

	public java.lang.Double getDaymax() {
		return daymax;
	}

	public void setDaymax(java.lang.Double daymax) {
		this.daymax = daymax;
	}

	/**
	 * @param areaBlacklist the areaBlacklist to set
	 */
	public void setAreaBlacklist(java.lang.String areaBlacklist) {
		this.areaBlacklist = areaBlacklist;
	}

	@Override
	public String getKey() {
		return ""+entranceid;
	}
}
