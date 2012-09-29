package lab3.net.data;

public class NetResponse 
{
    boolean _pointStatus;
    
    public NetResponse(boolean pointStatus)
    {
        _pointStatus = pointStatus;
    }
    
    public boolean getPointStatus()
    {
        return _pointStatus;
    }
}
