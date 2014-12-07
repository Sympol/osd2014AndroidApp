package com.symdev.ws_dso;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStrip.IconTabProvider;

public class QuickContactFragment extends DialogFragment
{
  private ContactPagerAdapter adapter;
  private ViewPager pager;
  private PagerSlidingTabStrip tabs;

  public static QuickContactFragment newInstance()
  {
    return new QuickContactFragment();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (getDialog() != null)
    {
      getDialog().getWindow().requestFeature(1);
      getDialog().getWindow().setBackgroundDrawableResource(17170445);
    }
    View localView = paramLayoutInflater.inflate(2130903065, paramViewGroup, false);
    this.tabs = ((PagerSlidingTabStrip)localView.findViewById(2131099708));
    this.pager = ((ViewPager)localView.findViewById(2131099709));
    this.adapter = new ContactPagerAdapter();
    this.pager.setAdapter(this.adapter);
    this.tabs.setViewPager(this.pager);
    return localView;
  }

  public void onStart()
  {
    super.onStart();
    Point localPoint;
    if (getDialog() != null)
    {
      if (Build.VERSION.SDK_INT < 13)
        break label115;
      Display localDisplay = getActivity().getWindowManager().getDefaultDisplay();
      localPoint = new Point();
      localDisplay.getSize(localPoint);
    }
    label115: for (int i = localPoint.x; ; i = getActivity().getWindowManager().getDefaultDisplay().getWidth())
    {
      int j = i - (int)TypedValue.applyDimension(1, 24.0F, getResources().getDisplayMetrics());
      int k = getDialog().getWindow().getAttributes().height;
      getDialog().getWindow().setLayout(j, k);
      return;
    }
  }

  public class ContactPagerAdapter extends PagerAdapter
    implements PagerSlidingTabStrip.IconTabProvider
  {
    private final String[] CONTENTS = { "(+225) 22.007.354 / 09.474.800 / 05.321.250", "secretariat@osdays.ci", "Abidjan / Côte divoire - Institut des Sciences des Technologies et de la Communication (ISTC) ", "http://www.osdays.ci" };
    private final int[] ICONS = { 2130837606, 2130837600, 2130837601, 2130837599 };

    public ContactPagerAdapter()
    {
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((View)paramObject);
    }

    public int getCount()
    {
      return this.ICONS.length;
    }

    public int getPageIconResId(int paramInt)
    {
      return this.ICONS[paramInt];
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      TextView localTextView = new TextView(QuickContactFragment.this.getActivity());
      localTextView.setBackgroundResource(2131034116);
      localTextView.setText(this.CONTENTS[paramInt]);
      int i = (int)TypedValue.applyDimension(1, 16.0F, QuickContactFragment.this.getResources().getDisplayMetrics());
      localTextView.setPadding(i, i, i, i);
      localTextView.setGravity(17);
      paramViewGroup.addView(localTextView, 0);
      return localTextView;
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      if (paramView == (View)paramObject);
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }
}