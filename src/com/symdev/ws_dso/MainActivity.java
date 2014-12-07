package com.symdev.ws_dso;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity
{
  private MyPagerAdapter adapter;
  private int currentColor = -10066330;
  private Drawable.Callback drawableCallback = new Drawable.Callback()
  {
    public void invalidateDrawable(Drawable paramAnonymousDrawable)
    {
      MainActivity.this.getActionBar().setBackgroundDrawable(paramAnonymousDrawable);
    }

    public void scheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable, long paramAnonymousLong)
    {
      MainActivity.this.handler.postAtTime(paramAnonymousRunnable, paramAnonymousLong);
    }

    public void unscheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable)
    {
      MainActivity.this.handler.removeCallbacks(paramAnonymousRunnable);
    }
  };
  private final Handler handler = new Handler();
  private Drawable oldBackground = null;
  private ViewPager pager;
  private PagerSlidingTabStrip tabs;

  private void changeColor(int paramInt)
  {
    this.tabs.setIndicatorColor(paramInt);
    LayerDrawable localLayerDrawable;
    if (Build.VERSION.SDK_INT >= 11)
    {
      localLayerDrawable = new LayerDrawable(new Drawable[] { new ColorDrawable(paramInt), getResources().getDrawable(2130837591) });
      if (this.oldBackground != null)
        break label112;
      if (Build.VERSION.SDK_INT >= 17)
        break label101;
      localLayerDrawable.setCallback(this.drawableCallback);
    }
    while (true)
    {
      this.oldBackground = localLayerDrawable;
      getActionBar().setDisplayShowTitleEnabled(false);
      getActionBar().setDisplayShowTitleEnabled(true);
      this.currentColor = paramInt;
      return;
      label101: getActionBar().setBackgroundDrawable(localLayerDrawable);
    }
    label112: Drawable[] arrayOfDrawable = new Drawable[2];
    arrayOfDrawable[0] = this.oldBackground;
    arrayOfDrawable[1] = localLayerDrawable;
    TransitionDrawable localTransitionDrawable = new TransitionDrawable(arrayOfDrawable);
    if (Build.VERSION.SDK_INT < 17)
      localTransitionDrawable.setCallback(this.drawableCallback);
    while (true)
    {
      localTransitionDrawable.startTransition(200);
      break;
      getActionBar().setBackgroundDrawable(localTransitionDrawable);
    }
  }

  public void onColorClicked(View paramView)
  {
    changeColor(Color.parseColor(paramView.getTag().toString()));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903064);
    this.tabs = ((PagerSlidingTabStrip)findViewById(2131099708));
    this.pager = ((ViewPager)findViewById(2131099709));
    this.adapter = new MyPagerAdapter(getSupportFragmentManager());
    this.pager.setAdapter(this.adapter);
    int i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    this.pager.setPageMargin(i);
    this.tabs.setViewPager(this.pager);
    changeColor(this.currentColor);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492864, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131099710)
      new QuickContactFragment().show(getSupportFragmentManager(), "QuickContactFragment");
    for (boolean bool = true; ; bool = super.onOptionsItemSelected(paramMenuItem))
      return bool;
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.currentColor = paramBundle.getInt("currentColor");
    changeColor(this.currentColor);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("currentColor", this.currentColor);
  }

  public class MyPagerAdapter extends FragmentPagerAdapter
  {
    private final String[] CONTENTS = { "Lorem ipsum dolor sit amet consectetur adipisicing elit", "Programmes", "Intervenant", "PartenaireS" };
    private final String[] TITLES = { "Présentation", "Programmes", "Intervenant", "PartenaireS" };

    public MyPagerAdapter(FragmentManager arg2)
    {
      super();
    }

    public int getCount()
    {
      return this.TITLES.length;
    }

    public Fragment getItem(int paramInt)
    {
      return SuperAwesomeCardFragment.newInstance(paramInt);
    }

    public CharSequence getPageTitle(int paramInt)
    {
      return this.TITLES[paramInt];
    }
  }
}