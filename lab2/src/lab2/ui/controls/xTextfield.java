package lab2.ui.controls;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class xTextfield
    extends xBase
{
    public xTextfield(String name, double min, double max)
    {
        super(name);
        
        JTextField tf = new JTextField(7);
        tf.getDocument().addDocumentListener(new TfDocumentListener(min, max, tf));
        
        _panel.add(tf);
    }
    
    class TfDocumentListener 
        implements DocumentListener
    {
        private final double _min;
        private final double _max;
        private final JTextField _tf;
        
        public TfDocumentListener(double min, double max, JTextField tf)
        {
            _min = min;
            _max = max;
            _tf = tf;
        }
       
        private void parse(DocumentEvent e)
        {
            double input;
            
            try 
            { 
                input = Double.parseDouble(_tf.getText()); 
            }
            catch(NumberFormatException ex)
            {
                return;
            }
            
            if (input < _min || input > _max) 
            {
                _tf.setForeground(Color.red);
                _tf.setToolTipText("Input must be between " 
                        + Double.toString(_min) 
                        + " and " 
                        + Double.toString(_max) 
                        + ".");
            }
            else
            {
                _tf.setToolTipText(null);
                _tf.setForeground(Color.black);
                
                notifyValueChanged(input);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            parse(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            parse(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            parse(e);
        }
       
    }
}
