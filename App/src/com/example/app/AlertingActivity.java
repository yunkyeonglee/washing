package com.example.app;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;

/*
 * �˸�â�� Ŭ���ϸ� ����Ǵ� ��Ƽ��Ƽ 
 */

public class AlertingActivity extends Activity {
	//�˸� �Ŵ��� ��ü

	 NotificationManager notiManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);		
	    //ȭ�� �����ϱ�
	    setContentView(R.layout.alerting);
	    //�˸� �Ŵ��� ��ü ������
	    //notiManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    //�˸� �����ϱ�
	    //notiManager.cancel(MainActivity.MyNoti);
	   }
	   //��ư�� �������� ��Ƽ��Ƽ �����ϱ� ����
	   public void push(View v){
	    finish();
	   }
}
