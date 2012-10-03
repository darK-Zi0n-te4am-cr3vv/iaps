package lab4.ui.mainframe.animation;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimationScheduler
{
    private List<AnimationTask> _tasks = Collections.synchronizedList(new ArrayList<AnimationTask>());
    
    private long _stepDelayMills ;
    public long getStepDelay()
    {
        return _stepDelayMills;
    }
    
    
    private Component _component;
    public Component getComponent()
    {
        return _component;
    }
    
    private Thread _schedullerThread;
    private Thread getSchedullerThread()
    {
        return _schedullerThread;
    }
    
    public AnimationScheduler(Component component, long stepDelayMills)
    {
        _component = component;
        _stepDelayMills = stepDelayMills;
        
        _schedullerThread = new Thread(new SchedulerThread());
    }
    
    private class SchedulerThread
        implements Runnable
    {
        private boolean sleep()
        {
            try 
            {
                Thread.sleep(getStepDelay());
                return true;
            } 
            catch (InterruptedException ex) 
            {
                return false;
            }
        }
        
        
        @Override public void run()
        {
           while(sleep())
           {
               Image img = new BufferedImage(getComponent().getWidth(),
                       getComponent().getHeight(), BufferedImage.TYPE_INT_RGB);
               
               
               int i = 0;
                while (i < _tasks.size())
                {
                    AnimationTask task = _tasks.get(i);

                    if (task.shedulerStep(img.getGraphics(), getStepDelay())==AnimationTask.StepResult.STOP)
                    {
                        _tasks.remove(i);
                    }
                    else 
                    {
                        i++;
                    }
                }
                
               Graphics g = getComponent().getGraphics();
               if (g!=null)                
                g.drawImage(img, 0, 0, getComponent());
           }
        }
        
        
        
    }

    
    public void addTask(AnimationTask task)
    {
        _tasks.add(task);
        
        if (getSchedullerThread().getState() == Thread.State.NEW)
        {
            getSchedullerThread().start();
        }
    }
    
    public void stop()
    {
        _schedullerThread.interrupt();
    }
}
