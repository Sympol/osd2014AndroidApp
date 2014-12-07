package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import java.util.Arrays;

public class ViewDragHelper
{
  private static final int BASE_SETTLE_DURATION = 256;
  public static final int DIRECTION_ALL = 3;
  public static final int DIRECTION_HORIZONTAL = 1;
  public static final int DIRECTION_VERTICAL = 2;
  public static final int EDGE_ALL = 15;
  public static final int EDGE_BOTTOM = 8;
  public static final int EDGE_LEFT = 1;
  public static final int EDGE_RIGHT = 2;
  private static final int EDGE_SIZE = 20;
  public static final int EDGE_TOP = 4;
  public static final int INVALID_POINTER = -1;
  private static final int MAX_SETTLE_DURATION = 600;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_IDLE = 0;
  public static final int STATE_SETTLING = 2;
  private static final String TAG = "ViewDragHelper";
  private static final Interpolator sInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      float f = paramAnonymousFloat - 1.0F;
      return 1.0F + f * (f * (f * (f * f)));
    }
  };
  private int mActivePointerId = -1;
  private final Callback mCallback;
  private View mCapturedView;
  private int mDragState;
  private int[] mEdgeDragsInProgress;
  private int[] mEdgeDragsLocked;
  private int mEdgeSize;
  private int[] mInitialEdgesTouched;
  private float[] mInitialMotionX;
  private float[] mInitialMotionY;
  private float[] mLastMotionX;
  private float[] mLastMotionY;
  private float mMaxVelocity;
  private float mMinVelocity;
  private final ViewGroup mParentView;
  private int mPointersDown;
  private boolean mReleaseInProgress;
  private ScrollerCompat mScroller;
  private final Runnable mSetIdleRunnable = new Runnable()
  {
    public void run()
    {
      ViewDragHelper.this.setDragState(0);
    }
  };
  private int mTouchSlop;
  private int mTrackingEdges;
  private VelocityTracker mVelocityTracker;

  private ViewDragHelper(Context paramContext, ViewGroup paramViewGroup, Callback paramCallback)
  {
    if (paramViewGroup == null)
      throw new IllegalArgumentException("Parent view may not be null");
    if (paramCallback == null)
      throw new IllegalArgumentException("Callback may not be null");
    this.mParentView = paramViewGroup;
    this.mCallback = paramCallback;
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(paramContext);
    this.mEdgeSize = ((int)(0.5F + 20.0F * paramContext.getResources().getDisplayMetrics().density));
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMaxVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    this.mMinVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mScroller = ScrollerCompat.create(paramContext, sInterpolator);
  }

  private boolean checkNewEdgeDrag(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    float f1 = Math.abs(paramFloat1);
    float f2 = Math.abs(paramFloat2);
    int i = paramInt2 & this.mInitialEdgesTouched[paramInt1];
    boolean bool1 = false;
    if (i == paramInt2)
    {
      int j = paramInt2 & this.mTrackingEdges;
      bool1 = false;
      if (j != 0)
      {
        int k = paramInt2 & this.mEdgeDragsLocked[paramInt1];
        bool1 = false;
        if (k != paramInt2)
        {
          int m = paramInt2 & this.mEdgeDragsInProgress[paramInt1];
          bool1 = false;
          if (m != paramInt2)
          {
            if (f1 > this.mTouchSlop)
              break label124;
            boolean bool3 = f2 < this.mTouchSlop;
            bool1 = false;
            if (bool3)
              break label124;
          }
        }
      }
    }
    while (true)
    {
      return bool1;
      label124: if ((f1 < 0.5F * f2) && (this.mCallback.onEdgeLock(paramInt2)))
      {
        int[] arrayOfInt = this.mEdgeDragsLocked;
        arrayOfInt[paramInt1] = (paramInt2 | arrayOfInt[paramInt1]);
        bool1 = false;
      }
      else
      {
        int n = paramInt2 & this.mEdgeDragsInProgress[paramInt1];
        bool1 = false;
        if (n == 0)
        {
          boolean bool2 = f1 < this.mTouchSlop;
          bool1 = false;
          if (bool2)
            bool1 = true;
        }
      }
    }
  }

  private boolean checkTouchSlop(View paramView, float paramFloat1, float paramFloat2)
  {
    boolean bool1 = true;
    if (paramView == null)
      bool1 = false;
    while (true)
    {
      return bool1;
      boolean bool2;
      if (this.mCallback.getViewHorizontalDragRange(paramView) > 0)
      {
        bool2 = bool1;
        label28: if (this.mCallback.getViewVerticalDragRange(paramView) <= 0)
          break label86;
      }
      label86: for (boolean bool3 = bool1; ; bool3 = false)
      {
        if ((!bool2) || (!bool3))
          break label92;
        if (paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 > this.mTouchSlop * this.mTouchSlop)
          break;
        bool1 = false;
        break;
        bool2 = false;
        break label28;
      }
      label92: if (bool2)
      {
        if (Math.abs(paramFloat1) <= this.mTouchSlop)
          bool1 = false;
      }
      else if (bool3)
      {
        if (Math.abs(paramFloat2) <= this.mTouchSlop)
          bool1 = false;
      }
      else
        bool1 = false;
    }
  }

  private float clampMag(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f = Math.abs(paramFloat1);
    if (f < paramFloat2)
      paramFloat3 = 0.0F;
    while (true)
    {
      return paramFloat3;
      if (f > paramFloat3)
      {
        if (paramFloat1 <= 0.0F)
          paramFloat3 = -paramFloat3;
      }
      else
        paramFloat3 = paramFloat1;
    }
  }

  private int clampMag(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = Math.abs(paramInt1);
    if (i < paramInt2)
      paramInt3 = 0;
    while (true)
    {
      return paramInt3;
      if (i > paramInt3)
      {
        if (paramInt1 <= 0)
          paramInt3 = -paramInt3;
      }
      else
        paramInt3 = paramInt1;
    }
  }

  private void clearMotionHistory()
  {
    if (this.mInitialMotionX == null);
    while (true)
    {
      return;
      Arrays.fill(this.mInitialMotionX, 0.0F);
      Arrays.fill(this.mInitialMotionY, 0.0F);
      Arrays.fill(this.mLastMotionX, 0.0F);
      Arrays.fill(this.mLastMotionY, 0.0F);
      Arrays.fill(this.mInitialEdgesTouched, 0);
      Arrays.fill(this.mEdgeDragsInProgress, 0);
      Arrays.fill(this.mEdgeDragsLocked, 0);
      this.mPointersDown = 0;
    }
  }

  private void clearMotionHistory(int paramInt)
  {
    if (this.mInitialMotionX == null);
    while (true)
    {
      return;
      this.mInitialMotionX[paramInt] = 0.0F;
      this.mInitialMotionY[paramInt] = 0.0F;
      this.mLastMotionX[paramInt] = 0.0F;
      this.mLastMotionY[paramInt] = 0.0F;
      this.mInitialEdgesTouched[paramInt] = 0;
      this.mEdgeDragsInProgress[paramInt] = 0;
      this.mEdgeDragsLocked[paramInt] = 0;
      this.mPointersDown &= (0xFFFFFFFF ^ 1 << paramInt);
    }
  }

  private int computeAxisDuration(int paramInt1, int paramInt2, int paramInt3)
  {
    int n;
    if (paramInt1 == 0)
    {
      n = 0;
      return n;
    }
    int i = this.mParentView.getWidth();
    int j = i / 2;
    float f1 = Math.min(1.0F, Math.abs(paramInt1) / i);
    float f2 = j + j * distanceInfluenceForSnapDuration(f1);
    int k = Math.abs(paramInt2);
    if (k > 0);
    for (int m = 4 * Math.round(1000.0F * Math.abs(f2 / k)); ; m = (int)(256.0F * (1.0F + Math.abs(paramInt1) / paramInt3)))
    {
      n = Math.min(m, 600);
      break;
    }
  }

  private int computeSettleDuration(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = clampMag(paramInt3, (int)this.mMinVelocity, (int)this.mMaxVelocity);
    int j = clampMag(paramInt4, (int)this.mMinVelocity, (int)this.mMaxVelocity);
    int k = Math.abs(paramInt1);
    int m = Math.abs(paramInt2);
    int n = Math.abs(i);
    int i1 = Math.abs(j);
    int i2 = n + i1;
    int i3 = k + m;
    float f1;
    if (i != 0)
    {
      f1 = n / i2;
      if (j == 0)
        break label165;
    }
    label165: for (float f2 = i1 / i2; ; f2 = m / i3)
    {
      int i4 = computeAxisDuration(paramInt1, i, this.mCallback.getViewHorizontalDragRange(paramView));
      int i5 = computeAxisDuration(paramInt2, j, this.mCallback.getViewVerticalDragRange(paramView));
      return (int)(f1 * i4 + f2 * i5);
      f1 = k / i3;
      break;
    }
  }

  public static ViewDragHelper create(ViewGroup paramViewGroup, float paramFloat, Callback paramCallback)
  {
    ViewDragHelper localViewDragHelper = create(paramViewGroup, paramCallback);
    localViewDragHelper.mTouchSlop = ((int)(localViewDragHelper.mTouchSlop * (1.0F / paramFloat)));
    return localViewDragHelper;
  }

  public static ViewDragHelper create(ViewGroup paramViewGroup, Callback paramCallback)
  {
    return new ViewDragHelper(paramViewGroup.getContext(), paramViewGroup, paramCallback);
  }

  private void dispatchViewReleased(float paramFloat1, float paramFloat2)
  {
    this.mReleaseInProgress = true;
    this.mCallback.onViewReleased(this.mCapturedView, paramFloat1, paramFloat2);
    this.mReleaseInProgress = false;
    if (this.mDragState == 1)
      setDragState(0);
  }

  private float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin((float)(0.47123891676382D * (paramFloat - 0.5F)));
  }

  private void dragTo(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt1;
    int j = paramInt2;
    int k = this.mCapturedView.getLeft();
    int m = this.mCapturedView.getTop();
    if (paramInt3 != 0)
    {
      i = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, paramInt1, paramInt3);
      this.mCapturedView.offsetLeftAndRight(i - k);
    }
    if (paramInt4 != 0)
    {
      j = this.mCallback.clampViewPositionVertical(this.mCapturedView, paramInt2, paramInt4);
      this.mCapturedView.offsetTopAndBottom(j - m);
    }
    if ((paramInt3 != 0) || (paramInt4 != 0))
    {
      int n = i - k;
      int i1 = j - m;
      this.mCallback.onViewPositionChanged(this.mCapturedView, i, j, n, i1);
    }
  }

  private void ensureMotionHistorySizeForId(int paramInt)
  {
    if ((this.mInitialMotionX == null) || (this.mInitialMotionX.length <= paramInt))
    {
      float[] arrayOfFloat1 = new float[paramInt + 1];
      float[] arrayOfFloat2 = new float[paramInt + 1];
      float[] arrayOfFloat3 = new float[paramInt + 1];
      float[] arrayOfFloat4 = new float[paramInt + 1];
      int[] arrayOfInt1 = new int[paramInt + 1];
      int[] arrayOfInt2 = new int[paramInt + 1];
      int[] arrayOfInt3 = new int[paramInt + 1];
      if (this.mInitialMotionX != null)
      {
        System.arraycopy(this.mInitialMotionX, 0, arrayOfFloat1, 0, this.mInitialMotionX.length);
        System.arraycopy(this.mInitialMotionY, 0, arrayOfFloat2, 0, this.mInitialMotionY.length);
        System.arraycopy(this.mLastMotionX, 0, arrayOfFloat3, 0, this.mLastMotionX.length);
        System.arraycopy(this.mLastMotionY, 0, arrayOfFloat4, 0, this.mLastMotionY.length);
        System.arraycopy(this.mInitialEdgesTouched, 0, arrayOfInt1, 0, this.mInitialEdgesTouched.length);
        System.arraycopy(this.mEdgeDragsInProgress, 0, arrayOfInt2, 0, this.mEdgeDragsInProgress.length);
        System.arraycopy(this.mEdgeDragsLocked, 0, arrayOfInt3, 0, this.mEdgeDragsLocked.length);
      }
      this.mInitialMotionX = arrayOfFloat1;
      this.mInitialMotionY = arrayOfFloat2;
      this.mLastMotionX = arrayOfFloat3;
      this.mLastMotionY = arrayOfFloat4;
      this.mInitialEdgesTouched = arrayOfInt1;
      this.mEdgeDragsInProgress = arrayOfInt2;
      this.mEdgeDragsLocked = arrayOfInt3;
    }
  }

  private boolean forceSettleCapturedViewAt(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = false;
    int i = this.mCapturedView.getLeft();
    int j = this.mCapturedView.getTop();
    int k = paramInt1 - i;
    int m = paramInt2 - j;
    if ((k == 0) && (m == 0))
    {
      this.mScroller.abortAnimation();
      setDragState(0);
    }
    while (true)
    {
      return bool;
      int n = computeSettleDuration(this.mCapturedView, k, m, paramInt3, paramInt4);
      this.mScroller.startScroll(i, j, k, m, n);
      setDragState(2);
      bool = true;
    }
  }

  private int getEdgesTouched(int paramInt1, int paramInt2)
  {
    int i = this.mParentView.getLeft() + this.mEdgeSize;
    int j = 0;
    if (paramInt1 < i)
      j = 0x0 | 0x1;
    if (paramInt2 < this.mParentView.getTop() + this.mEdgeSize)
      j |= 4;
    if (paramInt1 > this.mParentView.getRight() - this.mEdgeSize)
      j |= 2;
    if (paramInt2 > this.mParentView.getBottom() - this.mEdgeSize)
      j |= 8;
    return j;
  }

  private void releaseViewForPointerUp()
  {
    this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
    dispatchViewReleased(clampMag(VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
  }

  private void reportNewEdgeDrags(float paramFloat1, float paramFloat2, int paramInt)
  {
    boolean bool = checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 1);
    int i = 0;
    if (bool)
      i = 0x0 | 0x1;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 4))
      i |= 4;
    if (checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 2))
      i |= 2;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 8))
      i |= 8;
    if (i != 0)
    {
      int[] arrayOfInt = this.mEdgeDragsInProgress;
      arrayOfInt[paramInt] = (i | arrayOfInt[paramInt]);
      this.mCallback.onEdgeDragStarted(i, paramInt);
    }
  }

  private void saveInitialMotion(float paramFloat1, float paramFloat2, int paramInt)
  {
    ensureMotionHistorySizeForId(paramInt);
    float[] arrayOfFloat1 = this.mInitialMotionX;
    this.mLastMotionX[paramInt] = paramFloat1;
    arrayOfFloat1[paramInt] = paramFloat1;
    float[] arrayOfFloat2 = this.mInitialMotionY;
    this.mLastMotionY[paramInt] = paramFloat2;
    arrayOfFloat2[paramInt] = paramFloat2;
    this.mInitialEdgesTouched[paramInt] = getEdgesTouched((int)paramFloat1, (int)paramFloat2);
    this.mPointersDown |= 1 << paramInt;
  }

  private void saveLastMotion(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getPointerCount(paramMotionEvent);
    for (int j = 0; j < i; j++)
    {
      int k = MotionEventCompat.getPointerId(paramMotionEvent, j);
      float f1 = MotionEventCompat.getX(paramMotionEvent, j);
      float f2 = MotionEventCompat.getY(paramMotionEvent, j);
      this.mLastMotionX[k] = f1;
      this.mLastMotionY[k] = f2;
    }
  }

  public void abort()
  {
    cancel();
    if (this.mDragState == 2)
    {
      int i = this.mScroller.getCurrX();
      int j = this.mScroller.getCurrY();
      this.mScroller.abortAnimation();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      this.mCallback.onViewPositionChanged(this.mCapturedView, k, m, k - i, m - j);
    }
    setDragState(0);
  }

  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int k;
    boolean bool;
    if ((paramView instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int i = paramView.getScrollX();
      int j = paramView.getScrollY();
      k = -1 + localViewGroup.getChildCount();
      if (k >= 0)
      {
        View localView = localViewGroup.getChildAt(k);
        if ((paramInt3 + i >= localView.getLeft()) && (paramInt3 + i < localView.getRight()) && (paramInt4 + j >= localView.getTop()) && (paramInt4 + j < localView.getBottom()) && (canScroll(localView, true, paramInt1, paramInt2, paramInt3 + i - localView.getLeft(), paramInt4 + j - localView.getTop())))
          bool = true;
      }
    }
    while (true)
    {
      return bool;
      k--;
      break;
      if ((paramBoolean) && ((ViewCompat.canScrollHorizontally(paramView, -paramInt1)) || (ViewCompat.canScrollVertically(paramView, -paramInt2))))
        bool = true;
      else
        bool = false;
    }
  }

  public void cancel()
  {
    this.mActivePointerId = -1;
    clearMotionHistory();
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }

  public void captureChildView(View paramView, int paramInt)
  {
    if (paramView.getParent() != this.mParentView)
      throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
    this.mCapturedView = paramView;
    this.mActivePointerId = paramInt;
    this.mCallback.onViewCaptured(paramView, paramInt);
    setDragState(1);
  }

  public boolean checkTouchSlop(int paramInt)
  {
    int i = this.mInitialMotionX.length;
    int j = 0;
    if (j < i)
      if (!checkTouchSlop(paramInt, j));
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      j++;
      break;
    }
  }

  public boolean checkTouchSlop(int paramInt1, int paramInt2)
  {
    int i = 1;
    if (!isPointerDown(paramInt2))
      i = 0;
    while (true)
    {
      return i;
      int k;
      if ((paramInt1 & 0x1) == i)
      {
        k = i;
        label24: if ((paramInt1 & 0x2) != 2)
          break label110;
      }
      float f1;
      float f2;
      int j;
      int m;
      label110: int i1;
      for (int n = i; ; i1 = 0)
      {
        f1 = this.mLastMotionX[paramInt2] - this.mInitialMotionX[paramInt2];
        f2 = this.mLastMotionY[paramInt2] - this.mInitialMotionY[paramInt2];
        if ((k == 0) || (n == 0))
          break label116;
        if (f1 * f1 + f2 * f2 > this.mTouchSlop * this.mTouchSlop)
          break;
        j = 0;
        break;
        m = 0;
        break label24;
      }
      label116: if (m != 0)
      {
        if (Math.abs(f1) <= this.mTouchSlop)
          j = 0;
      }
      else if (i1 != 0)
      {
        if (Math.abs(f2) <= this.mTouchSlop)
          j = 0;
      }
      else
        j = 0;
    }
  }

  public boolean continueSettling(boolean paramBoolean)
  {
    if (this.mDragState == 2)
    {
      boolean bool2 = this.mScroller.computeScrollOffset();
      int i = this.mScroller.getCurrX();
      int j = this.mScroller.getCurrY();
      int k = i - this.mCapturedView.getLeft();
      int m = j - this.mCapturedView.getTop();
      if (k != 0)
        this.mCapturedView.offsetLeftAndRight(k);
      if (m != 0)
        this.mCapturedView.offsetTopAndBottom(m);
      if ((k != 0) || (m != 0))
        this.mCallback.onViewPositionChanged(this.mCapturedView, i, j, k, m);
      if ((bool2) && (i == this.mScroller.getFinalX()) && (j == this.mScroller.getFinalY()))
      {
        this.mScroller.abortAnimation();
        bool2 = this.mScroller.isFinished();
      }
      if (!bool2)
      {
        if (!paramBoolean)
          break label190;
        this.mParentView.post(this.mSetIdleRunnable);
      }
    }
    if (this.mDragState == 2);
    for (boolean bool1 = true; ; bool1 = false)
    {
      return bool1;
      label190: setDragState(0);
      break;
    }
  }

  public View findTopChildUnder(int paramInt1, int paramInt2)
  {
    int i = -1 + this.mParentView.getChildCount();
    View localView;
    if (i >= 0)
    {
      localView = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(i));
      if ((paramInt1 < localView.getLeft()) || (paramInt1 >= localView.getRight()) || (paramInt2 < localView.getTop()) || (paramInt2 >= localView.getBottom()));
    }
    while (true)
    {
      return localView;
      i--;
      break;
      localView = null;
    }
  }

  public void flingCapturedView(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!this.mReleaseInProgress)
      throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
    this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int)VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId), (int)VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId), paramInt1, paramInt3, paramInt2, paramInt4);
    setDragState(2);
  }

  public int getActivePointerId()
  {
    return this.mActivePointerId;
  }

  public View getCapturedView()
  {
    return this.mCapturedView;
  }

  public int getEdgeSize()
  {
    return this.mEdgeSize;
  }

  public float getMinVelocity()
  {
    return this.mMinVelocity;
  }

  public int getTouchSlop()
  {
    return this.mTouchSlop;
  }

  public int getViewDragState()
  {
    return this.mDragState;
  }

  public boolean isCapturedViewUnder(int paramInt1, int paramInt2)
  {
    return isViewUnder(this.mCapturedView, paramInt1, paramInt2);
  }

  public boolean isEdgeTouched(int paramInt)
  {
    int i = this.mInitialEdgesTouched.length;
    int j = 0;
    if (j < i)
      if (!isEdgeTouched(paramInt, j));
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      j++;
      break;
    }
  }

  public boolean isEdgeTouched(int paramInt1, int paramInt2)
  {
    if ((isPointerDown(paramInt2)) && ((paramInt1 & this.mInitialEdgesTouched[paramInt2]) != 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isPointerDown(int paramInt)
  {
    int i = 1;
    if ((this.mPointersDown & i << paramInt) != 0);
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public boolean isViewUnder(View paramView, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    if (paramView == null);
    while (true)
    {
      return bool;
      int i = paramView.getLeft();
      bool = false;
      if (paramInt1 >= i)
      {
        int j = paramView.getRight();
        bool = false;
        if (paramInt1 < j)
        {
          int k = paramView.getTop();
          bool = false;
          if (paramInt2 >= k)
          {
            int m = paramView.getBottom();
            bool = false;
            if (paramInt2 < m)
              bool = true;
          }
        }
      }
    }
  }

  public void processTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    int j = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (i == 0)
      cancel();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (i)
    {
    case 4:
    default:
    case 0:
    case 5:
    case 2:
    case 6:
    case 1:
    case 3:
    }
    while (true)
    {
      return;
      float f11 = paramMotionEvent.getX();
      float f12 = paramMotionEvent.getY();
      int i11 = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      View localView2 = findTopChildUnder((int)f11, (int)f12);
      saveInitialMotion(f11, f12, i11);
      tryCaptureViewForDrag(localView2, i11);
      int i12 = this.mInitialEdgesTouched[i11];
      if ((i12 & this.mTrackingEdges) != 0)
      {
        this.mCallback.onEdgeTouched(i12 & this.mTrackingEdges, i11);
        continue;
        int i9 = MotionEventCompat.getPointerId(paramMotionEvent, j);
        float f9 = MotionEventCompat.getX(paramMotionEvent, j);
        float f10 = MotionEventCompat.getY(paramMotionEvent, j);
        saveInitialMotion(f9, f10, i9);
        if (this.mDragState == 0)
        {
          tryCaptureViewForDrag(findTopChildUnder((int)f9, (int)f10), i9);
          int i10 = this.mInitialEdgesTouched[i9];
          if ((i10 & this.mTrackingEdges) != 0)
            this.mCallback.onEdgeTouched(i10 & this.mTrackingEdges, i9);
        }
        else if (isCapturedViewUnder((int)f9, (int)f10))
        {
          tryCaptureViewForDrag(this.mCapturedView, i9);
          continue;
          if (this.mDragState == 1)
          {
            int i6 = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
            float f7 = MotionEventCompat.getX(paramMotionEvent, i6);
            float f8 = MotionEventCompat.getY(paramMotionEvent, i6);
            int i7 = (int)(f7 - this.mLastMotionX[this.mActivePointerId]);
            int i8 = (int)(f8 - this.mLastMotionY[this.mActivePointerId]);
            dragTo(i7 + this.mCapturedView.getLeft(), i8 + this.mCapturedView.getTop(), i7, i8);
            saveLastMotion(paramMotionEvent);
          }
          else
          {
            int i3 = MotionEventCompat.getPointerCount(paramMotionEvent);
            for (int i4 = 0; ; i4++)
            {
              int i5;
              float f3;
              float f4;
              float f5;
              float f6;
              if (i4 < i3)
              {
                i5 = MotionEventCompat.getPointerId(paramMotionEvent, i4);
                f3 = MotionEventCompat.getX(paramMotionEvent, i4);
                f4 = MotionEventCompat.getY(paramMotionEvent, i4);
                f5 = f3 - this.mInitialMotionX[i5];
                f6 = f4 - this.mInitialMotionY[i5];
                reportNewEdgeDrags(f5, f6, i5);
                if (this.mDragState != 1)
                  break label483;
              }
              label483: View localView1;
              do
              {
                saveLastMotion(paramMotionEvent);
                break;
                localView1 = findTopChildUnder((int)f3, (int)f4);
              }
              while ((checkTouchSlop(localView1, f5, f6)) && (tryCaptureViewForDrag(localView1, i5)));
            }
            int k = MotionEventCompat.getPointerId(paramMotionEvent, j);
            if ((this.mDragState == 1) && (k == this.mActivePointerId))
            {
              int m = -1;
              int n = MotionEventCompat.getPointerCount(paramMotionEvent);
              int i1 = 0;
              if (i1 < n)
              {
                int i2 = MotionEventCompat.getPointerId(paramMotionEvent, i1);
                if (i2 == this.mActivePointerId);
                float f1;
                float f2;
                do
                {
                  i1++;
                  break;
                  f1 = MotionEventCompat.getX(paramMotionEvent, i1);
                  f2 = MotionEventCompat.getY(paramMotionEvent, i1);
                }
                while ((findTopChildUnder((int)f1, (int)f2) != this.mCapturedView) || (!tryCaptureViewForDrag(this.mCapturedView, i2)));
                m = this.mActivePointerId;
              }
              if (m == -1)
                releaseViewForPointerUp();
            }
            clearMotionHistory(k);
            continue;
            if (this.mDragState == 1)
              releaseViewForPointerUp();
            cancel();
            continue;
            if (this.mDragState == 1)
              dispatchViewReleased(0.0F, 0.0F);
            cancel();
          }
        }
      }
    }
  }

  void setDragState(int paramInt)
  {
    if (this.mDragState != paramInt)
    {
      this.mDragState = paramInt;
      this.mCallback.onViewDragStateChanged(paramInt);
      if (paramInt == 0)
        this.mCapturedView = null;
    }
  }

  public void setEdgeTrackingEnabled(int paramInt)
  {
    this.mTrackingEdges = paramInt;
  }

  public void setMinVelocity(float paramFloat)
  {
    this.mMinVelocity = paramFloat;
  }

  public boolean settleCapturedViewAt(int paramInt1, int paramInt2)
  {
    if (!this.mReleaseInProgress)
      throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    return forceSettleCapturedViewAt(paramInt1, paramInt2, (int)VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId), (int)VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId));
  }

  public boolean shouldInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    int j = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (i == 0)
      cancel();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (i)
    {
    case 4:
    default:
      if (this.mDragState != 1)
        break;
    case 0:
    case 5:
    case 2:
    case 6:
    case 1:
    case 3:
    }
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      float f7 = paramMotionEvent.getX();
      float f8 = paramMotionEvent.getY();
      int i3 = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      saveInitialMotion(f7, f8, i3);
      View localView3 = findTopChildUnder((int)f7, (int)f8);
      if ((localView3 == this.mCapturedView) && (this.mDragState == 2))
        tryCaptureViewForDrag(localView3, i3);
      int i4 = this.mInitialEdgesTouched[i3];
      if ((i4 & this.mTrackingEdges) == 0)
        break;
      this.mCallback.onEdgeTouched(i4 & this.mTrackingEdges, i3);
      break;
      int i1 = MotionEventCompat.getPointerId(paramMotionEvent, j);
      float f5 = MotionEventCompat.getX(paramMotionEvent, j);
      float f6 = MotionEventCompat.getY(paramMotionEvent, j);
      saveInitialMotion(f5, f6, i1);
      if (this.mDragState == 0)
      {
        int i2 = this.mInitialEdgesTouched[i1];
        if ((i2 & this.mTrackingEdges) == 0)
          break;
        this.mCallback.onEdgeTouched(i2 & this.mTrackingEdges, i1);
        break;
      }
      if (this.mDragState != 2)
        break;
      View localView2 = findTopChildUnder((int)f5, (int)f6);
      if (localView2 != this.mCapturedView)
        break;
      tryCaptureViewForDrag(localView2, i1);
      break;
      int k = MotionEventCompat.getPointerCount(paramMotionEvent);
      for (int m = 0; ; m++)
      {
        int n;
        float f1;
        float f2;
        float f3;
        float f4;
        if (m < k)
        {
          n = MotionEventCompat.getPointerId(paramMotionEvent, m);
          f1 = MotionEventCompat.getX(paramMotionEvent, m);
          f2 = MotionEventCompat.getY(paramMotionEvent, m);
          f3 = f1 - this.mInitialMotionX[n];
          f4 = f2 - this.mInitialMotionY[n];
          reportNewEdgeDrags(f3, f4, n);
          if (this.mDragState != 1)
            break label410;
        }
        label410: View localView1;
        do
        {
          saveLastMotion(paramMotionEvent);
          break;
          localView1 = findTopChildUnder((int)f1, (int)f2);
        }
        while ((localView1 != null) && (checkTouchSlop(localView1, f3, f4)) && (tryCaptureViewForDrag(localView1, n)));
      }
      clearMotionHistory(MotionEventCompat.getPointerId(paramMotionEvent, j));
      break;
      cancel();
      break;
    }
  }

  public boolean smoothSlideViewTo(View paramView, int paramInt1, int paramInt2)
  {
    this.mCapturedView = paramView;
    this.mActivePointerId = -1;
    return forceSettleCapturedViewAt(paramInt1, paramInt2, 0, 0);
  }

  boolean tryCaptureViewForDrag(View paramView, int paramInt)
  {
    boolean bool = true;
    if ((paramView == this.mCapturedView) && (this.mActivePointerId == paramInt));
    while (true)
    {
      return bool;
      if ((paramView != null) && (this.mCallback.tryCaptureView(paramView, paramInt)))
      {
        this.mActivePointerId = paramInt;
        captureChildView(paramView, paramInt);
      }
      else
      {
        bool = false;
      }
    }
  }

  public static abstract class Callback
  {
    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      return 0;
    }

    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      return 0;
    }

    public int getOrderedChildIndex(int paramInt)
    {
      return paramInt;
    }

    public int getViewHorizontalDragRange(View paramView)
    {
      return 0;
    }

    public int getViewVerticalDragRange(View paramView)
    {
      return 0;
    }

    public void onEdgeDragStarted(int paramInt1, int paramInt2)
    {
    }

    public boolean onEdgeLock(int paramInt)
    {
      return false;
    }

    public void onEdgeTouched(int paramInt1, int paramInt2)
    {
    }

    public void onViewCaptured(View paramView, int paramInt)
    {
    }

    public void onViewDragStateChanged(int paramInt)
    {
    }

    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
    }

    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
    }

    public abstract boolean tryCaptureView(View paramView, int paramInt);
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ViewDragHelper
 * JD-Core Version:    0.6.2
 */