package lab4;

import java.io.IOException;
import lab4.localisation.LocalisationMap;
import lab4.ui.mainframe.MainPanel;
import lab4.ui.mainframe.MainWindow;

public class Entry 
{
    public static void main(String[] args) throws IOException, Exception
    {
        LocalisationMap.registerStandartAnnotations();
        
        if (args.length!=0)
        {
            try
            {
                Const.VARIANT = Integer.parseInt(args[0]);
            }
            catch(Exception e)
            {
                ////
            }
        }
        
        MainWindow frame = new MainWindow();
    }
}
