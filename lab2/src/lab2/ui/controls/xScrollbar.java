/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.ui.controls;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import lab2.ui.controls.xBase;


/**
 *
 * @author C.c
 */
public class xScrollbar
    extends xBase
{
    private final java.awt.Scrollbar _scrollbar;
    
    public xScrollbar(String name, int min, int max)
    {
        super(name);
        
        _scrollbar = new java.awt.Scrollbar(java.awt.Scrollbar.HORIZONTAL, 0, 1, min, max);
        _scrollbar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) 
            {
                notifyValueChanged(e.getValue());
            }
        });
        
        _panel.add(_scrollbar);
    }
    
    
}
