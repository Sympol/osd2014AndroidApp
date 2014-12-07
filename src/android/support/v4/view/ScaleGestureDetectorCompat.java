package android.support.v4.view;

import android.os.Build.VERSION;

public class ScaleGestureDetectorCompat
{
  static final ScaleGestureDetectorImpl IMPL;

  static
  {
    if (Build.VERSION.SDK_INT >= 19);
    for (IMPL = new ScaleGestureDetectorCompatKitKatImpl(null); ; IMPL = new BaseScaleGestureDetectorImpl(null))
      return;
  }

  public static boolean isQuickScaleEnabled(Object paramObject)
  {
    return IMPL.isQuickScaleEnabled(paramObject);
  }

  public static void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
  {
    IMPL.setQuickScaleEnabled(paramObject, paramBoolean);
  }

  private static class BaseScaleGestureDetectorImpl
    implements ScaleGestureDetectorCompat.ScaleGestureDetectorImpl
  {
    public boolean isQuickScaleEnabled(Object paramObject)
    {
      return false;
    }

    public void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
    {
    }
  }

  private static class ScaleGestureDetectorCompatKitKatImpl
    implements ScaleGestureDetectorCompat.ScaleGestureDetectorImpl
  {
    public boolean isQuickScaleEnabled(Object paramObject)
    {
      return ScaleGestureDetectorCompatKitKat.isQuickScaleEnabled(paramObject);
    }

    public void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
    {
      ScaleGestureDetectorCompatKitKat.setQuickScaleEnabled(paramObject, paramBoolean);
    }
  }

  static abstract interface ScaleGestureDetectorImpl
  {
    public abstract boolean isQuickScaleEnabled(Object paramObject);

    public abstract void setQuickScaleEnabled(Object paramObject, boolean paramBoolean);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.view.ScaleGestureDetectorCompat
 * JD-Core Version:    0.6.2
 */