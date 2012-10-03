package lab4.localisation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Locale("ru_RU")
public @interface Ru
{
    String value();
}
