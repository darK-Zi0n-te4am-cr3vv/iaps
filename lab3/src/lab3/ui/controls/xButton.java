package lab3.ui.controls;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xButton 
    extends xBase
{
    public xButton(String name, double[] values)
    {
        super(name);
     
        for (double value : values)
        {
            Button bt = new  Button(Double.toString(value));
            bt.addActionListener(new BtActionListener(value));
            
            _panel.add(bt);
            
        }
        
    }
    
    
    class BtActionListener 
        implements ActionListener
    {
        private final double _value;
        
        public BtActionListener(double value)
        {
            _value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            notifyValueChanged(_value);            
        }
    }
}
