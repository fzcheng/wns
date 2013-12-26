package cn.game.pay.mmarket;



public class Order_Charge_Schema
{

    private String OrderID;
    
    private String StartTime;
    
    private String ExpiredTime;
    
    private String ValidTimes;
    
    private String UserID;
    
    
    public String getOrderID()
    {
    
        return OrderID;
    }

    
    public void setOrderID(String orderID)
    {
    
        OrderID = orderID;
    }

    
    public String getStartTime()
    {
    
        return StartTime;
    }

    
    public void setStartTime(String startTime)
    {
    
        StartTime = startTime;
    }

    
    public String getExpiredTime()
    {
    
        return ExpiredTime;
    }

    
    public void setExpiredTime(String expiredTime)
    {
    
        ExpiredTime = expiredTime;
    }

    
    public String getValidTimes()
    {
    
        return ValidTimes;
    }

    
    public void setValidTimes(String validTimes)
    {
    
        ValidTimes = validTimes;
    }

    
    public String getUserID()
    {
    
        return UserID;
    }

    
    public void setUserID(String userID)
    {
    
        UserID = userID;
    }

    
    public String getSPServiceID()
    {
    
        return SPServiceID;
    }

    
    public void setSPServiceID(String sPServiceID)
    {
    
        SPServiceID = sPServiceID;
    }

    private String SPServiceID;
}
