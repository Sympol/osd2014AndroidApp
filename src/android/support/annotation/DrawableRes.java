package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD})
public @interface DrawableRes
{
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.annotation.DrawableRes
 * JD-Core Version:    0.6.2
 */