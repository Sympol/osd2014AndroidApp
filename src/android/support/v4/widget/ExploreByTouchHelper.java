package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
  private static final String DEFAULT_CLASS_NAME = View.class.getName();
  public static final int INVALID_ID = -2147483648;
  private int mFocusedVirtualViewId = -2147483648;
  private int mHoveredVirtualViewId = -2147483648;
  private final AccessibilityManager mManager;
  private ExploreByTouchNodeProvider mNodeProvider;
  private final int[] mTempGlobalRect = new int[2];
  private final Rect mTempParentRect = new Rect();
  private final Rect mTempScreenRect = new Rect();
  private final Rect mTempVisibleRect = new Rect();
  private final View mView;

  public ExploreByTouchHelper(View paramView)
  {
    if (paramView == null)
      throw new IllegalArgumentException("View may not be null");
    this.mView = paramView;
    this.mManager = ((AccessibilityManager)paramView.getContext().getSystemService("accessibility"));
  }

  private boolean clearAccessibilityFocus(int paramInt)
  {
    if (isAccessibilityFocused(paramInt))
    {
      this.mFocusedVirtualViewId = -2147483648;
      this.mView.invalidate();
      sendEventForVirtualView(paramInt, 65536);
    }
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private AccessibilityEvent createEvent(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
    case -1:
    }
    for (AccessibilityEvent localAccessibilityEvent = createEventForChild(paramInt1, paramInt2); ; localAccessibilityEvent = createEventForHost(paramInt2))
      return localAccessibilityEvent;
  }

  private AccessibilityEvent createEventForChild(int paramInt1, int paramInt2)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt2);
    localAccessibilityEvent.setEnabled(true);
    localAccessibilityEvent.setClassName(DEFAULT_CLASS_NAME);
    onPopulateEventForVirtualView(paramInt1, localAccessibilityEvent);
    if ((localAccessibilityEvent.getText().isEmpty()) && (localAccessibilityEvent.getContentDescription() == null))
      throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
    localAccessibilityEvent.setPackageName(this.mView.getContext().getPackageName());
    AccessibilityEventCompat.asRecord(localAccessibilityEvent).setSource(this.mView, paramInt1);
    return localAccessibilityEvent;
  }

  private AccessibilityEvent createEventForHost(int paramInt)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt);
    ViewCompat.onInitializeAccessibilityEvent(this.mView, localAccessibilityEvent);
    return localAccessibilityEvent;
  }

  private AccessibilityNodeInfoCompat createNode(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case -1:
    }
    for (AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = createNodeForChild(paramInt); ; localAccessibilityNodeInfoCompat = createNodeForHost())
      return localAccessibilityNodeInfoCompat;
  }

  private AccessibilityNodeInfoCompat createNodeForChild(int paramInt)
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain();
    localAccessibilityNodeInfoCompat.setEnabled(true);
    localAccessibilityNodeInfoCompat.setClassName(DEFAULT_CLASS_NAME);
    onPopulateNodeForVirtualView(paramInt, localAccessibilityNodeInfoCompat);
    if ((localAccessibilityNodeInfoCompat.getText() == null) && (localAccessibilityNodeInfoCompat.getContentDescription() == null))
      throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
    localAccessibilityNodeInfoCompat.getBoundsInParent(this.mTempParentRect);
    if (this.mTempParentRect.isEmpty())
      throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    int i = localAccessibilityNodeInfoCompat.getActions();
    if ((i & 0x40) != 0)
      throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    if ((i & 0x80) != 0)
      throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    localAccessibilityNodeInfoCompat.setPackageName(this.mView.getContext().getPackageName());
    localAccessibilityNodeInfoCompat.setSource(this.mView, paramInt);
    localAccessibilityNodeInfoCompat.setParent(this.mView);
    if (this.mFocusedVirtualViewId == paramInt)
    {
      localAccessibilityNodeInfoCompat.setAccessibilityFocused(true);
      localAccessibilityNodeInfoCompat.addAction(128);
    }
    while (true)
    {
      if (intersectVisibleToUser(this.mTempParentRect))
      {
        localAccessibilityNodeInfoCompat.setVisibleToUser(true);
        localAccessibilityNodeInfoCompat.setBoundsInParent(this.mTempParentRect);
      }
      this.mView.getLocationOnScreen(this.mTempGlobalRect);
      int j = this.mTempGlobalRect[0];
      int k = this.mTempGlobalRect[1];
      this.mTempScreenRect.set(this.mTempParentRect);
      this.mTempScreenRect.offset(j, k);
      localAccessibilityNodeInfoCompat.setBoundsInScreen(this.mTempScreenRect);
      return localAccessibilityNodeInfoCompat;
      localAccessibilityNodeInfoCompat.setAccessibilityFocused(false);
      localAccessibilityNodeInfoCompat.addAction(64);
    }
  }

  private AccessibilityNodeInfoCompat createNodeForHost()
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(this.mView);
    ViewCompat.onInitializeAccessibilityNodeInfo(this.mView, localAccessibilityNodeInfoCompat);
    LinkedList localLinkedList = new LinkedList();
    getVisibleVirtualViews(localLinkedList);
    Iterator localIterator = localLinkedList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      localAccessibilityNodeInfoCompat.addChild(this.mView, localInteger.intValue());
    }
    return localAccessibilityNodeInfoCompat;
  }

  private boolean intersectVisibleToUser(Rect paramRect)
  {
    boolean bool1 = false;
    if (paramRect != null)
    {
      boolean bool2 = paramRect.isEmpty();
      bool1 = false;
      if (!bool2)
        break label19;
    }
    while (true)
    {
      return bool1;
      label19: int i = this.mView.getWindowVisibility();
      bool1 = false;
      if (i == 0)
      {
        View localView;
        for (ViewParent localViewParent = this.mView.getParent(); ; localViewParent = localView.getParent())
        {
          if (!(localViewParent instanceof View))
            break label99;
          localView = (View)localViewParent;
          boolean bool4 = ViewCompat.getAlpha(localView) < 0.0F;
          bool1 = false;
          if (!bool4)
            break;
          int j = localView.getVisibility();
          bool1 = false;
          if (j != 0)
            break;
        }
        label99: bool1 = false;
        if (localViewParent != null)
        {
          boolean bool3 = this.mView.getLocalVisibleRect(this.mTempVisibleRect);
          bool1 = false;
          if (bool3)
            bool1 = paramRect.intersect(this.mTempVisibleRect);
        }
      }
    }
  }

  private boolean isAccessibilityFocused(int paramInt)
  {
    if (this.mFocusedVirtualViewId == paramInt);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean manageFocusForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    boolean bool;
    switch (paramInt2)
    {
    default:
      bool = false;
    case 64:
    case 128:
    }
    while (true)
    {
      return bool;
      bool = requestAccessibilityFocus(paramInt1);
      continue;
      bool = clearAccessibilityFocus(paramInt1);
    }
  }

  private boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    switch (paramInt1)
    {
    default:
    case -1:
    }
    for (boolean bool = performActionForChild(paramInt1, paramInt2, paramBundle); ; bool = performActionForHost(paramInt2, paramBundle))
      return bool;
  }

  private boolean performActionForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    switch (paramInt2)
    {
    default:
    case 64:
    case 128:
    }
    for (boolean bool = onPerformActionForVirtualView(paramInt1, paramInt2, paramBundle); ; bool = manageFocusForChild(paramInt1, paramInt2, paramBundle))
      return bool;
  }

  private boolean performActionForHost(int paramInt, Bundle paramBundle)
  {
    return ViewCompat.performAccessibilityAction(this.mView, paramInt, paramBundle);
  }

  private boolean requestAccessibilityFocus(int paramInt)
  {
    boolean bool1 = this.mManager.isEnabled();
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager);
      bool2 = false;
      if (bool3)
        break label32;
    }
    while (true)
    {
      return bool2;
      label32: boolean bool4 = isAccessibilityFocused(paramInt);
      bool2 = false;
      if (!bool4)
      {
        this.mFocusedVirtualViewId = paramInt;
        this.mView.invalidate();
        sendEventForVirtualView(paramInt, 32768);
        bool2 = true;
      }
    }
  }

  private void updateHoveredVirtualView(int paramInt)
  {
    if (this.mHoveredVirtualViewId == paramInt);
    while (true)
    {
      return;
      int i = this.mHoveredVirtualViewId;
      this.mHoveredVirtualViewId = paramInt;
      sendEventForVirtualView(paramInt, 128);
      sendEventForVirtualView(i, 256);
    }
  }

  public boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1 = true;
    boolean bool2 = this.mManager.isEnabled();
    boolean bool3 = false;
    if (bool2)
    {
      boolean bool4 = AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager);
      bool3 = false;
      if (bool4)
        break label37;
    }
    while (true)
    {
      return bool3;
      label37: switch (paramMotionEvent.getAction())
      {
      case 8:
      default:
        bool3 = false;
        break;
      case 7:
      case 9:
        int j = getVirtualViewAt(paramMotionEvent.getX(), paramMotionEvent.getY());
        updateHoveredVirtualView(j);
        if (j != -2147483648);
        while (true)
        {
          bool3 = bool1;
          break;
          bool1 = false;
        }
      case 10:
        int i = this.mFocusedVirtualViewId;
        bool3 = false;
        if (i != -2147483648)
        {
          updateHoveredVirtualView(-2147483648);
          bool3 = bool1;
        }
        break;
      }
    }
  }

  public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
  {
    if (this.mNodeProvider == null)
      this.mNodeProvider = new ExploreByTouchNodeProvider(null);
    return this.mNodeProvider;
  }

  public int getFocusedVirtualView()
  {
    return this.mFocusedVirtualViewId;
  }

  protected abstract int getVirtualViewAt(float paramFloat1, float paramFloat2);

  protected abstract void getVisibleVirtualViews(List<Integer> paramList);

  public void invalidateRoot()
  {
    invalidateVirtualView(-1);
  }

  public void invalidateVirtualView(int paramInt)
  {
    sendEventForVirtualView(paramInt, 2048);
  }

  protected abstract boolean onPerformActionForVirtualView(int paramInt1, int paramInt2, Bundle paramBundle);

  protected abstract void onPopulateEventForVirtualView(int paramInt, AccessibilityEvent paramAccessibilityEvent);

  protected abstract void onPopulateNodeForVirtualView(int paramInt, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);

  public boolean sendEventForVirtualView(int paramInt1, int paramInt2)
  {
    boolean bool1 = false;
    if (paramInt1 != -2147483648)
    {
      boolean bool2 = this.mManager.isEnabled();
      bool1 = false;
      if (bool2)
        break label26;
    }
    while (true)
    {
      return bool1;
      label26: ViewParent localViewParent = this.mView.getParent();
      bool1 = false;
      if (localViewParent != null)
      {
        AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, paramInt2);
        bool1 = ViewParentCompat.requestSendAccessibilityEvent(localViewParent, this.mView, localAccessibilityEvent);
      }
    }
  }

  private class ExploreByTouchNodeProvider extends AccessibilityNodeProviderCompat
  {
    private ExploreByTouchNodeProvider()
    {
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
    {
      return ExploreByTouchHelper.this.createNode(paramInt);
    }

    public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      return ExploreByTouchHelper.this.performAction(paramInt1, paramInt2, paramBundle);
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ExploreByTouchHelper
 * JD-Core Version:    0.6.2
 */