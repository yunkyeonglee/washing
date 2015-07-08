package com.example.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ReservationActivity extends Activity {
	boolean loadingFinished = true;
	boolean redirect = false;

	private WebView myWebView = null;
	private Toast textView;
	private WebViewInterface mWebViewInterface;
	ProgressBar progressBar1;

	private final Handler handler = new Handler();

	/** Called when the activity is first created. */
	@SuppressLint("JavascriptInterface")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation);

		// ȭ�� ��ȯ�� �ʾ����� �ε��ð� ��ŭ ���α׷��� â
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);

		// TODO Auto-generated method stub
		myWebView = (WebView) findViewById(R.id.webview);
		// myWebView.loadUrl("http://210.115.48.111:8080/test3/beforelogin.jsp");

		myWebView.setWebViewClient(new WebViewClient() {

			public boolean shouldOverrideUrlLoading(WebView view,
					String urlNewString) {
				if (!ReservationActivity.this.loadingFinished) {
					redirect = true;
				}

				loadingFinished = false;
				ReservationActivity.this.myWebView.loadUrl(urlNewString);
				return true;

			}

			public void onPageStarted(WebView view, String url) {
				loadingFinished = false;
			}

			public void onPageFinished(WebView view, String url) {
				progressBar1.setVisibility(view.INVISIBLE);
				if (!redirect) {
					loadingFinished = true;
				}

				if (loadingFinished && !redirect) {
					progressBar1.setVisibility(view.INVISIBLE);
				} else {
					redirect = false;
				}
			}

		});

		
		// �������� �ȵ���̵�� ������ ��ȯ
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new AndroidBridge(), "HybridApp");
		myWebView.loadUrl("http://210.115.48.111:8080/test3/javapage.html");

		myWebView.setWebViewClient(new HelloWebViewClient()); // WebViewClient
																// ����

		// �ۿ��� ������ ������ ������ ��ư
		// mButton.setOnClickListener( new OnClickListener(){
		// public void onClick(View view) {
		// myWebView.loadUrl("javascript:setMessage('"+mEditText.getText()+"')");
		// }
		// });

		// // ���信�� �ڹٽ�ũ��Ʈ���డ��
		// mWebView.getSettings().setJavaScriptEnabled(true);
		// // Bridge �ν��Ͻ� ���
		// mWebView.addJavascriptInterface(new AndroidBridge(), "HybridApp");
		//
		// mWebView.loadUrl("file:///android_asset/test.html"); // ���� HTML ���� �ε�
		//
		//
		//

		// getWindow().requestFeature(Window.FEATURE_PROGRESS);
		// setContentView(R.layout.reservation);
		//
		// myWebView = (WebView) findViewById(R.id.webview); // ���� ��ü
		// textView = (TextView) findViewById(R.id.textView);
		//
		// // mWebViewInterface = new WebViewInterface(ReservationActivity.this,
		// // myWebView); //JavascriptInterface ��üȭ
		// // myWebView.addJavascriptInterface(mWebViewInterface, "Android");
		// //���信
		// // JavascriptInterface�� ����
		//
		// WebSettings webSet = myWebView.getSettings();
		// webSet.setJavaScriptEnabled(true);
		// webSet.setBuiltInZoomControls(true);
		// myWebView.addJavascriptInterface(new WebViewInterface(), "myJs");
		// myWebView.loadUrl("http://210.115.48.111:8080/test3/beforelogin.jsp");

	}

	private class AndroidBridge {
		public void setMessage(final String arg) { // must be final
			handler.post(new Runnable() {
				@Override
				public void run() {
					Log.d("HybridApp", "setMessage(" + arg + ")");
					textView.setText("���� �޽��� : \n" + arg);
				}
			});
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
			myWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
