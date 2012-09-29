package lab3.core;

public interface IArea 
{
    boolean checkPoint(Point pt);
    void apply(IAreaVisitor visitor);
}
