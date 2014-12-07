package android.support.v4.internal.view;

import android.view.Menu;

public abstract interface SupportMenu extends Menu
{
  public static final int CATEGORY_MASK = -65536;
  public static final int CATEGORY_SHIFT = 16;
  public static final int USER_MASK = 65535;
  public static final int USER_SHIFT;
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.internal.view.SupportMenu
 * JD-Core Version:    0.6.2
 */