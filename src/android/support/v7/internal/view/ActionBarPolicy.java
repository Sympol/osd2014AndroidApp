package android.support.v7.internal.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.bool;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.integer;
import android.support.v7.appcompat.R.styleable;
import android.util.DisplayMetrics;

public class ActionBarPolicy
{
  private Context mContext;

  private ActionBarPolicy(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static ActionBarPolicy get(Context paramContext)
  {
    return new ActionBarPolicy(paramContext);
  }

  public boolean enableHomeButtonByDefault()
  {
    if (this.mContext.getApplicationInfo().targetSdkVersion < 14);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public int getEmbeddedMenuWidthLimit()
  {
    return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
  }

  public int getMaxActionButtons()
  {
    return this.mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
  }

  public int getStackedTabMaxWidth()
  {
    return this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
  }

  public int getTabContainerHeight()
  {
    TypedArray localTypedArray = this.mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    int i = localTypedArray.getLayoutDimension(1, 0);
    Resources localResources = this.mContext.getResources();
    if (!hasEmbeddedTabs())
      i = Math.min(i, localResources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
    localTypedArray.recycle();
    return i;
  }

  public boolean hasEmbeddedTabs()
  {
    return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs_pre_jb);
  }

  public boolean showsOverflowMenuButton()
  {
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.internal.view.ActionBarPolicy
 * JD-Core Version:    0.6.2
 */