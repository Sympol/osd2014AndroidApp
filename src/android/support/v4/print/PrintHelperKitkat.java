package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import java.io.FileNotFoundException;

class PrintHelperKitkat
{
  public static final int COLOR_MODE_COLOR = 2;
  public static final int COLOR_MODE_MONOCHROME = 1;
  private static final String LOG_TAG = "PrintHelperKitkat";
  private static final int MAX_PRINT_SIZE = 3500;
  public static final int ORIENTATION_LANDSCAPE = 1;
  public static final int ORIENTATION_PORTRAIT = 2;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  int mColorMode = 2;
  final Context mContext;
  BitmapFactory.Options mDecodeOptions = null;
  private final Object mLock = new Object();
  int mOrientation = 1;
  int mScaleMode = 2;

  PrintHelperKitkat(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3)
  {
    Matrix localMatrix = new Matrix();
    float f1 = paramRectF.width() / paramInt1;
    if (paramInt3 == 2);
    for (float f2 = Math.max(f1, paramRectF.height() / paramInt2); ; f2 = Math.min(f1, paramRectF.height() / paramInt2))
    {
      localMatrix.postScale(f2, f2);
      localMatrix.postTranslate((paramRectF.width() - f2 * paramInt1) / 2.0F, (paramRectF.height() - f2 * paramInt2) / 2.0F);
      return localMatrix;
    }
  }

