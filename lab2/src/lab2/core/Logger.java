/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import lab2.Const;

/**
 *
 * @author C.c
 */
public class Logger 
{
    private PrintWriter _writer;
    
    public Logger()
    {
        try 
        {
            _writer = new PrintWriter(Const.LOG_FILE);
        } 
        catch (FileNotFoundException ex)
        {
            /* supress */
        }
        
    }
    
    public void log(double ptx, double pty, double r, boolean inArea)
    {
        if (_writer!=null)
        {
            if (inArea)
            {
                _writer.printf("In area: x=%f y=%f r=%f\n", ptx, pty, r);
            }
            else
            {
                _writer.printf("Not in area: x=%f y=%f r=%f\n", ptx, pty, r);
            }

            _writer.flush();
        }
    }
    
    @Override
    public void finalize() throws Throwable
    {
        super.finalize();
    
        if (_writer != null)
        {
            try
            {
                _writer.flush();
                _writer.close();
            }
            catch(Exception e)
            {
                /* supressed */
            }
        }
    }
    
}
