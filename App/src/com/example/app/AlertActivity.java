package com.example.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;


//일단은 MainActivity에서 예약 버튼 누르면 AlertActivity로 연결되도록 임시 연결

/*
 * 카톡 메시지가 도착하거나, 업데이트가 있을 때 좌상단에 작은 표시가 뜨는것을 Notification이라고 한다.
 * [ Notification ] 알림 사용하기
 *  
 */

public class AlertActivity extends Activity {
	// 알림 메니저 객체
	NotificationManager notiManager;
	// 진동을 줘보자 진동객체
	Vibrator vibrator;
	// 알림 식별값
	final static int MyNoti = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert);
		// 알림 메니저 객체 얻어오기
		notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// 진동 객체 생성
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	// 버튼을 눌렀을때 실행되는 메소드
	public void push(View v) {
		// 버튼을 눌렀을 때 테스트 하기
		Toast.makeText(this, "10초후에 알림이 올 예정입니다", 0).show();
		handler.sendEmptyMessageDelayed(0, 10000);
	}

	// 일정 시간 후에 알림을 발생시킬 핸들러
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// notification 객체 생성(상단바에 보여질 아이콘, 메세지, 도착시간 정의)
			Notification noti = new Notification(R.drawable.icon1// 알림창에 띄울 아이콘
					, "세탁하실 시간입니다.", // 간단 메세지
					System.currentTimeMillis()); // 도착 시간
			// 기본으로 지정된 소리를 내기 위해
			noti.defaults = Notification.DEFAULT_SOUND;
			// 알림 소리를 한번만 내도록
			noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;
			// 확인하면 자동으로 알림이 제거 되도록
			noti.flags = Notification.FLAG_AUTO_CANCEL;
			// 사용자가 알람을 확인하고 클릭했을때 새로운 액티비티를 시작할 인텐트 객체
			Intent intent = new Intent(AlertActivity.this, AlertingActivity.class);
			// 새로운 태스크(Task) 상에서 실행되도록(보통은 태스크1에 쌓이지만 태스크2를 만들어서 전혀 다른 실행으로
			// 관리한다)
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// 인텐트 객체를 포장해서 전달할 인텐트 전달자 객체
			PendingIntent pendingI = PendingIntent.getActivity(	AlertActivity.this, 0, intent, 0);
			// 상단바를 드래그 했을때 보여질 내용 정의하기
			noti.setLatestEventInfo(AlertActivity.this, "SWEATAK", "예약하신 세탁 시간이 되었습니다.",
					pendingI);
			// 알림창 띄우기(알림이 여러개일수도 있으니 알림을 구별할 상수값, 여러개라면 상수값을 달리 줘야 한다.)
			notiManager.notify(MyNoti, noti);
			// 진동주기(** 퍼미션이 필요함 **)
			vibrator.vibrate(1000); // 1초 동안 진동
		}
	};

}
