package android.support.v7.app;

class ActionBarActivityDelegateJBMR2 extends ActionBarActivityDelegateJB
{
  ActionBarActivityDelegateJBMR2(ActionBarActivity paramActionBarActivity)
  {
    super(paramActionBarActivity);
  }

  public ActionBar createSupportActionBar()
  {
    return new ActionBarImplJBMR2(this.mActivity, this.mActivity);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.app.ActionBarActivityDelegateJBMR2
 * JD-Core Version:    0.6.2
 */