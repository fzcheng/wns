package cn.game.pay.mmarket;



public class AppOrderQueryReq
{

    private String MsgType;
    
    
    public String getMsgType()
    {
    
        return MsgType;
    }

    
    public void setMsgType(String msgType)
    {
    
        MsgType = msgType;
    }

    
    public String getVersion()
    {
    
        return Version;
    }

    
    public void setVersion(String version)
    {
    
        Version = version;
    }

    
    public Address_Info_Schema getSend_Address()
    {
    
        return Send_Address;
    }

    
    public void setSend_Address(Address_Info_Schema sendAddress)
    {
    
        Send_Address = sendAddress;
    }

    
    public Address_Info_Schema getDest_Address()
    {
    
        return Dest_Address;
    }

    
    public void setDest_Address(Address_Info_Schema destAddress)
    {
    
        Dest_Address = destAddress;
    }

    
    public String getAppType()
    {
    
        return AppType;
    }

    
    public void setAppType(String appType)
    {
    
        AppType = appType;
    }

    
    public String getMSISDN()
    {
    
        return MSISDN;
    }

    
    public void setMSISDN(String mSISDN)
    {
    
        MSISDN = mSISDN;
    }

    
    public String getPayCode()
    {
    
        return PayCode;
    }

    
    public void setPayCode(String payCode)
    {
    
        PayCode = payCode;
    }

    
    public String getProgramID()
    {
    
        return ProgramID;
    }

    
    public void setProgramID(String programID)
    {
    
        ProgramID = programID;
    }

    
    public String getOsID()
    {
    
        return OsID;
    }

    
    public void setOsID(String osID)
    {
    
        OsID = osID;
    }

    
    public String getTradeID()
    {
    
        return TradeID;
    }

    
    public void setTradeID(String tradeID)
    {
    
        TradeID = tradeID;
    }
    
    public String getAppID(){
        return AppID;
    }


    
    public void setAppID(String appID){
        AppID = appID;
    }

    private String Version;
    
    private Address_Info_Schema Send_Address;
    
    private Address_Info_Schema Dest_Address;
    
    private String AppType;
    
    private String MSISDN;
    
    private String AppID;
    
    private String PayCode;
    
    private String ProgramID;
    
    private String OsID;
    
    private String TradeID;
}
