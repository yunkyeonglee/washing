package com.example.app;

import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WebView webView;
	Button buttonNotice, buttonPersonalSetting, buttonQnA, buttonWebSetting;
	ImageButton buttonReservation, buttonSetting, buttonLogin;
	private OnClickListener cListener;

	/**
	 * GCM
	 */
	private static final String TAG = "MainActivity";
	private static final String PROJECT_ID = "30404537792"; // Google Cloud
															// Messageing
	// Service PROJECT ID
	private Context appContext = null;// applicationContext

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/**
		 * GCM
		 */
		initialize();// 변수 초기화
		startGCM();// GCM 시작 메소드
		

		buttonNotice = (Button) findViewById(R.id.buttonNotice);
		buttonNotice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						NoticeActivity.class);
				startActivity(intent);
			}
		});
		buttonPersonalSetting = (Button) findViewById(R.id.buttonPersonalSetting);
		buttonPersonalSetting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						PersonalSettingActivity.class);
				startActivity(intent);
			}
		});
		buttonQnA = (Button) findViewById(R.id.buttonQnA);
		buttonQnA.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, QnaActivity.class);
				startActivity(intent);
			}
		});

		// 일단은 MainActivity에서 예약 버튼 누르면 AlertActivity로 연결되도록 임시 연결
		buttonReservation = (ImageButton) findViewById(R.id.buttonReservation);
		buttonReservation.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						AlertActivity.class);
				startActivity(intent);
			}
		});
		buttonReservation = (ImageButton) findViewById(R.id.buttonReservationBuble);
		buttonReservation.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						TestActivity.class);
				startActivity(intent);
			}
		});
		buttonSetting = (ImageButton) findViewById(R.id.buttonSetting);
		buttonSetting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SettingActivity.class);
				startActivity(intent);
			}
		});
		buttonLogin = (ImageButton) findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initialize() {
		appContext = getApplicationContext();
	}

	/**
	 * GCM 서비스를 시작한다.
	 */
	private void startGCM() {

		/**
		 * GCM Service가 이용 가능한 Device인지 체크한다. api 8(Android 2.2) 미만인 경우나
		 * GCMService를 이용할 수 없는 디바이스의 경우 오류를 발생시키니 반드시 예외처리하도록 한다.
		 */
		try {
			GCMRegistrar.checkDevice(appContext);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "This device can't use GCM");
			return;
		}

		/**
		 * 2.SharedPreference에 저장된 RegistrationID가 있는지 확인한다. 없는 경우 null이 아닌 ""이
		 * 리턴
		 */
		String regId = GCMRegistrar.getRegistrationId(appContext);

		/**
		 * Registration Id가 없는 경우(어플리케이션 최초 설치로 발급받은 적이 없거나, 삭제 후 재설치 등
		 * SharedPreference에 저장된 Registration Id가 없는 경우가 이에 해당한다.)
		 */
		if (CommonUtils.isEmpty(regId)) {
			/**
			 * 3.RegstrationId가 없는 경우 GCM Server로 Regsitration ID를 발급 요청한다. 발급
			 * 요청이 정상적으로 이루어진 경우 Registration ID는 SharedPreference에 저장되며,
			 * GCMIntentService.class의 onRegistered를 콜백한다.
			 */
			GCMRegistrar.register(appContext, PROJECT_ID);

			// SharedPreference에 저장된 Registration Id가 존재하는 경
		} else {
			Toast.makeText(appContext, "Exist Registration Id: " + regId,
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		/**
		 * 4.앱 종료되기 전이나 종료하기 전에 GCMRegistrar.onDestroy를 반드시 호출한다. 호출하지 않을 경우
		 * unRegisterReceiver오류가 발생한다. 해당 함수는 null이나 기타 오류에 대해 내부적으로 예외 처리하고
		 * 있으므로, 아무때나 마음껏 호출해도 된다.
		 */
		GCMRegistrar.onDestroy(appContext);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
