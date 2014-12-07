package android.support.v7.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle.Delegate;
import android.support.v4.app.ActionBarDrawerToggle.DelegateProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.TaskStackBuilder.SupportParentable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ActionBarActivity extends FragmentActivity
  implements ActionBar.Callback, TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider
{
  ActionBarActivityDelegate mImpl;

  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.mImpl.addContentView(paramView, paramLayoutParams);
  }

  public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
  {
    return this.mImpl.getDrawerToggleDelegate();
  }

  public MenuInflater getMenuInflater()
  {
    return this.mImpl.getMenuInflater();
  }

  public ActionBar getSupportActionBar()
  {
    return this.mImpl.getSupportActionBar();
  }

  public Intent getSupportParentActivityIntent()
  {
    return NavUtils.getParentActivityIntent(this);
  }

  public void onBackPressed()
  {
    if (!this.mImpl.onBackPressed())
      super.onBackPressed();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.mImpl.onConfigurationChanged(paramConfiguration);
  }

  public final void onContentChanged()
  {
    this.mImpl.onContentChanged();
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.mImpl = ActionBarActivityDelegate.createDelegate(this);
    super.onCreate(paramBundle);
    this.mImpl.onCreate(paramBundle);
  }

  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    return this.mImpl.onCreatePanelMenu(paramInt, paramMenu);
  }

  public View onCreatePanelView(int paramInt)
  {
    if (paramInt == 0);
    for (View localView = this.mImpl.onCreatePanelView(paramInt); ; localView = super.onCreatePanelView(paramInt))
      return localView;
  }

  public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder paramTaskStackBuilder)
  {
    paramTaskStackBuilder.addParentStack(this);
  }

  public final boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    boolean bool;
    if (this.mImpl.onMenuItemSelected(paramInt, paramMenuItem))
      bool = true;
    while (true)
    {
      return bool;
      ActionBar localActionBar = getSupportActionBar();
      if ((paramMenuItem.getItemId() == 16908332) && (localActionBar != null) && ((0x4 & localActionBar.getDisplayOptions()) != 0))
        bool = onSupportNavigateUp();
      else
        bool = false;
    }
  }

  protected void onPostResume()
  {
    super.onPostResume();
    this.mImpl.onPostResume();
  }

  protected boolean onPrepareOptionsPanel(View paramView, Menu paramMenu)
  {
    return this.mImpl.onPrepareOptionsPanel(paramView, paramMenu);
  }

  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    return this.mImpl.onPreparePanel(paramInt, paramView, paramMenu);
  }

  public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder paramTaskStackBuilder)
  {
  }

  protected void onStop()
  {
    super.onStop();
    this.mImpl.onStop();
  }

  public void onSupportActionModeFinished(ActionMode paramActionMode)
  {
  }

  public void onSupportActionModeStarted(ActionMode paramActionMode)
  {
  }

  public void onSupportContentChanged()
  {
  }

  public boolean onSupportNavigateUp()
  {
    Intent localIntent = getSupportParentActivityIntent();
    if (localIntent != null)
      if (supportShouldUpRecreateTask(localIntent))
      {
        TaskStackBuilder localTaskStackBuilder = TaskStackBuilder.create(this);
        onCreateSupportNavigateUpTaskStack(localTaskStackBuilder);
        onPrepareSupportNavigateUpTaskStack(localTaskStackBuilder);
        localTaskStackBuilder.startActivities();
      }
    while (true)
    {
      try
      {
        ActivityCompat.finishAffinity(this);
        bool = true;
        return bool;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        finish();
        continue;
      }
      supportNavigateUpTo(localIntent);
      continue;
      boolean bool = false;
    }
  }

  protected void onTitleChanged(CharSequence paramCharSequence, int paramInt)
  {
    super.onTitleChanged(paramCharSequence, paramInt);
    this.mImpl.onTitleChanged(paramCharSequence);
  }

  public void setContentView(int paramInt)
  {
    this.mImpl.setContentView(paramInt);
  }

  public void setContentView(View paramView)
  {
    this.mImpl.setContentView(paramView);
  }

  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.mImpl.setContentView(paramView, paramLayoutParams);
  }

  public void setSupportProgress(int paramInt)
  {
    this.mImpl.setSupportProgress(paramInt);
  }

  public void setSupportProgressBarIndeterminate(boolean paramBoolean)
  {
    this.mImpl.setSupportProgressBarIndeterminate(paramBoolean);
  }

  public void setSupportProgressBarIndeterminateVisibility(boolean paramBoolean)
  {
    this.mImpl.setSupportProgressBarIndeterminateVisibility(paramBoolean);
  }

  public void setSupportProgressBarVisibility(boolean paramBoolean)
  {
    this.mImpl.setSupportProgressBarVisibility(paramBoolean);
  }

  public ActionMode startSupportActionMode(ActionMode.Callback paramCallback)
  {
    return this.mImpl.startSupportActionMode(paramCallback);
  }

  void superAddContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addContentView(paramView, paramLayoutParams);
  }

  boolean superOnCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    return super.onCreatePanelMenu(paramInt, paramMenu);
  }

  boolean superOnMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    return super.onMenuItemSelected(paramInt, paramMenuItem);
  }

  boolean superOnPrepareOptionsPanel(View paramView, Menu paramMenu)
  {
    return super.onPrepareOptionsPanel(paramView, paramMenu);
  }

  boolean superOnPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    return super.onPreparePanel(paramInt, paramView, paramMenu);
  }

  void superSetContentView(int paramInt)
  {
    super.setContentView(paramInt);
  }

  void superSetContentView(View paramView)
  {
    super.setContentView(paramView);
  }

  void superSetContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
  }

  public void supportInvalidateOptionsMenu()
  {
    if (Build.VERSION.SDK_INT >= 14)
      super.supportInvalidateOptionsMenu();
    this.mImpl.supportInvalidateOptionsMenu();
  }

  public void supportNavigateUpTo(Intent paramIntent)
  {
    NavUtils.navigateUpTo(this, paramIntent);
  }

  public boolean supportRequestWindowFeature(int paramInt)
  {
    return this.mImpl.supportRequestWindowFeature(paramInt);
  }

  public boolean supportShouldUpRecreateTask(Intent paramIntent)
  {
    return NavUtils.shouldUpRecreateTask(this, paramIntent);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.app.ActionBarActivity
 * JD-Core Version:    0.6.2
 */