package lab2.ui.controls;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xList 
    extends xBase
{
    private final List _list;
    private double[] _values;
    
    public xList(String name, double[] values)
    {
        super(name);
        
        _list = new List(2);
        _values = values;
        
        for (double val : values) 
        {
           _list.add(Double.toString(val));
        }
        
       
        _list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                notifyValueChanged(_values[_list.getSelectedIndex()]);
            }
        });
        
        _panel.add(_list);
    }
}
