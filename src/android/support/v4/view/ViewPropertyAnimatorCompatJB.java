package android.support.v4.view;

import android.view.View;
import android.view.ViewPropertyAnimator;

class ViewPropertyAnimatorCompatJB
{
  public static void withEndAction(View paramView, Runnable paramRunnable)
  {
    paramView.animate().withEndAction(paramRunnable);
  }

  public static void withLayer(View paramView)
  {
    paramView.animate().withLayer();
  }

  public static void withStartAction(View paramView, Runnable paramRunnable)
  {
    paramView.animate().withStartAction(paramRunnable);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.view.ViewPropertyAnimatorCompatJB
 * JD-Core Version:    0.6.2
 */