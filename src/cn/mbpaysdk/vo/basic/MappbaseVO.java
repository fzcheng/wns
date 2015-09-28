package cn.mbpaysdk.vo.basic;

import cn.game.vo.basic.BasicVO;

public class MappbaseVO extends BasicVO implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4306958244598131963L;
	// Fields
    private java.lang.Integer activetimes;//
    private java.lang.String adder;//
    private java.lang.String addtime;//
    private java.lang.String apkpath;//
    private java.lang.String appdesc;//
    private java.lang.Integer appid;//
    private java.lang.String appname;//
    private java.lang.Double appprice;//
    private java.lang.String bannerpath;//
    private java.lang.String apppics;//组图
    private java.lang.String classid;//
    private java.lang.Integer comandlvl;//
    private java.lang.Integer commontimes;//
    private java.lang.Integer developerid;//
    private java.lang.String developname;//
    private java.lang.Integer downtimes;//
    private java.lang.Integer filesize;//
    private java.lang.Integer hotlvl;//
    private java.lang.String iconpath;//
    private java.lang.String languages;//
    private java.lang.String latestversion;//
    private java.lang.String memo;//
    private java.lang.String pacagename;//
    private java.lang.Integer readtimes;//
    private java.lang.Integer scorelvl;//
    private java.lang.Integer scoretimes;//
    private java.lang.Integer setuptimes;//
    private java.lang.Integer sts;//
    private java.lang.Integer autoaddversion;//
    private java.lang.String payNotifyUrl; // 支付通知url
    

    private java.lang.Integer ispushOpen;// 是否打开广告推送
    private java.lang.String pushstopcanals; // 屏蔽广告的渠道
    
    private Integer isOwn; //-- 是否自有应用0：关闭，1：开启。
    private Integer isPayable; //-- 是否开启支付0：关闭，1：开启。
    private Integer isCheckUserBlacklist; //-- 是否校验用户黑名单0：关闭，1：开启。
    private Integer isCheckUserDayQuota; //-- 是否校验用户日限额0：关闭，1：开启。
    private Integer isCheckAreaBlacklist; //--是否校验地区黑名单0：关闭，1：开启。
    private Integer isCheckAreaDayQuota; //-- 是否校验地区日限额0：关闭，1：开启。
    private Integer isCheckEntrDayQuota; //-- 是否校验通道日限额0：关闭，1：开启。

    

    
    public Integer getIsOwn() {
		return (isOwn == null) ? 0 : isOwn;
	}

	public void setIsOwn(Integer isOwn) {
		this.isOwn = isOwn;
	}

	public Integer getIsPayable() {
		return (isPayable == null) ? 0 : isPayable;
	}

	public void setIsPayable(Integer isPayable) {
		this.isPayable = isPayable;
	}

	public Integer getIsCheckUserBlacklist() {
		return (isCheckUserBlacklist == null) ? 0 : isCheckUserBlacklist;
	}

	public void setIsCheckUserBlacklist(Integer isCheckUserBlacklist) {
		this.isCheckUserBlacklist = isCheckUserBlacklist;
	}

	public Integer getIsCheckUserDayQuota() {
		return (isCheckUserDayQuota == null) ? 0 : isCheckUserDayQuota;
	}

	public void setIsCheckUserDayQuota(Integer isCheckUserDayQuota) {
		this.isCheckUserDayQuota = isCheckUserDayQuota;
	}

	public Integer getIsCheckAreaBlacklist() {
		return (isCheckAreaBlacklist == null) ? 0 : isCheckAreaBlacklist;
	}

	public void setIsCheckAreaBlacklist(Integer isCheckAreaBlacklist) {
		this.isCheckAreaBlacklist = isCheckAreaBlacklist;
	}

	public Integer getIsCheckAreaDayQuota() {
		return (isCheckAreaDayQuota == null) ? 0 : isCheckAreaDayQuota;
	}

	public void setIsCheckAreaDayQuota(Integer isCheckAreaDayQuota) {
		this.isCheckAreaDayQuota = isCheckAreaDayQuota;
	}

	public Integer getIsCheckEntrDayQuota() {
		return (isCheckEntrDayQuota == null) ? 0 : isCheckEntrDayQuota;
	}

	public void setIsCheckEntrDayQuota(Integer isCheckEntrDayQuota) {
		this.isCheckEntrDayQuota = isCheckEntrDayQuota;
	}

	public java.lang.Integer getAutoaddversion() {
		return autoaddversion;
	}

	public void setAutoaddversion(java.lang.Integer autoaddversion) {
		this.autoaddversion = autoaddversion;
	}

	private java.lang.String typename;//
    private java.lang.String upgratetime;//
    private java.lang.String version;//
    private java.lang.String isdelete;//
    private String signkeystore;

    // Constructors

    public String getSignkeystore() {
		return signkeystore;
	}

	public void setSignkeystore(String signkeystore) {
		this.signkeystore = signkeystore;
	}

	public java.lang.String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(java.lang.String isdelete) {
		this.isdelete = isdelete;
	}

	/** default constructor */
    public MappbaseVO(){
    }

    /** full constructor */
    public MappbaseVO(int ispushOpen,String pushstopcanals,int autoaddversion,String signkeystore,String apppics,java.lang.Integer activetimes, java.lang.String adder, java.lang.String addtime, java.lang.String apkpath, java.lang.String appdesc, java.lang.Integer appid, java.lang.String appname, java.lang.Double appprice, java.lang.String bannerpath, java.lang.String classid, java.lang.Integer comandlvl, java.lang.Integer commontimes, java.lang.Integer developerid, java.lang.String developname, java.lang.Integer downtimes, java.lang.Integer filesize, java.lang.Integer hotlvl, java.lang.String iconpath, java.lang.String languages, java.lang.String latestversion, java.lang.String memo, java.lang.String pacagename, java.lang.Integer readtimes, java.lang.Integer scorelvl, java.lang.Integer scoretimes, java.lang.Integer setuptimes, java.lang.Integer sts, java.lang.String typename, java.lang.String upgratetime, java.lang.String version, java.lang.String payNotifyUrl){
     this.ispushOpen =ispushOpen;
     this.pushstopcanals = pushstopcanals;
    	this.apppics = apppics;
       this.autoaddversion = autoaddversion;
       this.signkeystore= signkeystore;
    	this.activetimes=activetimes;
        this.adder=adder;
        this.addtime=addtime;
        this.apkpath=apkpath;
        this.appdesc=appdesc;
        this.appid=appid;
        this.appname=appname;
        this.appprice=appprice;
        this.bannerpath=bannerpath;
        this.classid=classid;
        this.comandlvl=comandlvl;
        this.commontimes=commontimes;
        this.developerid=developerid;
        this.developname=developname;
        this.downtimes=downtimes;
        this.filesize=filesize;
        this.hotlvl=hotlvl;
        this.iconpath=iconpath;
        this.languages=languages;
        this.latestversion=latestversion;
        this.memo=memo;
        this.pacagename=pacagename;
        this.readtimes=readtimes;
        this.scorelvl=scorelvl;
        this.scoretimes=scoretimes;
        this.setuptimes=setuptimes;
        this.sts=sts;
        this.typename=typename;
        this.upgratetime=upgratetime;
        this.version=version;
        this.payNotifyUrl = payNotifyUrl;
    }

    /** Property accessors */

    public java.lang.Integer getActivetimes(){ 
        return this.activetimes;
    }

    public void setActivetimes(java.lang.Integer activetimes){ 
        this.activetimes = activetimes;
    }

    public java.lang.String getAdder(){ 
        return this.adder;
    }

    public void setAdder(java.lang.String adder){ 
        this.adder = adder;
    }

    public java.lang.String getAddtime(){ 
        return this.addtime;
    }

    public void setAddtime(java.lang.String addtime){ 
        this.addtime = addtime;
    }

    public java.lang.String getApkpath(){ 
        return this.apkpath;
    }

    public void setApkpath(java.lang.String apkpath){ 
        this.apkpath = apkpath;
    }

    public java.lang.String getAppdesc(){ 
        return this.appdesc;
    }

    public void setAppdesc(java.lang.String appdesc){ 
        this.appdesc = appdesc;
    }

    public java.lang.Integer getAppid(){ 
        return this.appid;
    }

    public void setAppid(java.lang.Integer appid){ 
        this.appid = appid;
    }

    public java.lang.String getAppname(){ 
        return this.appname;
    }

    public void setAppname(java.lang.String appname){ 
        this.appname = appname;
    }

    public java.lang.Double getAppprice(){ 
        return this.appprice;
    }

    public void setAppprice(java.lang.Double appprice){ 
        this.appprice = appprice;
    }

    public java.lang.String getBannerpath(){ 
        return this.bannerpath;
    }

    public void setBannerpath(java.lang.String bannerpath){ 
        this.bannerpath = bannerpath;
    }

    public java.lang.String getClassid(){ 
        return this.classid;
    }

    public void setClassid(java.lang.String classid){ 
        this.classid = classid;
    }

    public java.lang.Integer getComandlvl(){ 
        return this.comandlvl;
    }

    public void setComandlvl(java.lang.Integer comandlvl){ 
        this.comandlvl = comandlvl;
    }

    public java.lang.Integer getCommontimes(){ 
        return this.commontimes;
    }

    public void setCommontimes(java.lang.Integer commontimes){ 
        this.commontimes = commontimes;
    }

    public java.lang.Integer getDeveloperid(){ 
        return this.developerid;
    }

    public void setDeveloperid(java.lang.Integer developerid){ 
        this.developerid = developerid;
    }

    public java.lang.String getDevelopname(){ 
        return this.developname;
    }

    public void setDevelopname(java.lang.String developname){ 
        this.developname = developname;
    }

    public java.lang.Integer getDowntimes(){ 
        return this.downtimes;
    }

    public void setDowntimes(java.lang.Integer downtimes){ 
        this.downtimes = downtimes;
    }

    public java.lang.Integer getFilesize(){ 
        return this.filesize;
    }

    public void setFilesize(java.lang.Integer filesize){ 
        this.filesize = filesize;
    }

    public java.lang.Integer getHotlvl(){ 
        return this.hotlvl;
    }

    public void setHotlvl(java.lang.Integer hotlvl){ 
        this.hotlvl = hotlvl;
    }

    public java.lang.String getIconpath(){ 
        return this.iconpath;
    }

    public void setIconpath(java.lang.String iconpath){ 
        this.iconpath = iconpath;
    }

    public java.lang.String getLanguages(){ 
        return this.languages;
    }

    public void setLanguages(java.lang.String languages){ 
        this.languages = languages;
    }

    public java.lang.String getLatestversion(){ 
        return this.latestversion;
    }

    public void setLatestversion(java.lang.String latestversion){ 
        this.latestversion = latestversion;
    }

    public java.lang.String getMemo(){ 
        return this.memo;
    }

    public void setMemo(java.lang.String memo){ 
        this.memo = memo;
    }

    public java.lang.String getPacagename(){ 
        return this.pacagename;
    }

    public void setPacagename(java.lang.String pacagename){ 
        this.pacagename = pacagename;
    }

    public java.lang.Integer getReadtimes(){ 
        return this.readtimes;
    }

    public void setReadtimes(java.lang.Integer readtimes){ 
        this.readtimes = readtimes;
    }

    public java.lang.Integer getScorelvl(){ 
        return this.scorelvl;
    }

    public void setScorelvl(java.lang.Integer scorelvl){ 
        this.scorelvl = scorelvl;
    }

    public java.lang.Integer getScoretimes(){ 
        return this.scoretimes;
    }

    public void setScoretimes(java.lang.Integer scoretimes){ 
        this.scoretimes = scoretimes;
    }

    public java.lang.Integer getSetuptimes(){ 
        return this.setuptimes;
    }

    public void setSetuptimes(java.lang.Integer setuptimes){ 
        this.setuptimes = setuptimes;
    }

    public java.lang.Integer getSts(){ 
        return this.sts;
    }

    public void setSts(java.lang.Integer sts){ 
        this.sts = sts;
    }

    public java.lang.String getTypename(){ 
        return this.typename;
    }

    public void setTypename(java.lang.String typename){ 
        this.typename = typename;
    }

    public java.lang.String getUpgratetime(){ 
        return this.upgratetime;
    }

    public void setUpgratetime(java.lang.String upgratetime){ 
        this.upgratetime = upgratetime;
    }

    public java.lang.String getVersion(){ 
        return this.version;
    }

    public void setVersion(java.lang.String version){ 
        this.version = version;
    }

	public java.lang.String getApppics() {
		return apppics;
	}

	public void setApppics(java.lang.String apppics) {
		this.apppics = apppics;
	}

	/**
	 * @return the payNotifyUrl
	 */
	public java.lang.String getPayNotifyUrl() {
		return payNotifyUrl;
	}

	/**
	 * @param payNotifyUrl the payNotifyUrl to set
	 */
	public void setPayNotifyUrl(java.lang.String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}

	public java.lang.Integer getIspushOpen() {
		return ispushOpen;
	}

	public void setIspushOpen(java.lang.Integer ispushOpen) {
		this.ispushOpen = ispushOpen;
	}

	public java.lang.String getPushstopcanals() {
		return pushstopcanals;
	}

	public void setPushstopcanals(java.lang.String pushstopcanals) {
		this.pushstopcanals = pushstopcanals;
	}

	@Override
	public String getKey() {
		return ""+appid;
	}

}
