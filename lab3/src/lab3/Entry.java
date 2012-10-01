package lab3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import lab3.server.Server;
import lab3.ui.mainframe.MainFrame;

public class Entry 
{
   
    
    private static void RunServer() throws IOException
    {
        System.out.println("press a key to stop server");
        
        Server.start();
        System.in.read();
        
        Server.stop();
    }
    
    
    
    public static void main(String[] args) throws IOException
    {
        RunServer();
    }
}
