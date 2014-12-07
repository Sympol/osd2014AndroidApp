package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

abstract class AdapterViewICS<T extends Adapter> extends ViewGroup
{
  public static final int INVALID_POSITION = -1;
  public static final long INVALID_ROW_ID = -9223372036854775808L;
  static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
  static final int ITEM_VIEW_TYPE_IGNORE = -1;
  static final int SYNC_FIRST_POSITION = 1;
  static final int SYNC_MAX_DURATION_MILLIS = 100;
  static final int SYNC_SELECTED_POSITION;
  boolean mBlockLayoutRequests = false;
  boolean mDataChanged;
  private boolean mDesiredFocusableInTouchModeState;
  private boolean mDesiredFocusableState;
  private View mEmptyView;

  @ViewDebug.ExportedProperty(category="scrolling")
  int mFirstPosition = 0;
  boolean mInLayout = false;

  @ViewDebug.ExportedProperty(category="list")
  int mItemCount;
  private int mLayoutHeight;
  boolean mNeedSync = false;

  @ViewDebug.ExportedProperty(category="list")
  int mNextSelectedPosition = -1;
  long mNextSelectedRowId = -9223372036854775808L;
  int mOldItemCount;
  int mOldSelectedPosition = -1;
  long mOldSelectedRowId = -9223372036854775808L;
  OnItemClickListener mOnItemClickListener;
  OnItemLongClickListener mOnItemLongClickListener;
  OnItemSelectedListener mOnItemSelectedListener;

  @ViewDebug.ExportedProperty(category="list")
  int mSelectedPosition = -1;
  long mSelectedRowId = -9223372036854775808L;
  private AdapterViewICS<T>.SelectionNotifier mSelectionNotifier;
  int mSpecificTop;
  long mSyncHeight;
  int mSyncMode;
  int mSyncPosition;
  long mSyncRowId = -9223372036854775808L;

  AdapterViewICS(Context paramContext)
  {
    super(paramContext);
  }

