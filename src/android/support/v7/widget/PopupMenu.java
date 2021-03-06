package android.support.v7.widget;

import android.content.Context;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class PopupMenu
  implements MenuBuilder.Callback, MenuPresenter.Callback
{
  private View mAnchor;
  private Context mContext;
  private OnDismissListener mDismissListener;
  private MenuBuilder mMenu;
  private OnMenuItemClickListener mMenuItemClickListener;
  private MenuPopupHelper mPopup;

  public PopupMenu(Context paramContext, View paramView)
  {
    this.mContext = paramContext;
    this.mMenu = new MenuBuilder(paramContext);
    this.mMenu.setCallback(this);
    this.mAnchor = paramView;
    this.mPopup = new MenuPopupHelper(paramContext, this.mMenu, paramView);
    this.mPopup.setCallback(this);
  }

  public void dismiss()
  {
    this.mPopup.dismiss();
  }

  public Menu getMenu()
  {
    return this.mMenu;
  }

  public MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(this.mContext);
  }

  public void inflate(int paramInt)
  {
    getMenuInflater().inflate(paramInt, this.mMenu);
  }

  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (this.mDismissListener != null)
      this.mDismissListener.onDismiss(this);
  }

  public void onCloseSubMenu(SubMenuBuilder paramSubMenuBuilder)
  {
  }

  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    if (this.mMenuItemClickListener != null);
    for (boolean bool = this.mMenuItemClickListener.onMenuItemClick(paramMenuItem); ; bool = false)
      return bool;
  }

  public void onMenuModeChange(MenuBuilder paramMenuBuilder)
  {
  }

  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    boolean bool = true;
    if (paramMenuBuilder == null)
      bool = false;
    while (true)
    {
      return bool;
      if (paramMenuBuilder.hasVisibleItems())
        new MenuPopupHelper(this.mContext, paramMenuBuilder, this.mAnchor).show();
    }
  }

  public void setOnDismissListener(OnDismissListener paramOnDismissListener)
  {
    this.mDismissListener = paramOnDismissListener;
  }

  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    this.mMenuItemClickListener = paramOnMenuItemClickListener;
  }

  public void show()
  {
    this.mPopup.show();
  }

  public static abstract interface OnDismissListener
  {
    public abstract void onDismiss(PopupMenu paramPopupMenu);
  }

  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.widget.PopupMenu
 * JD-Core Version:    0.6.2
 */