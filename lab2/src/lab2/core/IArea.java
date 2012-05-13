package lab2.core;

public interface IArea 
{
    boolean checkPoint(Point pt);
    void apply(IAreaVisitor visitor);
}
