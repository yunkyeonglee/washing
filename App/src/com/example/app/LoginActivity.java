package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class LoginActivity extends Activity {
	boolean loadingFinished = true;
	boolean redirect = false;
	WebView myWebView;
	ProgressBar progressBar1;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.reservation);

	    progressBar1 = (ProgressBar) findViewById( R.id.progressBar1);
	    
	    // TODO Auto-generated method stub
	    myWebView = (WebView) findViewById(R.id.webview);
	    myWebView.loadUrl("http://210.115.48.111:8080/test3/login/login.jsp");
	    
	    
	    myWebView.setWebViewClient(new WebViewClient() {
	    	
	    	public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
				if(!LoginActivity.this.loadingFinished ) {
					redirect = true;
				}
	    	
				loadingFinished = false;
				LoginActivity.this.myWebView.loadUrl(urlNewString);
			return true;
				
	    	}
	    	
	    	public void onPageStarted (WebView view, String url) {
	    		loadingFinished = false;
	    	}
	    	
	    	public void onPageFinished(WebView view, String url) {
	    		progressBar1.setVisibility(view.INVISIBLE);
	    		if(!redirect) {
	    			loadingFinished = true;
	    		}
	    		
	    		if (loadingFinished && !redirect){
	    			progressBar1.setVisibility(view.INVISIBLE);
	    		}else{
	    			redirect = false;
	    		}
	    	}
	    	
	    	
	    	
	    });
	}

}
