package lab4.core;

public interface IArea 
{
    boolean checkPoint(Point pt);
    void apply(IAreaVisitor visitor);
}
