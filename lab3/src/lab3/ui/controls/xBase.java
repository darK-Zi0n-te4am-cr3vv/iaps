package lab3.ui.controls;

import java.awt.Label;
import javax.swing.JPanel;

public class xBase
    implements IxControl
{
    protected  IxControlListener _listener;
    protected JPanel _panel;

    public xBase(String controlName)
    {
        _panel = new JPanel();
        
        Label lb = new Label(controlName);
        _panel.add(lb);
    }
    
    protected void notifyValueChanged(double newValue)
    {
        if (_listener != null) 
            _listener.valueChanged(newValue);
    }
    
    @Override
    public void setListener(IxControlListener listener)
    {
        _listener = listener;
        
    }

    @Override
    public void setPanel(JPanel panel) 
    {
        panel.add(_panel);
    }
}
