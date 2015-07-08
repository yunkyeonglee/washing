package com.example.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends Activity {
	WebView mWebView;
	TextView mTextView;
	EditText mEditText;
	Button mButton;
	String mStringHead;
	private final Handler handler = new Handler();

	/** Called when the activity is first created. */
	@SuppressLint("JavascriptInterface")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		mWebView = (WebView) findViewById(R.id.webview);
		mTextView = (TextView) findViewById(R.id.textview);
		mButton = (Button) findViewById(R.id.button);
		mEditText = (EditText) findViewById(R.id.EditText01);
		mStringHead = this.getString(R.string.string_head);

		// 웹뷰에서 자바스크립트실행가능
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("file:///android_asset/test.html"); // 로컬 HTML 파일 로드

		mWebView.setWebViewClient(new HelloWebViewClient()); // WebViewClient 지정
		// Bridge 인스턴스 등록
		mWebView.addJavascriptInterface(new AndroidBridge(), "HybridApp");

		
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				mWebView.loadUrl("javascript:setMessage('"
						+ mEditText.getText() + "')");
			}
		});

	}

	
	public class AndroidBridge {
		
		@JavascriptInterface
		public void setMessage(final String arg) { // must be final
			Log.d("HybridApp", "setMessage(" + arg + ")");
			
			handler.post(new Runnable() {
				@Override
				public void run() {
					
					mTextView.setText(mStringHead + arg);
				}
			});
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
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