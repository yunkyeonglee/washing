package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.intro);
	
	    // TODO Auto-generated method stub
	    Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
			public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                // �ڷΰ��� ������� �ȳ������� �����ֱ� >> finish!!
                finish();
            }
        }, 2000);   
	}

}
