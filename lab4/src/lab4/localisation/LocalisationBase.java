package lab4.localisation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocalisationBase<T extends LocalisationBase>
{   
    private static Annotation getAnnotation(Field field,  Class a)
    {
       for (Annotation an : field.getAnnotations())
       {
           if (a.isAssignableFrom(an.getClass())) return an;
       }
       
       return null;
    }
    
    public void load(java.util.Locale loc) throws Exception
    {
        Class annotationClass = LocalisationMap.getAnnotationClass(loc.toString().toLowerCase());
        Method value = null;
        try 
        {
             value = annotationClass.getMethod("value", (Class[])null);
        } 
        catch (Exception ex) 
        {
            throw new Exception("error");
        }
        
        Class thiz = getClass();
        
        for (Field field : thiz.getDeclaredFields())
        {
            Annotation annotation = getAnnotation(field, annotationClass);
            
            if (annotation!=null)
            {
                String str =  (String)value.invoke(annotation, (Object[])null);
                field.set(this, str);
            }
        }
        
        notifyListeners();
    }
    
    private List<LocalisationListener<T>> _listeners = new ArrayList<LocalisationListener<T>>();
    public void addLocalisationListener(LocalisationListener<T> localisationListener)
    {
        _listeners.add(localisationListener);
        localisationListener.addChildListeners((T)this);
    }
    
    public void removeLocalisationListener(LocalisationListener<T> localisationListener)
    {
        _listeners.remove(localisationListener);
        localisationListener.removeChildListeners((T)this);
    }
    
    private void notifyListeners()
    {
        for (LocalisationListener<T> localisationListener : _listeners)
        {
            localisationListener.applyLocalisation((T)this);
        }
    }
    
}
