package lab4.localisation;

import java.util.HashMap;
import lab4.localisation.annotations.En;
import lab4.localisation.annotations.Ru;

public class LocalisationMap 
{
    private static HashMap<String, Class> _annotations= new HashMap<String, Class>();
    
    public static <TAnnotation> void registerLocalisationAnnotation(Class<TAnnotation> clazz) throws Exception
    {
       lab4.localisation.annotations.Locale locale  =  
               (lab4.localisation.annotations.Locale)
               clazz.getAnnotation(lab4.localisation.annotations.Locale.class);
       
       if (locale==null)
       {
           throw new Exception("invalid annotation");
       }
       
       for (String loc : locale.value().split("\\|"))
       {
           _annotations.put(loc.toLowerCase(), clazz);
       }
    }
    
    public static Class getAnnotationClass(String locale)
    {
        return _annotations.get(locale.toLowerCase());
    }
    
    public static void registerStandartAnnotations() throws Exception
    {
        LocalisationMap.registerLocalisationAnnotation(Ru.class);
        LocalisationMap.registerLocalisationAnnotation(En.class);
    }
}
