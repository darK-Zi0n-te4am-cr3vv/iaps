package lab4.ui.mainframe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import lab4.ui.mainframe.animation.AnimationTask;

public class DotAnimationTask
    extends AnimationTask
{   
    enum DotState
    {
        START(0, 0),
        RISING(0, 1000),
        FADING(1000, 3000),
        DISAPEARING(3000, 10000),
        DISAPEARED(10000);
        
        private long _start;
        private long _end;
        
        
        private DotState(long start, long end)
        {
            _start = start;
            _end = end;
        }
        
        private DotState(long start)
        {
            _start = start;
            _end = -1;
        }
        
        private boolean checkMatch(long mills)
        {
         return ((mills >= _start) && (mills <= _end)) || ((mills >= _start) && (_end == -1)); 
        }
        
        public DotState getNextState(long mills) 
       {
           for (DotState state : getClass().getEnumConstants())
           {
               if (state.checkMatch(mills)) return state;
           }
           
           return null;
        }
        
        public double getPercent(long mills)
        {
            return ((double)(mills - _start)) / ((double) (_end- _start));
        }
    }
    
    interface PointProjectionProvider
    {
        Point project(double x, double y);
    }
    
    private final double BEGIN_POINT_SIZE = 10d;
    private final double MAX_POINT_SIZE = 20d; 
    
    private DotState _state = DotState.START;
   
    private double _x, _y;
    private Color _color;
    
    private PointProjectionProvider _ppp;
    
    public DotAnimationTask(Color color, double x, double y, PointProjectionProvider ppp)
    {
        _x = x;
        _y = y;
        
        _color = color;
        _ppp = ppp;
    }
    
    @Override protected StepResult step(Graphics graphics, long delta, long mills) 
    {
        double pointSize = 0d;
        _state = _state.getNextState(mills);
        
        switch(_state)
        {
            case START:
                    pointSize = BEGIN_POINT_SIZE;
                break;
                
            case RISING:
                    pointSize = BEGIN_POINT_SIZE   
                            + (MAX_POINT_SIZE -  BEGIN_POINT_SIZE)  * _state.getPercent(mills);
             break;
        
            case FADING:
                    pointSize = MAX_POINT_SIZE 
                            - (MAX_POINT_SIZE -  BEGIN_POINT_SIZE)  * _state.getPercent(mills);
                
                break;
                
            case DISAPEARING:
                   pointSize = BEGIN_POINT_SIZE;
                break;
                
               case DISAPEARED:
                   return StepResult.STOP;
                    
        }
        
        int pointSizeInt = (int)pointSize; 
        
        graphics.setColor(_color);
        
        Point pt = _ppp.project(_x, _y);
        graphics.fillOval(pt.x - pointSizeInt/2, pt.y - pointSizeInt/2, 
                pointSizeInt, pointSizeInt);
        
        
        return StepResult.CONTINUE;
    }
}
