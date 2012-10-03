package lab4.localisation.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Locale("en_EN|en_Us")
public @interface En 
{
    String value();
}