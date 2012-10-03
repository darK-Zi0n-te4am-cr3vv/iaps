package lab3;

import javax.swing.JFrame;
import lab3.ui.mainframe.MainFrame;

public class Applet
    extends java.applet.Applet
{
    @Override public void init()
    {
       try {
            String variant = getParameter("variant");
            if (!(variant == null || variant.equals("")))
            {
                Const.VARIANT = Integer.parseInt(variant);
            }
        }
        catch(Exception e)
        {}
        
        
        MainFrame frame = new MainFrame("127.0.0.1");
        add(frame);
        
       // frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
