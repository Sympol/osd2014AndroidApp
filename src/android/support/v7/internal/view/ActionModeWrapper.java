package android.support.v7.internal.view;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ActionModeWrapper extends android.support.v7.view.ActionMode
{
  final MenuInflater mInflater;
  final android.view.ActionMode mWrappedObject;

  public ActionModeWrapper(Context paramContext, android.view.ActionMode paramActionMode)
  {
    this.mWrappedObject = paramActionMode;
    this.mInflater = new SupportMenuInflater(paramContext);
  }

  public void finish()
  {
    this.mWrappedObject.finish();
  }

  public View getCustomView()
  {
    return this.mWrappedObject.getCustomView();
  }

  public Menu getMenu()
  {
    return MenuWrapperFactory.createMenuWrapper(this.mWrappedObject.getMenu());
  }

  public MenuInflater getMenuInflater()
  {
    return this.mInflater;
  }

  public CharSequence getSubtitle()
  {
    return this.mWrappedObject.getSubtitle();
  }

  public Object getTag()
  {
    return this.mWrappedObject.getTag();
  }

  public CharSequence getTitle()
  {
    return this.mWrappedObject.getTitle();
  }

  public void invalidate()
  {
    this.mWrappedObject.invalidate();
  }

  public void setCustomView(View paramView)
  {
    this.mWrappedObject.setCustomView(paramView);
  }

  public void setSubtitle(int paramInt)
  {
    this.mWrappedObject.setSubtitle(paramInt);
  }

  public void setSubtitle(CharSequence paramCharSequence)
  {
    this.mWrappedObject.setSubtitle(paramCharSequence);
  }

  public void setTag(Object paramObject)
  {
    this.mWrappedObject.setTag(paramObject);
  }

  public void setTitle(int paramInt)
  {
    this.mWrappedObject.setTitle(paramInt);
  }

  public void setTitle(CharSequence paramCharSequence)
  {
    this.mWrappedObject.setTitle(paramCharSequence);
  }

  public static class CallbackWrapper
    implements android.view.ActionMode.Callback
  {
    final Context mContext;
    private ActionModeWrapper mLastStartedActionMode;
    final android.support.v7.view.ActionMode.Callback mWrappedCallback;

    public CallbackWrapper(Context paramContext, android.support.v7.view.ActionMode.Callback paramCallback)
    {
      this.mContext = paramContext;
      this.mWrappedCallback = paramCallback;
    }

    private android.support.v7.view.ActionMode getActionModeWrapper(android.view.ActionMode paramActionMode)
    {
      if ((this.mLastStartedActionMode != null) && (this.mLastStartedActionMode.mWrappedObject == paramActionMode));
      for (ActionModeWrapper localActionModeWrapper = this.mLastStartedActionMode; ; localActionModeWrapper = createActionModeWrapper(this.mContext, paramActionMode))
        return localActionModeWrapper;
    }

    protected ActionModeWrapper createActionModeWrapper(Context paramContext, android.view.ActionMode paramActionMode)
    {
      return new ActionModeWrapper(paramContext, paramActionMode);
    }

    public boolean onActionItemClicked(android.view.ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return this.mWrappedCallback.onActionItemClicked(getActionModeWrapper(paramActionMode), MenuWrapperFactory.createMenuItemWrapper(paramMenuItem));
    }

    public boolean onCreateActionMode(android.view.ActionMode paramActionMode, Menu paramMenu)
    {
      return this.mWrappedCallback.onCreateActionMode(getActionModeWrapper(paramActionMode), MenuWrapperFactory.createMenuWrapper(paramMenu));
    }

    public void onDestroyActionMode(android.view.ActionMode paramActionMode)
    {
      this.mWrappedCallback.onDestroyActionMode(getActionModeWrapper(paramActionMode));
    }

    public boolean onPrepareActionMode(android.view.ActionMode paramActionMode, Menu paramMenu)
    {
      return this.mWrappedCallback.onPrepareActionMode(getActionModeWrapper(paramActionMode), MenuWrapperFactory.createMenuWrapper(paramMenu));
    }

    public void setLastStartedActionMode(ActionModeWrapper paramActionModeWrapper)
    {
      this.mLastStartedActionMode = paramActionModeWrapper;
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.internal.view.ActionModeWrapper
 * JD-Core Version:    0.6.2
 */