package lab4;

import java.awt.Color;
import java.util.Locale;

public class Const 
{
    public static final int WINDOW_SIZE_X = 900;
    public static final int WINDOW_SIZE_Y = 520;
    public static final int AREA_SIZE_X = 900;
    public static final int AREA_SIZE_Y = 400;

    public static final int LENGTH_X      = 430;
    public static final int LENGTH_Y      = 200;
    
    public static int INDENT        = 10;
    
    public static final int HALF_X        = AREA_SIZE_X / 2;
    public static final int HALF_Y        = AREA_SIZE_Y / 2;
    public static final int STEP          = 40; /* R/2 */
    public static final int RSTEP = STEP * 2;
    
    public static  int VARIANT = 340;
    
    public static final Color AREA_COLOR = new Color(51, 153, 255);
    public static final Color AXIS_COLOR = Color.BLACK;
    
    public static final Color IN_AREA_COLOR = Color.YELLOW;
    public static final Color NOT_IN_AREA_COLOR = Color.RED;
    public static final Color UNREACHABLE_COLOR = Color.GREEN;
    

    public static final String LOG_FILE = "points.log";
    
    public static final int COMMUNICATION_PORT = 8666;
    
    
    public static final Locale PRIMARY_LOCALE = new Locale("ru", "RU");
    public static final Locale ALTERNATIVE_LOCALE = new Locale("en", "US");
    
}
