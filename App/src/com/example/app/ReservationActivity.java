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

		// 화면 전환시 늦어지는 로딩시간 만큼 프로그레스 창
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

		
		// 웹서버랑 안드로이드랑 데이터 교환
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new AndroidBridge(), "HybridApp");
		myWebView.loadUrl("http://210.115.48.111:8080/test3/javapage.html");

		myWebView.setWebViewClient(new HelloWebViewClient()); // WebViewClient
																// 지정

		// 앱에서 웹으로 데이터 보내는 버튼
		// mButton.setOnClickListener( new OnClickListener(){
		// public void onClick(View view) {
		// myWebView.loadUrl("javascript:setMessage('"+mEditText.getText()+"')");
		// }
		// });

		// // 웹뷰에서 자바스크립트실행가능
		// mWebView.getSettings().setJavaScriptEnabled(true);
		// // Bridge 인스턴스 등록
		// mWebView.addJavascriptInterface(new AndroidBridge(), "HybridApp");
		//
		// mWebView.loadUrl("file:///android_asset/test.html"); // 로컬 HTML 파일 로드
		//
		//
		//

		// getWindow().requestFeature(Window.FEATURE_PROGRESS);
		// setContentView(R.layout.reservation);
		//
		// myWebView = (WebView) findViewById(R.id.webview); // 웹뷰 객체
		// textView = (TextView) findViewById(R.id.textView);
		//
		// // mWebViewInterface = new WebViewInterface(ReservationActivity.this,
		// // myWebView); //JavascriptInterface 객체화
		// // myWebView.addJavascriptInterface(mWebViewInterface, "Android");
		// //웹뷰에
		// // JavascriptInterface를 연결
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
					textView.setText("받은 메시지 : \n" + arg);
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
