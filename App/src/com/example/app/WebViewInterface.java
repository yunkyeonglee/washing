package com.example.app;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewInterface extends Activity {

	private WebView mAppView;
    private Activity mContext;
    
    private Handler mHandler;
    private Toast textView;
    
    public void setMessage(final String arg) {
    	mHandler.post(new Runnable() {
	    	@Override
			public void run() {
	    		Log.d("Android", "setMessage("+arg+")");
	    		textView.setText("���� �޽��� : \n" + arg);
	    	}
		});
	}
}
 
//    /**
//     * ������.
//     * @param activity : context
//     * @param view : ����� ����
//     */
//    
//    public WebViewInterface () {
//    	
//    }
//    
//    /**
//     * This is not called on the UI thread. Post a runnable to invoke
//     * loadUrl on the UI thread.
//     */
//    public void callAndroid(final String str) {
//        mHandler.post(new Runnable() {
//            public void run() {
//            	textView.setText(str);
//            }
//        });
//
//    }
//    
//    public WebViewInterface(Activity activity, WebView view) {
//        mAppView = view;
//        mContext = activity;
//    }
//    /**
//     * �ȵ���̵� �佺Ʈ�� ����Ѵ�. Time Long.
//     * @param message : �޽���
//     */
//    @JavascriptInterface
//    public void toastLong (String message) {
//        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
//    }
//    /**
//     * �ȵ���̵� �佺Ʈ�� ����Ѵ�. Time Short.
//     * @param message : �޽���
//     */
//    @JavascriptInterface
//    public void toastShort (String message) { // Show toast for a short time
//        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
//    }
//    
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	
//	    // TODO Auto-generated method stub
//	}


