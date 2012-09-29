package lab3.net.interfaces;

import lab3.net.data.NetRequest;
import lab3.net.data.NetResponse;

public interface IRequestHandler 
{
    NetResponse OnRequest(NetRequest request);
}
