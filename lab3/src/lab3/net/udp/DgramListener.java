package lab3.net.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab3.net.data.NetRequest;
import lab3.net.data.NetResponse;
import lab3.net.data.RequestStreamer;
import lab3.net.data.ResponseStreamer;
import lab3.net.exceptions.ListenerStartException;
import lab3.net.interfaces.INetListener;
import lab3.net.interfaces.IRequestHandler;

public class DgramListener 
    implements INetListener
{
    private IRequestHandler _acceptor;
    private DatagramSocket _socket;
    private Thread _listeningThread;
  
    @Override public void StartAt(int port) 
            throws ListenerStartException
    {
        try 
        {
            _socket = new DatagramSocket(port);
            
            _listeningThread = new Thread(new PacketReciever());
            _listeningThread.start();
        } 
        catch (SocketException ex) 
        {
            throw new ListenerStartException(ex.getMessage());
        }
        
        
    }

    @Override
    public void Stop() 
    {
        _socket.close();
        
        try 
        {
            _listeningThread.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(DgramListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class PacketReciever
        implements Runnable
    {
        private final static int BUFFER_SIZE = 1024;
        
        @Override public void run()
        {
            
            
           
            
            
            while (true)
            {
                byte[] buffer = new byte[BUFFER_SIZE];
           
                DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
                ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
            
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                
                try
                {
                    stream.reset();
                    _socket.receive(packet);
                    
                    NetResponse response = NotifyHandler(RequestStreamer.ReadNetRequest(stream));
                    
                    ResponseStreamer.WriteNetResponse(response, ostream);
                    
                    byte[] obuf =ostream.toByteArray();
                   
                    DatagramPacket opacket = new DatagramPacket(obuf, obuf.length, 
                            packet.getAddress(), packet.getPort());
                    
                    _socket.send(opacket);
                }
                catch(SocketException se)
                {
                    break;
                }
                catch(Exception e)
                {
                    /* supress */
                }
            }
        }
    }
    

    @Override public void SetAcceptor(IRequestHandler acceptor) 
    {
        _acceptor = acceptor;
    }
    
    synchronized private NetResponse NotifyHandler(NetRequest request)
    {
        if (_acceptor != null) 
        {
            return _acceptor.OnRequest(request);
        }
        
        return null;
    }
}