  // ERROR //
  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
    throws FileNotFoundException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: getfield 45	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
    //   8: ifnonnull +13 -> 21
    //   11: new 95	java/lang/IllegalArgumentException
    //   14: dup
    //   15: ldc 97
    //   17: invokespecial 100	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   20: athrow
    //   21: aconst_null
    //   22: astore_3
    //   23: aload_0
    //   24: getfield 45	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
    //   27: invokevirtual 106	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   30: aload_1
    //   31: invokevirtual 112	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   34: astore_3
    //   35: aload_3
    //   36: aconst_null
    //   37: aload_2
    //   38: invokestatic 118	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   41: astore 7
    //   43: aload_3
    //   44: ifnull +7 -> 51
    //   47: aload_3
    //   48: invokevirtual 123	java/io/InputStream:close	()V
    //   51: aload 7
    //   53: areturn
    //   54: astore 8
    //   56: ldc 13
    //   58: ldc 125
    //   60: aload 8
    //   62: invokestatic 131	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   65: pop
    //   66: goto -15 -> 51
    //   69: astore 4
    //   71: aload_3
    //   72: ifnull +7 -> 79
    //   75: aload_3
    //   76: invokevirtual 123	java/io/InputStream:close	()V
    //   79: aload 4
    //   81: athrow
    //   82: astore 5
    //   84: ldc 13
    //   86: ldc 125
    //   88: aload 5
    //   90: invokestatic 131	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   93: pop
    //   94: goto -15 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   47	51	54	java/io/IOException
    //   23	43	69	finally
    //   75	79	82	java/io/IOException
  }

  // ERROR //
  private Bitmap loadConstrainedBitmap(Uri paramUri, int paramInt)
    throws FileNotFoundException
  {
    // Byte code:
    //   0: iload_2
    //   1: ifle +14 -> 15
    //   4: aload_1
    //   5: ifnull +10 -> 15
    //   8: aload_0
    //   9: getfield 45	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
    //   12: ifnonnull +13 -> 25
    //   15: new 95	java/lang/IllegalArgumentException
    //   18: dup
    //   19: ldc 133
    //   21: invokespecial 100	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   24: athrow
    //   25: new 135	android/graphics/BitmapFactory$Options
    //   28: dup
    //   29: invokespecial 136	android/graphics/BitmapFactory$Options:<init>	()V
    //   32: astore_3
    //   33: aload_3
    //   34: iconst_1
    //   35: putfield 140	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   38: aload_0
    //   39: aload_1
    //   40: aload_3
    //   41: invokespecial 142	android/support/v4/print/PrintHelperKitkat:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   44: pop
    //   45: aload_3
    //   46: getfield 145	android/graphics/BitmapFactory$Options:outWidth	I
    //   49: istore 5
    //   51: aload_3
    //   52: getfield 148	android/graphics/BitmapFactory$Options:outHeight	I
    //   55: istore 6
    //   57: aconst_null
    //   58: astore 7
    //   60: iload 5
    //   62: ifle +11 -> 73
    //   65: aconst_null
    //   66: astore 7
    //   68: iload 6
    //   70: ifgt +6 -> 76
    //   73: aload 7
    //   75: areturn
    //   76: iload 5
    //   78: iload 6
    //   80: invokestatic 151	java/lang/Math:max	(II)I
    //   83: istore 8
    //   85: iconst_1
    //   86: istore 9
    //   88: iload 8
    //   90: iload_2
    //   91: if_icmple +18 -> 109
    //   94: iload 8
    //   96: iconst_1
    //   97: iushr
    //   98: istore 8
    //   100: iload 9
    //   102: iconst_1
    //   103: ishl
    //   104: istore 9
    //   106: goto -18 -> 88
    //   109: aconst_null
    //   110: astore 7
    //   112: iload 9
    //   114: ifle -41 -> 73
    //   117: iload 5
    //   119: iload 6
    //   121: invokestatic 153	java/lang/Math:min	(II)I
    //   124: iload 9
    //   126: idiv
    //   127: istore 10
    //   129: aconst_null
    //   130: astore 7
    //   132: iload 10
    //   134: ifle -61 -> 73
    //   137: aload_0
    //   138: getfield 37	android/support/v4/print/PrintHelperKitkat:mLock	Ljava/lang/Object;
    //   141: astore 11
    //   143: aload 11
    //   145: monitorenter
    //   146: aload_0
    //   147: new 135	android/graphics/BitmapFactory$Options
    //   150: dup
    //   151: invokespecial 136	android/graphics/BitmapFactory$Options:<init>	()V
    //   154: putfield 35	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   157: aload_0
    //   158: getfield 35	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   161: iconst_1
    //   162: putfield 156	android/graphics/BitmapFactory$Options:inMutable	Z
    //   165: aload_0
    //   166: getfield 35	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   169: iload 9
    //   171: putfield 159	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   174: aload_0
    //   175: getfield 35	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   178: astore 13
    //   180: aload 11
    //   182: monitorexit
    //   183: aload_0
    //   184: aload_1
    //   185: aload 13
    //   187: invokespecial 142	android/support/v4/print/PrintHelperKitkat:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   190: astore 17
    //   192: aload 17
    //   194: astore 7
    //   196: aload_0
    //   197: getfield 37	android/support/v4/print/PrintHelperKitkat:mLock	Ljava/lang/Object;
    //   200: astore 18
    //   202: aload 18
    //   204: monitorenter
    //   205: aload_0
    //   206: aconst_null
    //   207: putfield 35	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   210: aload 18
    //   212: monitorexit
    //   213: goto -140 -> 73
    //   216: astore 19
    //   218: aload 18
    //   220: monitorexit
    //   221: aload 19
    //   223: athrow
    //   224: astore 12
    //   226: aload 11
    //   228: monitorexit
    //   229: aload 12
    //   231: athrow
    //   232: astore 14
    //   234: aload_0
    //   235: getfield 37	android/support/v4/print/PrintHelperKitkat:mLock	Ljava/lang/Object;
    //   238: astore 15
    //   240: aload 15
    //   242: monitorenter
    //   243: aload_0
    //   244: aconst_null
    //   245: putfield 35	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   248: aload 15
    //   250: monitorexit
    //   251: aload 14
    //   253: athrow
    //   254: astore 16
    //   256: aload 15
    //   258: monitorexit
    //   259: aload 16
    //   261: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   205	221	216	finally
    //   146	183	224	finally
    //   226	229	224	finally
    //   183	192	232	finally
    //   243	251	254	finally
    //   256	259	254	finally
  }

  public int getColorMode()
  {
    return this.mColorMode;
  }

  public int getOrientation()
  {
    return this.mOrientation;
  }

  public int getScaleMode()
  {
    return this.mScaleMode;
  }

  public void printBitmap(final String paramString, final Bitmap paramBitmap)
  {
    if (paramBitmap == null);
    while (true)
    {
      return;
      final int i = this.mScaleMode;
      PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
      PrintAttributes.MediaSize localMediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
      if (paramBitmap.getWidth() > paramBitmap.getHeight())
        localMediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
      PrintAttributes localPrintAttributes = new PrintAttributes.Builder().setMediaSize(localMediaSize).setColorMode(this.mColorMode).build();
      localPrintManager.print(paramString, new PrintDocumentAdapter()
      {
        private PrintAttributes mAttributes;

        public void onLayout(PrintAttributes paramAnonymousPrintAttributes1, PrintAttributes paramAnonymousPrintAttributes2, CancellationSignal paramAnonymousCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
        {
          int i = 1;
          this.mAttributes = paramAnonymousPrintAttributes2;
          PrintDocumentInfo localPrintDocumentInfo = new PrintDocumentInfo.Builder(paramString).setContentType(i).setPageCount(i).build();
          if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1));
          while (true)
          {
            paramAnonymousLayoutResultCallback.onLayoutFinished(localPrintDocumentInfo, i);
            return;
            int j = 0;
          }
        }

        // ERROR //
        public void onWrite(android.print.PageRange[] paramAnonymousArrayOfPageRange, android.os.ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
        {
          // Byte code:
          //   0: new 70	android/print/pdf/PrintedPdfDocument
          //   3: dup
          //   4: aload_0
          //   5: getfield 23	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
          //   8: getfield 74	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
          //   11: aload_0
          //   12: getfield 36	android/support/v4/print/PrintHelperKitkat$1:mAttributes	Landroid/print/PrintAttributes;
          //   15: invokespecial 77	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
          //   18: astore 5
          //   20: aload 5
          //   22: iconst_1
          //   23: invokevirtual 81	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
          //   26: astore 8
          //   28: new 83	android/graphics/RectF
          //   31: dup
          //   32: aload 8
          //   34: invokevirtual 89	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
          //   37: invokevirtual 95	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
          //   40: invokespecial 98	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
          //   43: astore 9
          //   45: aload_0
          //   46: getfield 23	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
          //   49: aload_0
          //   50: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
          //   53: invokevirtual 104	android/graphics/Bitmap:getWidth	()I
          //   56: aload_0
          //   57: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
          //   60: invokevirtual 107	android/graphics/Bitmap:getHeight	()I
          //   63: aload 9
          //   65: aload_0
          //   66: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$fittingMode	I
          //   69: invokestatic 111	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
          //   72: astore 10
          //   74: aload 8
          //   76: invokevirtual 115	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
          //   79: aload_0
          //   80: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
          //   83: aload 10
          //   85: aconst_null
          //   86: invokevirtual 121	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
          //   89: aload 5
          //   91: aload 8
          //   93: invokevirtual 125	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
          //   96: aload 5
          //   98: new 127	java/io/FileOutputStream
          //   101: dup
          //   102: aload_2
          //   103: invokevirtual 133	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
          //   106: invokespecial 136	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
          //   109: invokevirtual 140	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
          //   112: iconst_1
          //   113: anewarray 142	android/print/PageRange
          //   116: astore 14
          //   118: aload 14
          //   120: iconst_0
          //   121: getstatic 146	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
          //   124: aastore
          //   125: aload 4
          //   127: aload 14
          //   129: invokevirtual 152	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
          //   132: aload 5
          //   134: ifnull +8 -> 142
          //   137: aload 5
          //   139: invokevirtual 155	android/print/pdf/PrintedPdfDocument:close	()V
          //   142: aload_2
          //   143: ifnull +7 -> 150
          //   146: aload_2
          //   147: invokevirtual 156	android/os/ParcelFileDescriptor:close	()V
          //   150: return
          //   151: astore 11
          //   153: ldc 158
          //   155: ldc 160
          //   157: aload 11
          //   159: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   162: pop
          //   163: aload 4
          //   165: aconst_null
          //   166: invokevirtual 170	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
          //   169: goto -37 -> 132
          //   172: astore 6
          //   174: aload 5
          //   176: ifnull +8 -> 184
          //   179: aload 5
          //   181: invokevirtual 155	android/print/pdf/PrintedPdfDocument:close	()V
          //   184: aload_2
          //   185: ifnull +7 -> 192
          //   188: aload_2
          //   189: invokevirtual 156	android/os/ParcelFileDescriptor:close	()V
          //   192: aload 6
          //   194: athrow
          //   195: astore 13
          //   197: goto -47 -> 150
          //   200: astore 7
          //   202: goto -10 -> 192
          //
          // Exception table:
          //   from	to	target	type
          //   96	132	151	java/io/IOException
          //   20	96	172	finally
          //   96	132	172	finally
          //   153	169	172	finally
          //   146	150	195	java/io/IOException
          //   188	192	200	java/io/IOException
        }
      }
      , localPrintAttributes);
    }
  }

  public void printBitmap(final String paramString, final Uri paramUri)
    throws FileNotFoundException
  {
    PrintDocumentAdapter local2 = new PrintDocumentAdapter()
    {
      AsyncTask<Uri, Boolean, Bitmap> loadBitmap;
      private PrintAttributes mAttributes;
      Bitmap mBitmap = null;

      private void cancelLoad()
      {
        synchronized (PrintHelperKitkat.this.mLock)
        {
          if (PrintHelperKitkat.this.mDecodeOptions != null)
          {
            PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
            PrintHelperKitkat.this.mDecodeOptions = null;
          }
          return;
        }
      }

      public void onFinish()
      {
        super.onFinish();
        cancelLoad();
        this.loadBitmap.cancel(true);
      }

      public void onLayout(final PrintAttributes paramAnonymousPrintAttributes1, final PrintAttributes paramAnonymousPrintAttributes2, final CancellationSignal paramAnonymousCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
      {
        int i = 1;
        if (paramAnonymousCancellationSignal.isCanceled())
        {
          paramAnonymousLayoutResultCallback.onLayoutCancelled();
          this.mAttributes = paramAnonymousPrintAttributes2;
        }
        while (true)
        {
          return;
          if (this.mBitmap != null)
          {
            PrintDocumentInfo localPrintDocumentInfo = new PrintDocumentInfo.Builder(paramString).setContentType(i).setPageCount(i).build();
            if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1));
            while (true)
            {
              paramAnonymousLayoutResultCallback.onLayoutFinished(localPrintDocumentInfo, i);
              break;
              int j = 0;
            }
          }
          this.loadBitmap = new AsyncTask()
          {
            protected Bitmap doInBackground(Uri[] paramAnonymous2ArrayOfUri)
            {
              try
              {
                Bitmap localBitmap2 = PrintHelperKitkat.this.loadConstrainedBitmap(PrintHelperKitkat.2.this.val$imageFile, 3500);
                localBitmap1 = localBitmap2;
                return localBitmap1;
              }
              catch (FileNotFoundException localFileNotFoundException)
              {
                while (true)
                  Bitmap localBitmap1 = null;
              }
            }

            protected void onCancelled(Bitmap paramAnonymous2Bitmap)
            {
              paramAnonymousLayoutResultCallback.onLayoutCancelled();
            }

            protected void onPostExecute(Bitmap paramAnonymous2Bitmap)
            {
              int i = 1;
              super.onPostExecute(paramAnonymous2Bitmap);
              PrintHelperKitkat.2.this.mBitmap = paramAnonymous2Bitmap;
              if (paramAnonymous2Bitmap != null)
              {
                PrintDocumentInfo localPrintDocumentInfo = new PrintDocumentInfo.Builder(PrintHelperKitkat.2.this.val$jobName).setContentType(i).setPageCount(i).build();
                if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1))
                  paramAnonymousLayoutResultCallback.onLayoutFinished(localPrintDocumentInfo, i);
              }
              while (true)
              {
                return;
                int j = 0;
                break;
                paramAnonymousLayoutResultCallback.onLayoutFailed(null);
              }
            }

            protected void onPreExecute()
            {
              paramAnonymousCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
              {
                public void onCancel()
                {
                  PrintHelperKitkat.2.this.cancelLoad();
                  PrintHelperKitkat.2.1.this.cancel(false);
                }
              });
            }
          };
          this.loadBitmap.execute(new Uri[0]);
          this.mAttributes = paramAnonymousPrintAttributes2;
        }
      }

      // ERROR //
      public void onWrite(android.print.PageRange[] paramAnonymousArrayOfPageRange, android.os.ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
      {
        // Byte code:
        //   0: new 126	android/print/pdf/PrintedPdfDocument
        //   3: dup
        //   4: aload_0
        //   5: getfield 28	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   8: getfield 130	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
        //   11: aload_0
        //   12: getfield 83	android/support/v4/print/PrintHelperKitkat$2:mAttributes	Landroid/print/PrintAttributes;
        //   15: invokespecial 133	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   18: astore 5
        //   20: aload 5
        //   22: iconst_1
        //   23: invokevirtual 137	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   26: astore 8
        //   28: new 139	android/graphics/RectF
        //   31: dup
        //   32: aload 8
        //   34: invokevirtual 145	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   37: invokevirtual 151	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   40: invokespecial 154	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   43: astore 9
        //   45: aload_0
        //   46: getfield 28	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   49: aload_0
        //   50: getfield 39	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   53: invokevirtual 160	android/graphics/Bitmap:getWidth	()I
        //   56: aload_0
        //   57: getfield 39	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   60: invokevirtual 163	android/graphics/Bitmap:getHeight	()I
        //   63: aload 9
        //   65: aload_0
        //   66: getfield 34	android/support/v4/print/PrintHelperKitkat$2:val$fittingMode	I
        //   69: invokestatic 167	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //   72: astore 10
        //   74: aload 8
        //   76: invokevirtual 171	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   79: aload_0
        //   80: getfield 39	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   83: aload 10
        //   85: aconst_null
        //   86: invokevirtual 177	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   89: aload 5
        //   91: aload 8
        //   93: invokevirtual 181	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   96: aload 5
        //   98: new 183	java/io/FileOutputStream
        //   101: dup
        //   102: aload_2
        //   103: invokevirtual 189	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
        //   106: invokespecial 192	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
        //   109: invokevirtual 196	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
        //   112: iconst_1
        //   113: anewarray 198	android/print/PageRange
        //   116: astore 14
        //   118: aload 14
        //   120: iconst_0
        //   121: getstatic 202	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
        //   124: aastore
        //   125: aload 4
        //   127: aload 14
        //   129: invokevirtual 208	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
        //   132: aload 5
        //   134: ifnull +8 -> 142
        //   137: aload 5
        //   139: invokevirtual 211	android/print/pdf/PrintedPdfDocument:close	()V
        //   142: aload_2
        //   143: ifnull +7 -> 150
        //   146: aload_2
        //   147: invokevirtual 212	android/os/ParcelFileDescriptor:close	()V
        //   150: return
        //   151: astore 11
        //   153: ldc 214
        //   155: ldc 216
        //   157: aload 11
        //   159: invokestatic 222	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   162: pop
        //   163: aload 4
        //   165: aconst_null
        //   166: invokevirtual 226	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
        //   169: goto -37 -> 132
        //   172: astore 6
        //   174: aload 5
        //   176: ifnull +8 -> 184
        //   179: aload 5
        //   181: invokevirtual 211	android/print/pdf/PrintedPdfDocument:close	()V
        //   184: aload_2
        //   185: ifnull +7 -> 192
        //   188: aload_2
        //   189: invokevirtual 212	android/os/ParcelFileDescriptor:close	()V
        //   192: aload 6
        //   194: athrow
        //   195: astore 13
        //   197: goto -47 -> 150
        //   200: astore 7
        //   202: goto -10 -> 192
        //
        // Exception table:
        //   from	to	target	type
        //   96	132	151	java/io/IOException
        //   20	96	172	finally
        //   96	132	172	finally
        //   153	169	172	finally
        //   146	150	195	java/io/IOException
        //   188	192	200	java/io/IOException
      }
    };
    PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder();
    localBuilder.setColorMode(this.mColorMode);
    if (this.mOrientation == 1)
      localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
    while (true)
    {
      localPrintManager.print(paramString, local2, localBuilder.build());
      return;
      if (this.mOrientation == 2)
        localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
    }
  }

  public void setColorMode(int paramInt)
  {
    this.mColorMode = paramInt;
  }

  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }

  public void setScaleMode(int paramInt)
  {
    this.mScaleMode = paramInt;
  }
}

/* Location:           C:\Users\Delice\AppData\Local\Temp\ws_dso-dex2jar.jar
 * Qualified Name:     android.support.v4.print.PrintHelperKitkat
 * JD-Core Version:    0.6.2
 */