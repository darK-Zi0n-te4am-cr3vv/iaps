package lab4.core;

public interface IAreaVisitor 
{
    public void Visit(qCircle aThis);
    public void Visit(qRect aThis);
    public void Visit(qTriangle aThis);   
}
