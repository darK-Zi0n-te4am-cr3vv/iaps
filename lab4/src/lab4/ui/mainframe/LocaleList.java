
package lab4.ui.mainframe;

import java.awt.Choice;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

public class LocaleList extends Choice
    {
       public interface LocaleListListener
        {
            void localeChanged(Locale locale);
        }

    
   
        public LocaleList(final LocaleListListener lll, final Locale... locales)
        {
            for (Locale loc : locales)
            {
                add(loc.getDisplayLanguage());
            }
            
            addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                
                    lll.localeChanged(locales[getSelectedIndex()]);
                
                }
            });
            
        }
        
        
}
