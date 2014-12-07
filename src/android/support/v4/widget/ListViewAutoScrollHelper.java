package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper
{
  private final ListView mTarget;

  public ListViewAutoScrollHelper(ListView paramListView)
  {
    super(paramListView);
    this.mTarget = paramListView;
  }

  public boolean canTargetScrollHorizontally(int paramInt)
  {
    return false;
  }

  public boolean canTargetScrollVertically(int paramInt)
  {
    ListView localListView = this.mTarget;
    int i = localListView.getCount();
    boolean bool = false;
    if (i == 0);
    while (true)
    {
      return bool;
      int j = localListView.getChildCount();
      int k = localListView.getFirstVisiblePosition();
      int m = k + j;
      if (paramInt > 0)
      {
        if (m >= i)
        {
          int n = localListView.getChildAt(j - 1).getBottom();
          int i1 = localListView.getHeight();
          bool = false;
          if (n <= i1)
            continue;
        }
      }
      else
      {
        do
        {
          bool = true;
          break;
          bool = false;
          if (paramInt >= 0)
            break;
        }
        while ((k > 0) || (localListView.getChildAt(0).getTop() < 0));
        bool = false;
      }
    }
  }

  public void scrollTargetBy(int paramInt1, int paramInt2)
  {
    ListView localListView = this.mTarget;
    int i = localListView.getFirstVisiblePosition();
    if (i == -1);
    while (true)
    {
      return;
      View localView = localListView.getChildAt(0);
      if (localView != null)
        localListView.setSelectionFromTop(i, localView.getTop() - paramInt2);
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ListViewAutoScrollHelper
 * JD-Core Version:    0.6.2
 */