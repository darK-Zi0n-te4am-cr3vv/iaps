package lab4.ui.mainframe;

import lab4.localisation.LocalisationBase;
import lab4.localisation.annotations.En;
import lab4.localisation.annotations.Ru;


public class Localisation
    extends LocalisationBase<Localisation>
{
    @En("Ok") 
    @Ru("Ок")
    public String pointInArea;

    @En("X=") 
    @Ru("Икс=")
    public String Xeq;

    @En("Y=") 
    @Ru("У=")
    public String Yeq;

    @En("R=") 
    @Ru("Эр=")
    public String Req;
    
    @Ru("Буйнов К.А. Раевский С.С. вариант 341")
    @En("Buynov K.A. Raevskiy S.S. variant 341")
    public String atuthorsAndTask;

    @Ru("Лабораторная работа №4")
    @En("Lab #4")
    public String windowTitle;
    
    @Ru("Выбор языка:")
    @En("Select language:")
    public String selectLanguage;
    
    @Ru("Текущая дата и время:")
    @En("Current date and time:")
    public String currentDate;
    
}
