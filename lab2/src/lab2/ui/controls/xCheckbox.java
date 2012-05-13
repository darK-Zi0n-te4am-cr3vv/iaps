package lab2.ui.controls;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class xCheckbox 
    extends xBase
{
    public xCheckbox(String name, double[] values)
    {
        super(name);
     
        CheckboxGroup cbg = new CheckboxGroup();
        for (double value : values)
        {
            Checkbox cb = new Checkbox(Double.toString(value), false, cbg);
            cb.addItemListener(new CbActionListener(value));
            
            _panel.add(cb);
        }
    }
    
    
     class CbActionListener 
        implements ItemListener
    {
        private final double _value;
        
        public CbActionListener(double value)
        {
            _value = value;
        }

        @Override
        public void itemStateChanged(ItemEvent e) 
        { 
            notifyValueChanged(_value);            
        }

        
    }
}
