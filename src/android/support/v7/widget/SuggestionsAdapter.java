package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter
  implements View.OnClickListener
{
  private static final boolean DBG = false;
  static final int INVALID_INDEX = -1;
  private static final String LOG_TAG = "SuggestionsAdapter";
  private static final int QUERY_LIMIT = 50;
  static final int REFINE_ALL = 2;
  static final int REFINE_BY_ENTRY = 1;
  static final int REFINE_NONE;
  private boolean mClosed = false;
  private int mFlagsCol = -1;
  private int mIconName1Col = -1;
  private int mIconName2Col = -1;
  private WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
  private Context mProviderContext;
  private int mQueryRefinement = 1;
  private SearchManager mSearchManager = (SearchManager)this.mContext.getSystemService("search");
  private SearchView mSearchView;
  private SearchableInfo mSearchable;
  private int mText1Col = -1;
  private int mText2Col = -1;
  private int mText2UrlCol = -1;
  private ColorStateList mUrlColor;

  public SuggestionsAdapter(Context paramContext, SearchView paramSearchView, SearchableInfo paramSearchableInfo, WeakHashMap<String, Drawable.ConstantState> paramWeakHashMap)
  {
    super(paramContext, R.layout.abc_search_dropdown_item_icons_2line, null, true);
    this.mSearchView = paramSearchView;
    this.mSearchable = paramSearchableInfo;
    this.mProviderContext = paramContext;
    this.mOutsideDrawablesCache = paramWeakHashMap;
  }

  private Drawable checkIconCache(String paramString)
  {
    Drawable.ConstantState localConstantState = (Drawable.ConstantState)this.mOutsideDrawablesCache.get(paramString);
    if (localConstantState == null);
    for (Drawable localDrawable = null; ; localDrawable = localConstantState.newDrawable())
      return localDrawable;
  }

  private CharSequence formatUrl(CharSequence paramCharSequence)
  {
    if (this.mUrlColor == null)
    {
      TypedValue localTypedValue = new TypedValue();
      this.mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, localTypedValue, true);
      this.mUrlColor = this.mContext.getResources().getColorStateList(localTypedValue.resourceId);
    }
    SpannableString localSpannableString = new SpannableString(paramCharSequence);
    localSpannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.mUrlColor, null), 0, paramCharSequence.length(), 33);
    return localSpannableString;
  }

  private Drawable getActivityIcon(ComponentName paramComponentName)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    try
    {
      localActivityInfo = localPackageManager.getActivityInfo(paramComponentName, 128);
      i = localActivityInfo.getIconResource();
      if (i == 0)
      {
        localDrawable = null;
        return localDrawable;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        ActivityInfo localActivityInfo;
        int i;
        Log.w("SuggestionsAdapter", localNameNotFoundException.toString());
        Drawable localDrawable = null;
        continue;
        localDrawable = localPackageManager.getDrawable(paramComponentName.getPackageName(), i, localActivityInfo.applicationInfo);
        if (localDrawable == null)
        {
          Log.w("SuggestionsAdapter", "Invalid icon resource " + i + " for " + paramComponentName.flattenToShortString());
          localDrawable = null;
        }
      }
    }
  }

  private Drawable getActivityIconWithCache(ComponentName paramComponentName)
  {
    String str = paramComponentName.flattenToShortString();
    Object localObject2;
    if (this.mOutsideDrawablesCache.containsKey(str))
    {
      Drawable.ConstantState localConstantState = (Drawable.ConstantState)this.mOutsideDrawablesCache.get(str);
      localObject2 = null;
      if (localConstantState == null);
      while (true)
      {
        return localObject2;
        localObject2 = localConstantState.newDrawable(this.mProviderContext.getResources());
      }
    }
    Drawable localDrawable = getActivityIcon(paramComponentName);
    if (localDrawable == null);
    for (Object localObject1 = null; ; localObject1 = localDrawable.getConstantState())
    {
      this.mOutsideDrawablesCache.put(str, localObject1);
      localObject2 = localDrawable;
      break;
    }
  }

  public static String getColumnString(Cursor paramCursor, String paramString)
  {
    return getStringOrNull(paramCursor, paramCursor.getColumnIndex(paramString));
  }

  private Drawable getDefaultIcon1(Cursor paramCursor)
  {
    Drawable localDrawable = getActivityIconWithCache(this.mSearchable.getSearchActivity());
    if (localDrawable != null);
    while (true)
    {
      return localDrawable;
      localDrawable = this.mContext.getPackageManager().getDefaultActivityIcon();
    }
  }

  // ERROR //
  private Drawable getDrawable(Uri paramUri)
  {
    // Byte code:
    //   0: ldc_w 278
    //   3: aload_1
    //   4: invokevirtual 283	android/net/Uri:getScheme	()Ljava/lang/String;
    //   7: invokevirtual 288	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   10: istore 5
    //   12: iload 5
    //   14: ifeq +93 -> 107
    //   17: aload_0
    //   18: aload_1
    //   19: invokevirtual 291	android/support/v7/widget/SuggestionsAdapter:getDrawableFromResourceUri	(Landroid/net/Uri;)Landroid/graphics/drawable/Drawable;
    //   22: astore 14
    //   24: aload 14
    //   26: astore 4
    //   28: aload 4
    //   30: areturn
    //   31: astore 13
    //   33: new 272	java/io/FileNotFoundException
    //   36: dup
    //   37: new 208	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   44: ldc_w 293
    //   47: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_1
    //   51: invokevirtual 296	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokespecial 299	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   60: athrow
    //   61: astore_2
    //   62: ldc 16
    //   64: new 208	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   71: ldc_w 301
    //   74: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: aload_1
    //   78: invokevirtual 296	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   81: ldc_w 303
    //   84: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload_2
    //   88: invokevirtual 306	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   91: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: invokestatic 193	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   100: pop
    //   101: aconst_null
    //   102: astore 4
    //   104: goto -76 -> 28
    //   107: aload_0
    //   108: getfield 91	android/support/v7/widget/SuggestionsAdapter:mProviderContext	Landroid/content/Context;
    //   111: invokevirtual 310	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   114: aload_1
    //   115: invokevirtual 316	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   118: astore 6
    //   120: aload 6
    //   122: ifnonnull +31 -> 153
    //   125: new 272	java/io/FileNotFoundException
    //   128: dup
    //   129: new 208	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   136: ldc_w 318
    //   139: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_1
    //   143: invokevirtual 296	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokespecial 299	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   152: athrow
    //   153: aload 6
    //   155: aconst_null
    //   156: invokestatic 322	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   159: astore 10
    //   161: aload 10
    //   163: astore 4
    //   165: aload 6
    //   167: invokevirtual 327	java/io/InputStream:close	()V
    //   170: goto -142 -> 28
    //   173: astore 11
    //   175: ldc 16
    //   177: new 208	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   184: ldc_w 329
    //   187: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload_1
    //   191: invokevirtual 296	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: aload 11
    //   199: invokestatic 333	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   202: pop
    //   203: goto -175 -> 28
    //   206: astore 7
    //   208: aload 6
    //   210: invokevirtual 327	java/io/InputStream:close	()V
    //   213: aload 7
    //   215: athrow
    //   216: astore 8
    //   218: ldc 16
    //   220: new 208	java/lang/StringBuilder
    //   223: dup
    //   224: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   227: ldc_w 329
    //   230: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload_1
    //   234: invokevirtual 296	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   237: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   240: aload 8
    //   242: invokestatic 333	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   245: pop
    //   246: goto -33 -> 213
    //
    // Exception table:
    //   from	to	target	type
    //   17	24	31	android/content/res/Resources$NotFoundException
    //   0	12	61	java/io/FileNotFoundException
    //   17	24	61	java/io/FileNotFoundException
    //   33	61	61	java/io/FileNotFoundException
    //   107	153	61	java/io/FileNotFoundException
    //   165	170	61	java/io/FileNotFoundException
    //   175	203	61	java/io/FileNotFoundException
    //   208	213	61	java/io/FileNotFoundException
    //   213	246	61	java/io/FileNotFoundException
    //   165	170	173	java/io/IOException
    //   153	161	206	finally
    //   208	213	216	java/io/IOException
  }

  private Drawable getDrawableFromResourceValue(String paramString)
  {
    Drawable localDrawable;
    if ((paramString == null) || (paramString.length() == 0) || ("0".equals(paramString)))
      localDrawable = null;
    while (true)
    {
      return localDrawable;
      try
      {
        int i = Integer.parseInt(paramString);
        String str = "android.resource://" + this.mProviderContext.getPackageName() + "/" + i;
        localDrawable = checkIconCache(str);
        if (localDrawable == null)
        {
          localDrawable = this.mProviderContext.getResources().getDrawable(i);
          storeInIconCache(str, localDrawable);
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localDrawable = checkIconCache(paramString);
        if (localDrawable == null)
        {
          localDrawable = getDrawable(Uri.parse(paramString));
          storeInIconCache(paramString, localDrawable);
        }
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        Log.w("SuggestionsAdapter", "Icon resource not found: " + paramString);
        localDrawable = null;
      }
    }
  }

  private Drawable getIcon1(Cursor paramCursor)
  {
    Drawable localDrawable;
    if (this.mIconName1Col == -1)
      localDrawable = null;
    while (true)
    {
      return localDrawable;
      localDrawable = getDrawableFromResourceValue(paramCursor.getString(this.mIconName1Col));
      if (localDrawable == null)
        localDrawable = getDefaultIcon1(paramCursor);
    }
  }

  private Drawable getIcon2(Cursor paramCursor)
  {
    if (this.mIconName2Col == -1);
    for (Drawable localDrawable = null; ; localDrawable = getDrawableFromResourceValue(paramCursor.getString(this.mIconName2Col)))
      return localDrawable;
  }

  private static String getStringOrNull(Cursor paramCursor, int paramInt)
  {
    Object localObject = null;
    if (paramInt == -1);
    while (true)
    {
      return localObject;
      try
      {
        String str = paramCursor.getString(paramInt);
        localObject = str;
      }
      catch (Exception localException)
      {
        Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", localException);
        localObject = null;
      }
    }
  }

  private void setViewDrawable(ImageView paramImageView, Drawable paramDrawable, int paramInt)
  {
    paramImageView.setImageDrawable(paramDrawable);
    if (paramDrawable == null)
      paramImageView.setVisibility(paramInt);
    while (true)
    {
      return;
      paramImageView.setVisibility(0);
      paramDrawable.setVisible(false, false);
      paramDrawable.setVisible(true, false);
    }
  }

  private void setViewText(TextView paramTextView, CharSequence paramCharSequence)
  {
    paramTextView.setText(paramCharSequence);
    if (TextUtils.isEmpty(paramCharSequence))
      paramTextView.setVisibility(8);
    while (true)
    {
      return;
      paramTextView.setVisibility(0);
    }
  }

  private void storeInIconCache(String paramString, Drawable paramDrawable)
  {
    if (paramDrawable != null)
      this.mOutsideDrawablesCache.put(paramString, paramDrawable.getConstantState());
  }

  private void updateSpinnerState(Cursor paramCursor)
  {
    if (paramCursor != null);
    for (Bundle localBundle = paramCursor.getExtras(); ; localBundle = null)
    {
      if ((localBundle != null) && (localBundle.getBoolean("in_progress")));
      return;
    }
  }

  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    ChildViewCache localChildViewCache = (ChildViewCache)paramView.getTag();
    int i = this.mFlagsCol;
    int j = 0;
    if (i != -1)
      j = paramCursor.getInt(this.mFlagsCol);
    if (localChildViewCache.mText1 != null)
    {
      String str2 = getStringOrNull(paramCursor, this.mText1Col);
      setViewText(localChildViewCache.mText1, str2);
    }
    Object localObject;
    if (localChildViewCache.mText2 != null)
    {
      String str1 = getStringOrNull(paramCursor, this.mText2UrlCol);
      if (str1 != null)
      {
        localObject = formatUrl(str1);
        if (!TextUtils.isEmpty((CharSequence)localObject))
          break label259;
        if (localChildViewCache.mText1 != null)
        {
          localChildViewCache.mText1.setSingleLine(false);
          localChildViewCache.mText1.setMaxLines(2);
        }
        label130: setViewText(localChildViewCache.mText2, (CharSequence)localObject);
      }
    }
    else
    {
      if (localChildViewCache.mIcon1 != null)
        setViewDrawable(localChildViewCache.mIcon1, getIcon1(paramCursor), 4);
      if (localChildViewCache.mIcon2 != null)
        setViewDrawable(localChildViewCache.mIcon2, getIcon2(paramCursor), 8);
      if ((this.mQueryRefinement != 2) && ((this.mQueryRefinement != 1) || ((j & 0x1) == 0)))
        break label288;
      localChildViewCache.mIconRefine.setVisibility(0);
      localChildViewCache.mIconRefine.setTag(localChildViewCache.mText1.getText());
      localChildViewCache.mIconRefine.setOnClickListener(this);
    }
    while (true)
    {
      return;
      localObject = getStringOrNull(paramCursor, this.mText2Col);
      break;
      label259: if (localChildViewCache.mText1 == null)
        break label130;
      localChildViewCache.mText1.setSingleLine(true);
      localChildViewCache.mText1.setMaxLines(1);
      break label130;
      label288: localChildViewCache.mIconRefine.setVisibility(8);
    }
  }

  public void changeCursor(Cursor paramCursor)
  {
    if (this.mClosed)
    {
      Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
      if (paramCursor != null)
        paramCursor.close();
    }
    while (true)
    {
      return;
      try
      {
        super.changeCursor(paramCursor);
        if (paramCursor != null)
        {
          this.mText1Col = paramCursor.getColumnIndex("suggest_text_1");
          this.mText2Col = paramCursor.getColumnIndex("suggest_text_2");
          this.mText2UrlCol = paramCursor.getColumnIndex("suggest_text_2_url");
          this.mIconName1Col = paramCursor.getColumnIndex("suggest_icon_1");
          this.mIconName2Col = paramCursor.getColumnIndex("suggest_icon_2");
          this.mFlagsCol = paramCursor.getColumnIndex("suggest_flags");
        }
      }
      catch (Exception localException)
      {
        Log.e("SuggestionsAdapter", "error changing cursor and caching columns", localException);
      }
    }
  }

  public void close()
  {
    changeCursor(null);
    this.mClosed = true;
  }

  public CharSequence convertToString(Cursor paramCursor)
  {
    Object localObject;
    if (paramCursor == null)
      localObject = null;
    while (true)
    {
      return localObject;
      localObject = getColumnString(paramCursor, "suggest_intent_query");
      if (localObject == null)
        if (this.mSearchable.shouldRewriteQueryFromData())
        {
          String str2 = getColumnString(paramCursor, "suggest_intent_data");
          if (str2 != null)
            localObject = str2;
        }
        else if (this.mSearchable.shouldRewriteQueryFromText())
        {
          String str1 = getColumnString(paramCursor, "suggest_text_1");
          if (str1 != null)
            localObject = str1;
        }
        else
        {
          localObject = null;
        }
    }
  }

  Drawable getDrawableFromResourceUri(Uri paramUri)
    throws FileNotFoundException
  {
    String str = paramUri.getAuthority();
    if (TextUtils.isEmpty(str))
      throw new FileNotFoundException("No authority: " + paramUri);
    Resources localResources;
    List localList;
    try
    {
      localResources = this.mContext.getPackageManager().getResourcesForApplication(str);
      localList = paramUri.getPathSegments();
      if (localList == null)
        throw new FileNotFoundException("No path: " + paramUri);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new FileNotFoundException("No package found for authority: " + paramUri);
    }
    int i = localList.size();
    if (i == 1);
    int j;
    while (true)
    {
      try
      {
        int k = Integer.parseInt((String)localList.get(0));
        j = k;
        if (j != 0)
          break;
        throw new FileNotFoundException("No resource found for: " + paramUri);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new FileNotFoundException("Single path segment is not a resource ID: " + paramUri);
      }
      if (i == 2)
        j = localResources.getIdentifier((String)localList.get(1), (String)localList.get(0), str);
      else
        throw new FileNotFoundException("More than two path segments: " + paramUri);
    }
    return localResources.getDrawable(j);
  }

  public int getQueryRefinement()
  {
    return this.mQueryRefinement;
  }

  Cursor getSearchManagerSuggestions(SearchableInfo paramSearchableInfo, String paramString, int paramInt)
  {
    Cursor localCursor = null;
    if (paramSearchableInfo == null);
    String str1;
    do
    {
      return localCursor;
      str1 = paramSearchableInfo.getSuggestAuthority();
      localCursor = null;
    }
    while (str1 == null);
    Uri.Builder localBuilder = new Uri.Builder().scheme("content").authority(str1).query("").fragment("");
    String str2 = paramSearchableInfo.getSuggestPath();
    if (str2 != null)
      localBuilder.appendEncodedPath(str2);
    localBuilder.appendPath("search_suggest_query");
    String str3 = paramSearchableInfo.getSuggestSelection();
    if (str3 != null);
    for (String[] arrayOfString = { paramString }; ; arrayOfString = null)
    {
      if (paramInt > 0)
        localBuilder.appendQueryParameter("limit", String.valueOf(paramInt));
      Uri localUri = localBuilder.build();
      localCursor = this.mContext.getContentResolver().query(localUri, null, str3, arrayOfString, null);
      break;
      localBuilder.appendPath(paramString);
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    try
    {
      View localView2 = super.getView(paramInt, paramView, paramViewGroup);
      localView1 = localView2;
      return localView1;
    }
    catch (RuntimeException localRuntimeException)
    {
      while (true)
      {
        Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", localRuntimeException);
        View localView1 = newView(this.mContext, this.mCursor, paramViewGroup);
        if (localView1 != null)
          ((ChildViewCache)localView1.getTag()).mText1.setText(localRuntimeException.toString());
      }
    }
  }

  public boolean hasStableIds()
  {
    return false;
  }

  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    View localView = super.newView(paramContext, paramCursor, paramViewGroup);
    localView.setTag(new ChildViewCache(localView));
    return localView;
  }

  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    updateSpinnerState(getCursor());
  }

  public void notifyDataSetInvalidated()
  {
    super.notifyDataSetInvalidated();
    updateSpinnerState(getCursor());
  }

  public void onClick(View paramView)
  {
    Object localObject = paramView.getTag();
    if ((localObject instanceof CharSequence))
      this.mSearchView.onQueryRefine((CharSequence)localObject);
  }

  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence)
  {
    String str;
    Object localObject;
    if (paramCharSequence == null)
    {
      str = "";
      int i = this.mSearchView.getVisibility();
      localObject = null;
      if (i == 0)
      {
        int j = this.mSearchView.getWindowVisibility();
        localObject = null;
        if (j == 0)
          break label51;
      }
    }
    while (true)
    {
      return localObject;
      str = paramCharSequence.toString();
      break;
      try
      {
        label51: Cursor localCursor = getSearchManagerSuggestions(this.mSearchable, str, 50);
        localObject = null;
        if (localCursor != null)
        {
          localCursor.getCount();
          localObject = localCursor;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", localRuntimeException);
        localObject = null;
      }
    }
  }

  public void setQueryRefinement(int paramInt)
  {
    this.mQueryRefinement = paramInt;
  }

  private static final class ChildViewCache
  {
    public final ImageView mIcon1;
    public final ImageView mIcon2;
    public final ImageView mIconRefine;
    public final TextView mText1;
    public final TextView mText2;

    public ChildViewCache(View paramView)
    {
      this.mText1 = ((TextView)paramView.findViewById(16908308));
      this.mText2 = ((TextView)paramView.findViewById(16908309));
      this.mIcon1 = ((ImageView)paramView.findViewById(16908295));
      this.mIcon2 = ((ImageView)paramView.findViewById(16908296));
      this.mIconRefine = ((ImageView)paramView.findViewById(R.id.edit_query));
    }
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v7.widget.SuggestionsAdapter
 * JD-Core Version:    0.6.2
 */