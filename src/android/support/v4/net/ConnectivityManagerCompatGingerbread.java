package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class ConnectivityManagerCompatGingerbread
{
  public static boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
  {
    boolean bool = true;
    NetworkInfo localNetworkInfo = paramConnectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null);
    while (true)
    {
      return bool;
      switch (localNetworkInfo.getType())
      {
      case 0:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      default:
        break;
      case 1:
        bool = false;
      }
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.net.ConnectivityManagerCompatGingerbread
 * JD-Core Version:    0.6.2
 */