package lab3.net.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;
import lab3.net.data.NetRequest;
import lab3.net.data.NetResponse;
import lab3.net.data.RequestStreamer;
import lab3.net.data.ResponseStreamer;
import lab3.net.exceptions.CommunicationException;
import lab3.net.interfaces.INetClient;

public class DgramClient 
    implements INetClient
{
    private static final int BUFFER_SIZE = 1024;
    private static final int SO_TIMEOUT = 1000;
    
    
    private int _port;
    private String _host;
    
    @Override public void SetServerAddress(String host, int port) 
    {
        _host = host;
        _port = port;
    }

    @Override public NetResponse SendRequest(NetRequest req) 
            throws CommunicationException 
    {
        DatagramSocket socket;
        try 
        {
             socket = new DatagramSocket();
        } 
        catch (SocketException ex) 
        {
            throw new CommunicationException("Error creating socket: " + ex.getMessage());
        }
        

        ByteArrayOutputStream stream = new ByteArrayOutputStream(BUFFER_SIZE);
        try
        {
            RequestStreamer.WriteNetRequest(req, stream);
        } 
        catch (IOException ex) 
        {
            throw new CommunicationException("Error writing request to buffer");
        }
        
        
        DatagramPacket packet;
        try 
        {
            byte[] buffer = stream.toByteArray();
            
            packet = new DatagramPacket(buffer, stream.size(), InetAddress.getByName(_host), _port);
        } 
        catch (UnknownHostException ex)
        {
            throw new CommunicationException("Host not found");
        }
        catch(Exception e)
        {
            throw new CommunicationException("fucking awesome " + e.toString());
        }
        
        try 
        {
            socket.send(packet);
        } 
        catch (IOException ex) 
        {
            throw new CommunicationException("Error sending packet to server: " + ex.getMessage());
        }
        try 
        {
            socket.setSoTimeout(SO_TIMEOUT);
        } 
        catch (SocketException ex) 
        {
            throw new CommunicationException("Error setting timeot: " + ex.getMessage());
        }

        
        byte[] responseBuffer = new byte[1];
        DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
        
        
        try 
        {
            socket.receive(response);
        } 
        catch(SocketTimeoutException ex)
        {
            return null;
        }
        catch (IOException ex) 
        {
            throw new CommunicationException("Error recieving: " + ex.getMessage());
        }
        
        ByteArrayInputStream resp = new ByteArrayInputStream(responseBuffer);
        try {
            return ResponseStreamer.ReadNetResponse(resp);
        } catch (IOException ex) {
            return null;
        }
    }

   
}
