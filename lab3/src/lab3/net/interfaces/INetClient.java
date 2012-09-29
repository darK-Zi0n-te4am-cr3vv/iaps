package lab3.net.interfaces;

import lab3.net.data.NetRequest;
import lab3.net.data.NetResponse;
import lab3.net.exceptions.CommunicationException;

public interface INetClient 
{
    void SetServerAddress(String host, int port);
    NetResponse SendRequest(NetRequest req) throws CommunicationException;
}