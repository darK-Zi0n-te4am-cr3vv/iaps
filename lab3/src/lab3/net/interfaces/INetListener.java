package lab3.net.interfaces;

import lab3.net.exceptions.ListenerStartException;

public interface INetListener 
{
    void StartAt(int port) 
            throws ListenerStartException;
    
    void SetAcceptor(IRequestHandler acceptor);
    
    
    void Stop();
}
