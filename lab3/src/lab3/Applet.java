package lab3;

import javax.swing.JFrame;
import lab3.ui.mainframe.MainFrame;

public class Applet
    extends java.applet.Applet
{
    @Override public void init()
    {
        MainFrame frame = new MainFrame("127.0.0.1");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
