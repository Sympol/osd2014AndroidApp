package android.support.v7.app;

class ActionBarActivityDelegateHC extends ActionBarActivityDelegateBase
{
  ActionBarActivityDelegateHC(ActionBarActivity paramActionBarActivity)
  {
    super(paramActionBarActivity);
  }

  public ActionBar createSupportActionBar()
  {
    ensureSubDecor();
    return new ActionBarImplHC(this.mActivity, this.mActivity);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.app.ActionBarActivityDelegateHC
 * JD-Core Version:    0.6.2
 */