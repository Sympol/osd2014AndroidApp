package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

abstract class BaseMenuWrapper<T> extends BaseWrapper<T>
{
  private HashMap<MenuItem, SupportMenuItem> mMenuItems;
  private HashMap<SubMenu, SubMenu> mSubMenus;

  BaseMenuWrapper(T paramT)
  {
    super(paramT);
  }

  final SupportMenuItem getMenuItemWrapper(MenuItem paramMenuItem)
  {
    SupportMenuItem localSupportMenuItem;
    if (paramMenuItem != null)
    {
      if (this.mMenuItems == null)
        this.mMenuItems = new HashMap();
      localSupportMenuItem = (SupportMenuItem)this.mMenuItems.get(paramMenuItem);
      if (localSupportMenuItem == null)
      {
        localSupportMenuItem = MenuWrapperFactory.createSupportMenuItemWrapper(paramMenuItem);
        this.mMenuItems.put(paramMenuItem, localSupportMenuItem);
      }
    }
    while (true)
    {
      return localSupportMenuItem;
      localSupportMenuItem = null;
    }
  }

  final SubMenu getSubMenuWrapper(SubMenu paramSubMenu)
  {
    Object localObject;
    if (paramSubMenu != null)
    {
      if (this.mSubMenus == null)
        this.mSubMenus = new HashMap();
      localObject = (SubMenu)this.mSubMenus.get(paramSubMenu);
      if (localObject == null)
      {
        localObject = MenuWrapperFactory.createSupportSubMenuWrapper(paramSubMenu);
        this.mSubMenus.put(paramSubMenu, localObject);
      }
    }
    while (true)
    {
      return localObject;
      localObject = null;
    }
  }

  final void internalClear()
  {
    if (this.mMenuItems != null)
      this.mMenuItems.clear();
    if (this.mSubMenus != null)
      this.mSubMenus.clear();
  }

  final void internalRemoveGroup(int paramInt)
  {
    if (this.mMenuItems == null);
    while (true)
    {
      return;
      Iterator localIterator = this.mMenuItems.keySet().iterator();
      while (localIterator.hasNext())
        if (paramInt == ((MenuItem)localIterator.next()).getGroupId())
          localIterator.remove();
    }
  }

  final void internalRemoveItem(int paramInt)
  {
    if (this.mMenuItems == null)
      return;
    while (true)
    {
      Iterator localIterator = this.mMenuItems.keySet().iterator();
      if (localIterator.hasNext())
      {
        if (paramInt != ((MenuItem)localIterator.next()).getItemId())
          break;
        localIterator.remove();
      }
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.internal.view.menu.BaseMenuWrapper
 * JD-Core Version:    0.6.2
 */