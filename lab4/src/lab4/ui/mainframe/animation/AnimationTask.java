package lab4.ui.mainframe.animation;

import java.awt.Graphics;

public abstract class AnimationTask
{
    public enum StepResult
    {
        STOP,
        CONTINUE
    }
    
    private long _millsPassed = 0;
    
    StepResult shedulerStep(Graphics graphics, long millsDelta)
    {
        return step(graphics, millsDelta, _millsPassed+=millsDelta);
    }
    
    
    protected abstract StepResult step(Graphics graphics, long delta, long totalMills);
}
