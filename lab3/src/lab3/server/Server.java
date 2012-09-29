package lab3.server;

import java.io.IOException;
import lab3.Const;
import lab3.core.IArea;
import lab3.core.Point;
import lab3.generation.GenerationContext;
import lab3.net.data.NetRequest;
import lab3.net.data.NetResponse;
import lab3.net.exceptions.ListenerStartException;
import lab3.net.interfaces.INetListener;
import lab3.net.interfaces.IRequestHandler;
import lab3.net.udp.DgramListener;

public class Server
{
   public final static int PORT = 8080;
    
    public Server()
    {
    }
    
    
    static INetListener _listener;
    public static void start() throws IOException
    {
        _listener = new DgramListener();
           
        _listener.SetAcceptor(new IRequestHandler() {
            @Override public NetResponse OnRequest(NetRequest request)  
            {
                GenerationContext gc = new GenerationContext(request.getSeed(), request.getR());
                boolean status = gc.createArea().checkPoint(new Point(request.getX(), request.getY()));

                return new NetResponse(status);
            }
        });
        
        try 
        {
            _listener.StartAt(Const.COMMUNICATION_PORT);
        }
        catch (ListenerStartException e)
        {
            System.out.printf("Error starting listener: %s", e.getMessage());
            return;
        }   
    }
    
    
    public static void stop()
    {
        if (_listener == null)
        {
           
        }
        
        else
        {
            _listener.Stop();
        }
    }
}