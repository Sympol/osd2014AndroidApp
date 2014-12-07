package com.symdev.ws_dso;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class SuperAwesomeCardFragment extends Fragment
{
  private static final String ARG_POSITION = "position";
  private int position;

  public static SuperAwesomeCardFragment newInstance(int paramInt)
  {
    SuperAwesomeCardFragment localSuperAwesomeCardFragment = new SuperAwesomeCardFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("position", paramInt);
    localSuperAwesomeCardFragment.setArguments(localBundle);
    return localSuperAwesomeCardFragment;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.position = getArguments().getInt("position");
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    FrameLayout localFrameLayout = new FrameLayout(getActivity());
    localFrameLayout.setLayoutParams(localLayoutParams);
    int i = (int)TypedValue.applyDimension(1, 8.0F, getResources().getDisplayMetrics());
    TextView localTextView = new TextView(getActivity());
    localLayoutParams.setMargins(i, i, i, i);
    localTextView.setLayoutParams(localLayoutParams);
    localTextView.setLayoutParams(localLayoutParams);
    localTextView.setGravity(17);
    localTextView.setBackgroundResource(2130837592);
    localTextView.setText("Contenu en attente..." + (1 + this.position));
    localFrameLayout.addView(localTextView);
    return localFrameLayout;
  }
}