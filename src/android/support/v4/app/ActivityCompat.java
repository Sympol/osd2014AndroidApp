package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

public class ActivityCompat extends ContextCompat
{
  public static void finishAffinity(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 16)
      ActivityCompatJB.finishAffinity(paramActivity);
    while (true)
    {
      return;
      paramActivity.finish();
    }
  }

  public static boolean invalidateOptionsMenu(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 11)
      ActivityCompatHoneycomb.invalidateOptionsMenu(paramActivity);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static void startActivity(Activity paramActivity, Intent paramIntent, @Nullable Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16)
      ActivityCompatJB.startActivity(paramActivity, paramIntent, paramBundle);
    while (true)
    {
      return;
      paramActivity.startActivity(paramIntent);
    }
  }

  public static void startActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt, @Nullable Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16)
      ActivityCompatJB.startActivityForResult(paramActivity, paramIntent, paramInt, paramBundle);
    while (true)
    {
      return;
      paramActivity.startActivityForResult(paramIntent, paramInt);
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActivityCompat
 * JD-Core Version:    0.6.2
 */