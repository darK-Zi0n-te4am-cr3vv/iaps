/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3.net.data;

import java.io.*;

/**
 *
 * @author C.c
 */
public class ResponseStreamer 
{
    public static NetResponse ReadNetResponse(InputStream stream) 
            throws IOException
    {
        DataInputStream dataInputStream = new DataInputStream(stream);
        
        boolean pointStatus = dataInputStream.readBoolean();
      
        return new NetResponse(pointStatus);
    }
    
    
    public static void WriteNetResponse(NetResponse response, OutputStream stream) 
            throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(stream);
        
        dataOutputStream.writeBoolean(response.getPointStatus());
    }    
}
