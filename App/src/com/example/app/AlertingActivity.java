package com.example.app;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;

/*
 * 알림창을 클릭하면 실행되는 액티비티 
 */

public class AlertingActivity extends Activity {
	//알림 매니저 객체

	 NotificationManager notiManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);		
	    //화면 구성하기
	    setContentView(R.layout.alerting);
	    //알림 매니저 객체 얻어오기
	    //notiManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    //알림 제거하기
	    //notiManager.cancel(MainActivity.MyNoti);
	   }
	   //버튼을 눌렀을때 액티비티 종료하기 위해
	   public void push(View v){
	    finish();
	   }
}
