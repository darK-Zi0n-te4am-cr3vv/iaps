package lab3.net.data;

import java.io.*;

public class RequestStreamer 
{
    public static NetRequest ReadNetRequest(InputStream stream) 
            throws IOException
    {
        DataInputStream dataInputStream = new DataInputStream(stream);
        
        double x = dataInputStream.readDouble();
        double y = dataInputStream.readDouble();
        double r = dataInputStream.readDouble();
        int seed = dataInputStream.readInt();
        
        return new NetRequest(x, y, r, seed);
    }
    
    
    public static void WriteNetRequest(NetRequest request, OutputStream stream) 
            throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(stream);
        
        dataOutputStream.writeDouble(request.getX());
        dataOutputStream.writeDouble(request.getY());
        dataOutputStream.writeDouble(request.getR());
        
        dataOutputStream.writeInt(request.getSeed());
    }
}
