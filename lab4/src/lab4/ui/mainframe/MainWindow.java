package lab4.ui.mainframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lab4.Const;
import lab4.Format;
import lab4.localisation.LocalisationListener;

public class MainWindow
    extends JFrame
    implements LocalisationListener<Localisation>
{
    private MainPanel _mainPanel = new MainPanel();
    private JPanel _upPanel = new JPanel();
    
    private Label _selectLang = new Label();
    private Label _currentDate = new Label();
    private Label _date = new Label();
    private Label _info = new Label();
    
    
    private LocaleList _langList;
    
    private Localisation _localisation = new Localisation();
    
    private Timer _timer = new Timer();
    
    public MainWindow() throws Exception
    {
        setLayout(new BorderLayout());
        
        LocaleList.LocaleListListener lll =new LocaleList.LocaleListListener() {
            @Override
            public void localeChanged(Locale locale) {
                try{
                changeLocale(locale);
                } catch(Exception ex)
                {}
            }
        };
        
        _langList = new LocaleList(lll, Const.PRIMARY_LOCALE, Const.ALTERNATIVE_LOCALE);
        
        _upPanel.setLayout(new BorderLayout());
        
        
        _date.setSize(100, 10);
        
        Panel pnl2 = new Panel();
        pnl2.setLayout(new FlowLayout());
        pnl2.add(_currentDate, FlowLayout.LEFT);
        pnl2.add(_date, FlowLayout.CENTER);
        
        Panel pnl = new Panel();
        pnl.add(_info);
        pnl.add(_selectLang);
        pnl.add(_langList);
        
        _upPanel.add(pnl2, BorderLayout.PAGE_START);
        _upPanel.add(pnl, BorderLayout.PAGE_END);
        
        
        add(_upPanel, BorderLayout.PAGE_START);
        add(_mainPanel, BorderLayout.PAGE_END);
    
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        
        setSize(Const.WINDOW_SIZE_X, Const.WINDOW_SIZE_Y);
        
        _localisation.addLocalisationListener(this); 
        
        
     
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                _mainPanel.close();
            }
        });
        
        
        
        _timer.schedule(new TimerTask(){

            @Override
            public void run() {
               _date.setText(Format.toString(new Date()));
            }
            
            
        }, 1000, 1000);
        
        setVisible(true);
        
        try 
        {
            changeLocale(Locale.getDefault());
        } 
        catch (Exception e)
        {
            changeLocale(Const.PRIMARY_LOCALE);
        }
        
    }
    
    void changeLocale(Locale locale) throws Exception
    {
        
            _localisation.load(locale);
            Locale.setDefault(locale);
            
            invalidate();
            validate();
    }
    
    @Override
    public void applyLocalisation(Localisation localisation)
    {
        _selectLang.setText(localisation.selectLanguage);
        _currentDate.setText(localisation.currentDate);
        _info.setText(localisation.atuthorsAndTask);
        
        setTitle(localisation.windowTitle);
    }

    @Override
    public void addChildListeners(Localisation localisation) 
    {
        localisation.addLocalisationListener(_mainPanel);
    }

    @Override
    public void removeChildListeners(Localisation localisation)
    {
        localisation.removeLocalisationListener(_mainPanel);
    }

}