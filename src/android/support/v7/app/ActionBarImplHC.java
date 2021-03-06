package android.support.v7.app;

import android.support.v7.appcompat.R.id;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.support.v7.internal.widget.NativeActionModeAwareLayout.OnActionModeForChildListener;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;

class ActionBarImplHC extends ActionBarImplBase
  implements NativeActionModeAwareLayout.OnActionModeForChildListener
{
  private ActionMode mCurActionMode;
  final NativeActionModeAwareLayout mNativeActionModeAwareLayout;

  public ActionBarImplHC(ActionBarActivity paramActionBarActivity, ActionBar.Callback paramCallback)
  {
    super(paramActionBarActivity, paramCallback);
    this.mNativeActionModeAwareLayout = ((NativeActionModeAwareLayout)paramActionBarActivity.findViewById(R.id.action_bar_root));
    if (this.mNativeActionModeAwareLayout != null)
      this.mNativeActionModeAwareLayout.setActionModeForChildListener(this);
  }

  public void hide()
  {
    super.hide();
    if (this.mCurActionMode != null)
      this.mCurActionMode.finish();
  }

  boolean isShowHideAnimationEnabled()
  {
    if ((this.mCurActionMode == null) && (super.isShowHideAnimationEnabled()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public ActionMode.Callback onActionModeForChild(ActionMode.Callback paramCallback)
  {
    return new CallbackWrapper(paramCallback);
  }

  public void show()
  {
    super.show();
    if (this.mCurActionMode != null)
      this.mCurActionMode.finish();
  }

  private class CallbackWrapper
    implements ActionMode.Callback
  {
    private final ActionMode.Callback mWrappedCallback;

    CallbackWrapper(ActionMode.Callback arg2)
    {
      Object localObject;
      this.mWrappedCallback = localObject;
    }

    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return this.mWrappedCallback.onActionItemClicked(paramActionMode, paramMenuItem);
    }

    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      boolean bool = this.mWrappedCallback.onCreateActionMode(paramActionMode, paramMenu);
      if (bool)
      {
        ActionBarImplHC.access$002(ActionBarImplHC.this, paramActionMode);
        ActionBarImplHC.this.showForActionMode();
      }
      return bool;
    }

    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      this.mWrappedCallback.onDestroyActionMode(paramActionMode);
      ActionBarImplHC.this.hideForActionMode();
      ActionBarImplHC.access$002(ActionBarImplHC.this, null);
    }

    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return this.mWrappedCallback.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.app.ActionBarImplHC
 * JD-Core Version:    0.6.2
 */