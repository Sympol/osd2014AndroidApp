package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ICUCompatIcs
{
  private static final String TAG = "ICUCompatIcs";
  private static Method sAddLikelySubtagsMethod;
  private static Method sGetScriptMethod;

  static
  {
    try
    {
      Class localClass = Class.forName("libcore.icu.ICU");
      if (localClass != null)
      {
        sGetScriptMethod = localClass.getMethod("getScript", new Class[] { String.class });
        sAddLikelySubtagsMethod = localClass.getMethod("addLikelySubtags", new Class[] { String.class });
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.w("ICUCompatIcs", localException);
    }
  }

  public static String addLikelySubtags(String paramString)
  {
    try
    {
      if (sAddLikelySubtagsMethod != null)
      {
        Object[] arrayOfObject = { paramString };
        str = (String)sAddLikelySubtagsMethod.invoke(null, arrayOfObject);
        return str;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
      {
        Log.w("ICUCompatIcs", localIllegalAccessException);
        String str = paramString;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        Log.w("ICUCompatIcs", localInvocationTargetException);
    }
  }

  public static String getScript(String paramString)
  {
    try
    {
      if (sGetScriptMethod != null)
      {
        Object[] arrayOfObject = { paramString };
        str = (String)sGetScriptMethod.invoke(null, arrayOfObject);
        return str;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
      {
        Log.w("ICUCompatIcs", localIllegalAccessException);
        String str = null;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        Log.w("ICUCompatIcs", localInvocationTargetException);
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.text.ICUCompatIcs
 * JD-Core Version:    0.6.2
 */