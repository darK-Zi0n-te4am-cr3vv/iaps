package lab4;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Format 
{
    public static double parseDouble(String str) throws ParseException
    {
        return NumberFormat.getInstance().parse(str).doubleValue();
    }
    
    public static String toString(double dbl)
    {
        return NumberFormat.getInstance().format(dbl);
    }
    
    public static String toString(Date dt)
    {
        return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(dt);
    }
}
