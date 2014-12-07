package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;

public class ActionBarImplJBMR2 extends ActionBarImplJB
{
  public ActionBarImplJBMR2(Activity paramActivity, ActionBar.Callback paramCallback)
  {
    super(paramActivity, paramCallback);
  }

  public void setHomeActionContentDescription(int paramInt)
  {
    this.mActionBar.setHomeActionContentDescription(paramInt);
  }

  public void setHomeActionContentDescription(CharSequence paramCharSequence)
  {
    this.mActionBar.setHomeActionContentDescription(paramCharSequence);
  }

  public void setHomeAsUpIndicator(int paramInt)
  {
    this.mActionBar.setHomeAsUpIndicator(paramInt);
  }

  public void setHomeAsUpIndicator(Drawable paramDrawable)
  {
    this.mActionBar.setHomeAsUpIndicator(paramDrawable);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.app.ActionBarImplJBMR2
 * JD-Core Version:    0.6.2
 */