  AdapterViewICS(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  AdapterViewICS(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void fireOnSelected()
  {
    if (this.mOnItemSelectedListener == null);
    while (true)
    {
      return;
      int i = getSelectedItemPosition();
      if (i >= 0)
      {
        View localView = getSelectedView();
        this.mOnItemSelectedListener.onItemSelected(this, localView, i, getAdapter().getItemId(i));
      }
      else
      {
        this.mOnItemSelectedListener.onNothingSelected(this);
      }
    }
  }

  private void updateEmptyStatus(boolean paramBoolean)
  {
    if (isInFilterMode())
      paramBoolean = false;
    if (paramBoolean)
      if (this.mEmptyView != null)
      {
        this.mEmptyView.setVisibility(0);
        setVisibility(8);
        if (this.mDataChanged)
          onLayout(false, getLeft(), getTop(), getRight(), getBottom());
      }
    while (true)
    {
      return;
      setVisibility(0);
      break;
      if (this.mEmptyView != null)
        this.mEmptyView.setVisibility(8);
      setVisibility(0);
    }
  }

  public void addView(View paramView)
  {
    throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
  }

  public void addView(View paramView, int paramInt)
  {
    throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
  }

  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
  }

  protected boolean canAnimate()
  {
    if ((super.canAnimate()) && (this.mItemCount > 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  void checkFocus()
  {
    Adapter localAdapter = getAdapter();
    int i;
    int j;
    label33: boolean bool1;
    if ((localAdapter == null) || (localAdapter.getCount() == 0))
    {
      i = 1;
      if ((i != 0) && (!isInFilterMode()))
        break label115;
      j = 1;
      if ((j == 0) || (!this.mDesiredFocusableInTouchModeState))
        break label120;
      bool1 = true;
      label47: super.setFocusableInTouchMode(bool1);
      if ((j == 0) || (!this.mDesiredFocusableState))
        break label126;
    }
    label115: label120: label126: for (boolean bool2 = true; ; bool2 = false)
    {
      super.setFocusable(bool2);
      if (this.mEmptyView != null)
      {
        boolean bool3;
        if (localAdapter != null)
        {
          boolean bool4 = localAdapter.isEmpty();
          bool3 = false;
          if (!bool4);
        }
        else
        {
          bool3 = true;
        }
        updateEmptyStatus(bool3);
      }
      return;
      i = 0;
      break;
      j = 0;
      break label33;
      bool1 = false;
      break label47;
    }
  }

  void checkSelectionChanged()
  {
    if ((this.mSelectedPosition != this.mOldSelectedPosition) || (this.mSelectedRowId != this.mOldSelectedRowId))
    {
      selectionChanged();
      this.mOldSelectedPosition = this.mSelectedPosition;
      this.mOldSelectedRowId = this.mSelectedRowId;
    }
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    View localView = getSelectedView();
    if ((localView != null) && (localView.getVisibility() == 0) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchThawSelfOnly(paramSparseArray);
  }

  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchFreezeSelfOnly(paramSparseArray);
  }

  int findSyncPosition()
  {
    int i = this.mItemCount;
    int m;
    if (i == 0)
      m = -1;
    long l1;
    long l2;
    int n;
    int i1;
    Adapter localAdapter;
    while (true)
    {
      return m;
      l1 = this.mSyncRowId;
      int j = this.mSyncPosition;
      if (l1 == -9223372036854775808L)
      {
        m = -1;
      }
      else
      {
        int k = Math.max(0, j);
        m = Math.min(i - 1, k);
        l2 = 100L + SystemClock.uptimeMillis();
        n = m;
        i1 = m;
        localAdapter = getAdapter();
        i2 = 0;
        if (localAdapter != null)
          break;
        m = -1;
      }
    }
    label95: int i4;
    int i3;
    if ((i4 != 0) || ((i2 != 0) && (i3 == 0)))
    {
      i1++;
      m = i1;
    }
    for (int i2 = 0; ; i2 = 1)
    {
      label154: label178: label184: label188: 
      do
      {
        if (SystemClock.uptimeMillis() <= l2)
        {
          if (localAdapter.getItemId(m) == l1)
            break;
          if (i1 != i - 1)
            break label178;
          i3 = 1;
          if (n != 0)
            break label184;
        }
        for (i4 = 1; ; i4 = 0)
        {
          if ((i3 == 0) || (i4 == 0))
            break label188;
          m = -1;
          break;
          i3 = 0;
          break label154;
        }
        break label95;
      }
      while ((i3 == 0) && ((i2 != 0) || (i4 != 0)));
      n--;
      m = n;
    }
  }

  public abstract T getAdapter();

  @ViewDebug.CapturedViewProperty
  public int getCount()
  {
    return this.mItemCount;
  }

  public View getEmptyView()
  {
    return this.mEmptyView;
  }

  public int getFirstVisiblePosition()
  {
    return this.mFirstPosition;
  }

  public Object getItemAtPosition(int paramInt)
  {
    Adapter localAdapter = getAdapter();
    if ((localAdapter == null) || (paramInt < 0));
    for (Object localObject = null; ; localObject = localAdapter.getItem(paramInt))
      return localObject;
  }

  public long getItemIdAtPosition(int paramInt)
  {
    Adapter localAdapter = getAdapter();
    if ((localAdapter == null) || (paramInt < 0));
    for (long l = -9223372036854775808L; ; l = localAdapter.getItemId(paramInt))
      return l;
  }

  public int getLastVisiblePosition()
  {
    return -1 + (this.mFirstPosition + getChildCount());
  }

  public final OnItemClickListener getOnItemClickListener()
  {
    return this.mOnItemClickListener;
  }

  public final OnItemLongClickListener getOnItemLongClickListener()
  {
    return this.mOnItemLongClickListener;
  }

  public final OnItemSelectedListener getOnItemSelectedListener()
  {
    return this.mOnItemSelectedListener;
  }

  public int getPositionForView(View paramView)
  {
    int i = -1;
    Object localObject = paramView;
    try
    {
      while (true)
      {
        View localView = (View)((View)localObject).getParent();
        boolean bool = localView.equals(this);
        if (bool)
          break;
        localObject = localView;
      }
    }
    catch (ClassCastException localClassCastException)
    {
    }
    label80: 
    while (true)
    {
      return i;
      int j = getChildCount();
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label80;
        if (getChildAt(k).equals(localObject))
        {
          i = k + this.mFirstPosition;
          break;
        }
      }
    }
  }

  public Object getSelectedItem()
  {
    Adapter localAdapter = getAdapter();
    int i = getSelectedItemPosition();
    if ((localAdapter != null) && (localAdapter.getCount() > 0) && (i >= 0));
    for (Object localObject = localAdapter.getItem(i); ; localObject = null)
      return localObject;
  }

  @ViewDebug.CapturedViewProperty
  public long getSelectedItemId()
  {
    return this.mNextSelectedRowId;
  }

  @ViewDebug.CapturedViewProperty
  public int getSelectedItemPosition()
  {
    return this.mNextSelectedPosition;
  }

  public abstract View getSelectedView();

  void handleDataChanged()
  {
    int i = this.mItemCount;
    int j = 0;
    if (i > 0)
    {
      boolean bool = this.mNeedSync;
      j = 0;
      if (bool)
      {
        this.mNeedSync = false;
        int n = findSyncPosition();
        j = 0;
        if (n >= 0)
        {
          int i1 = lookForSelectablePosition(n, true);
          j = 0;
          if (i1 == n)
          {
            setNextSelectedPositionInt(n);
            j = 1;
          }
        }
      }
      if (j == 0)
      {
        int k = getSelectedItemPosition();
        if (k >= i)
          k = i - 1;
        if (k < 0)
          k = 0;
        int m = lookForSelectablePosition(k, true);
        if (m < 0)
          m = lookForSelectablePosition(k, false);
        if (m >= 0)
        {
          setNextSelectedPositionInt(m);
          checkSelectionChanged();
          j = 1;
        }
      }
    }
    if (j == 0)
    {
      this.mSelectedPosition = -1;
      this.mSelectedRowId = -9223372036854775808L;
      this.mNextSelectedPosition = -1;
      this.mNextSelectedRowId = -9223372036854775808L;
      this.mNeedSync = false;
      checkSelectionChanged();
    }
  }

  boolean isInFilterMode()
  {
    return false;
  }

  int lookForSelectablePosition(int paramInt, boolean paramBoolean)
  {
    return paramInt;
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(this.mSelectionNotifier);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLayoutHeight = getHeight();
  }

  public boolean performItemClick(View paramView, int paramInt, long paramLong)
  {
    OnItemClickListener localOnItemClickListener = this.mOnItemClickListener;
    boolean bool = false;
    if (localOnItemClickListener != null)
    {
      playSoundEffect(0);
      if (paramView != null)
        paramView.sendAccessibilityEvent(1);
      this.mOnItemClickListener.onItemClick(this, paramView, paramInt, paramLong);
      bool = true;
    }
    return bool;
  }

  void rememberSyncState()
  {
    if (getChildCount() > 0)
    {
      this.mNeedSync = true;
      this.mSyncHeight = this.mLayoutHeight;
      if (this.mSelectedPosition >= 0)
      {
        View localView2 = getChildAt(this.mSelectedPosition - this.mFirstPosition);
        this.mSyncRowId = this.mNextSelectedRowId;
        this.mSyncPosition = this.mNextSelectedPosition;
        if (localView2 != null)
          this.mSpecificTop = localView2.getTop();
        this.mSyncMode = 0;
      }
    }
    else
    {
      return;
    }
    View localView1 = getChildAt(0);
    Adapter localAdapter = getAdapter();
    if ((this.mFirstPosition >= 0) && (this.mFirstPosition < localAdapter.getCount()));
    for (this.mSyncRowId = localAdapter.getItemId(this.mFirstPosition); ; this.mSyncRowId = -1L)
    {
      this.mSyncPosition = this.mFirstPosition;
      if (localView1 != null)
        this.mSpecificTop = localView1.getTop();
      this.mSyncMode = 1;
      break;
    }
  }

  public void removeAllViews()
  {
    throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
  }

  public void removeView(View paramView)
  {
    throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
  }

  public void removeViewAt(int paramInt)
  {
    throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
  }

  void selectionChanged()
  {
    if (this.mOnItemSelectedListener != null)
    {
      if ((!this.mInLayout) && (!this.mBlockLayoutRequests))
        break label78;
      if (this.mSelectionNotifier == null)
        this.mSelectionNotifier = new SelectionNotifier(null);
      post(this.mSelectionNotifier);
    }
    while (true)
    {
      if ((this.mSelectedPosition != -1) && (isShown()) && (!isInTouchMode()))
        sendAccessibilityEvent(4);
      return;
      label78: fireOnSelected();
    }
  }

  public abstract void setAdapter(T paramT);

  public void setEmptyView(View paramView)
  {
    this.mEmptyView = paramView;
    Adapter localAdapter = getAdapter();
    if ((localAdapter == null) || (localAdapter.isEmpty()));
    for (boolean bool = true; ; bool = false)
    {
      updateEmptyStatus(bool);
      return;
    }
  }

  public void setFocusable(boolean paramBoolean)
  {
    boolean bool1 = true;
    Adapter localAdapter = getAdapter();
    boolean bool2;
    if ((localAdapter == null) || (localAdapter.getCount() == 0))
    {
      bool2 = bool1;
      this.mDesiredFocusableState = paramBoolean;
      if (!paramBoolean)
        this.mDesiredFocusableInTouchModeState = false;
      if ((!paramBoolean) || ((bool2) && (!isInFilterMode())))
        break label65;
    }
    while (true)
    {
      super.setFocusable(bool1);
      return;
      bool2 = false;
      break;
      label65: bool1 = false;
    }
  }

  public void setFocusableInTouchMode(boolean paramBoolean)
  {
    boolean bool1 = true;
    Adapter localAdapter = getAdapter();
    boolean bool2;
    if ((localAdapter == null) || (localAdapter.getCount() == 0))
    {
      bool2 = bool1;
      this.mDesiredFocusableInTouchModeState = paramBoolean;
      if (paramBoolean)
        this.mDesiredFocusableState = bool1;
      if ((!paramBoolean) || ((bool2) && (!isInFilterMode())))
        break label65;
    }
    while (true)
    {
      super.setFocusableInTouchMode(bool1);
      return;
      bool2 = false;
      break;
      label65: bool1 = false;
    }
  }

  void setNextSelectedPositionInt(int paramInt)
  {
    this.mNextSelectedPosition = paramInt;
    this.mNextSelectedRowId = getItemIdAtPosition(paramInt);
    if ((this.mNeedSync) && (this.mSyncMode == 0) && (paramInt >= 0))
    {
      this.mSyncPosition = paramInt;
      this.mSyncRowId = this.mNextSelectedRowId;
    }
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
  }

  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClickListener = paramOnItemClickListener;
  }

  public void setOnItemLongClickListener(OnItemLongClickListener paramOnItemLongClickListener)
  {
    if (!isLongClickable())
      setLongClickable(true);
    this.mOnItemLongClickListener = paramOnItemLongClickListener;
  }

  public void setOnItemSelectedListener(OnItemSelectedListener paramOnItemSelectedListener)
  {
    this.mOnItemSelectedListener = paramOnItemSelectedListener;
  }

  void setSelectedPositionInt(int paramInt)
  {
    this.mSelectedPosition = paramInt;
    this.mSelectedRowId = getItemIdAtPosition(paramInt);
  }

  public abstract void setSelection(int paramInt);

  public static class AdapterContextMenuInfo
    implements ContextMenu.ContextMenuInfo
  {
    public long id;
    public int position;
    public View targetView;

    public AdapterContextMenuInfo(View paramView, int paramInt, long paramLong)
    {
      this.targetView = paramView;
      this.position = paramInt;
      this.id = paramLong;
    }
  }

  class AdapterDataSetObserver extends DataSetObserver
  {
    private Parcelable mInstanceState = null;

    AdapterDataSetObserver()
    {
    }

    public void clearSavedState()
    {
      this.mInstanceState = null;
    }

    public void onChanged()
    {
      AdapterViewICS.this.mDataChanged = true;
      AdapterViewICS.this.mOldItemCount = AdapterViewICS.this.mItemCount;
      AdapterViewICS.this.mItemCount = AdapterViewICS.this.getAdapter().getCount();
      if ((AdapterViewICS.this.getAdapter().hasStableIds()) && (this.mInstanceState != null) && (AdapterViewICS.this.mOldItemCount == 0) && (AdapterViewICS.this.mItemCount > 0))
      {
        AdapterViewICS.this.onRestoreInstanceState(this.mInstanceState);
        this.mInstanceState = null;
      }
      while (true)
      {
        AdapterViewICS.this.checkFocus();
        AdapterViewICS.this.requestLayout();
        return;
        AdapterViewICS.this.rememberSyncState();
      }
    }

    public void onInvalidated()
    {
      AdapterViewICS.this.mDataChanged = true;
      if (AdapterViewICS.this.getAdapter().hasStableIds())
        this.mInstanceState = AdapterViewICS.this.onSaveInstanceState();
      AdapterViewICS.this.mOldItemCount = AdapterViewICS.this.mItemCount;
      AdapterViewICS.this.mItemCount = 0;
      AdapterViewICS.this.mSelectedPosition = -1;
      AdapterViewICS.this.mSelectedRowId = -9223372036854775808L;
      AdapterViewICS.this.mNextSelectedPosition = -1;
      AdapterViewICS.this.mNextSelectedRowId = -9223372036854775808L;
      AdapterViewICS.this.mNeedSync = false;
      AdapterViewICS.this.checkFocus();
      AdapterViewICS.this.requestLayout();
    }
  }

  public static abstract interface OnItemClickListener
  {
    public abstract void onItemClick(AdapterViewICS<?> paramAdapterViewICS, View paramView, int paramInt, long paramLong);
  }

  class OnItemClickListenerWrapper
    implements AdapterView.OnItemClickListener
  {
    private final AdapterViewICS.OnItemClickListener mWrappedListener;

    public OnItemClickListenerWrapper(AdapterViewICS.OnItemClickListener arg2)
    {
      Object localObject;
      this.mWrappedListener = localObject;
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      this.mWrappedListener.onItemClick(AdapterViewICS.this, paramView, paramInt, paramLong);
    }
  }

  public static abstract interface OnItemLongClickListener
  {
    public abstract boolean onItemLongClick(AdapterViewICS<?> paramAdapterViewICS, View paramView, int paramInt, long paramLong);
  }

  public static abstract interface OnItemSelectedListener
  {
    public abstract void onItemSelected(AdapterViewICS<?> paramAdapterViewICS, View paramView, int paramInt, long paramLong);

    public abstract void onNothingSelected(AdapterViewICS<?> paramAdapterViewICS);
  }

  private class SelectionNotifier
    implements Runnable
  {
    private SelectionNotifier()
    {
    }

    public void run()
    {
      if (AdapterViewICS.this.mDataChanged)
        if (AdapterViewICS.this.getAdapter() != null)
          AdapterViewICS.this.post(this);
      while (true)
      {
        return;
        AdapterViewICS.this.fireOnSelected();
      }
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.internal.widget.AdapterViewICS
 * JD-Core Version:    0.6.2
 */