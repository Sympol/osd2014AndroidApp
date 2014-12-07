package android.support.v4.view;

import android.view.ScaleGestureDetector;

class ScaleGestureDetectorCompatKitKat
{
  public static boolean isQuickScaleEnabled(Object paramObject)
  {
    return ((ScaleGestureDetector)paramObject).isQuickScaleEnabled();
  }

  public static void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
  {
    ((ScaleGestureDetector)paramObject).setQuickScaleEnabled(paramBoolean);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.view.ScaleGestureDetectorCompatKitKat
 * JD-Core Version:    0.6.2
 */