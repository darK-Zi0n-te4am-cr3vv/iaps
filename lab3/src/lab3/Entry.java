package lab3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import lab3.server.Server;
import lab3.ui.mainframe.MainFrame;

public class Entry 
{
    private static void PrintUsage()
    {
        System.err.print("Usage:\n");
        System.err.print("\tlab3 --client [host]\n");
        System.err.print("\tlab3 --server\n");
        System.err.print("\tlab3 --combined\n");
        
        System.exit(1);
    }
    
    private static void RunServer() throws IOException
    {
        System.out.println("press a key to stop server");
        
        Server.start();
        System.in.read();
        
        Server.stop();
    }
    
    public static void Main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            PrintUsage();
        }
        
        String mode = args[0].toLowerCase();
        
        if (mode.equals("--server"))
        {
            RunServer();
            return;
        }
     
        else if (mode.equals("--client"))
        {
            if (args.length == 2)
            {
                String host = args[1];
                MainFrame frame = new MainFrame(host);
                
                return;
            }
            else
            {
                PrintUsage();
            }
        }
        
        else if (mode.equals("--combined"))
        {
            Server.start();
            
            MainFrame frame = new MainFrame("127.0.0.1");
            frame.addWindowListener(new WindowAdapter() {
                @Override public void windowClosed(WindowEvent e)
                {
                    Server.stop();
                }
            });
         
            return;
        }
        
        PrintUsage();
    }
    
    public static void main(String[] args) throws IOException
    {
        args = new String[]
        {
            "--combined"
        };
        
        Main(args);
    }
}
