package lab2.ui.controls;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class xChoice 
    extends xBase
{
    private Choice _choice;
    private final double[] _values;
    
    public xChoice(String name, double[] values)
    {
        super(name);
        
        _choice = new Choice();
        _values = values;
        
        for (double val : values) 
        {
           _choice.add(Double.toString(val));
        }
        
        _choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                notifyValueChanged(_values[_choice.getSelectedIndex()]);
            }
        });
        
        _panel.add(_choice);
    }
}
