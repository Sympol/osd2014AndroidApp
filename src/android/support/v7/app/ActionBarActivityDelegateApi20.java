package android.support.v7.app;

import android.view.Window.Callback;

class ActionBarActivityDelegateApi20 extends ActionBarActivityDelegateJBMR2
{
  ActionBarActivityDelegateApi20(ActionBarActivity paramActionBarActivity)
  {
    super(paramActionBarActivity);
  }

  Window.Callback createWindowCallbackWrapper(Window.Callback paramCallback)
  {
    return new WindowCallbackWrapperApi20(paramCallback);
  }

  class WindowCallbackWrapperApi20 extends ActionBarActivityDelegateICS.WindowCallbackWrapper
  {
    WindowCallbackWrapperApi20(Window.Callback arg2)
    {
      super(localCallback);
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.app.ActionBarActivityDelegateApi20
 * JD-Core Version:    0.6.2
 */