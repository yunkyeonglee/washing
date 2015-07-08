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
		initialize();// ���� �ʱ�ȭ
		startGCM();// GCM ���� �޼ҵ�
		

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

		// �ϴ��� MainActivity���� ���� ��ư ������ AlertActivity�� ����ǵ��� �ӽ� ����
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
	 * GCM ���񽺸� �����Ѵ�.
	 */
	private void startGCM() {

		/**
		 * GCM Service�� �̿� ������ Device���� üũ�Ѵ�. api 8(Android 2.2) �̸��� ��쳪
		 * GCMService�� �̿��� �� ���� ����̽��� ��� ������ �߻���Ű�� �ݵ�� ����ó���ϵ��� �Ѵ�.
		 */
		try {
			GCMRegistrar.checkDevice(appContext);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "This device can't use GCM");
			return;
		}

		/**
		 * 2.SharedPreference�� ����� RegistrationID�� �ִ��� Ȯ���Ѵ�. ���� ��� null�� �ƴ� ""��
		 * ����
		 */
		String regId = GCMRegistrar.getRegistrationId(appContext);

		/**
		 * Registration Id�� ���� ���(���ø����̼� ���� ��ġ�� �߱޹��� ���� ���ų�, ���� �� �缳ġ ��
		 * SharedPreference�� ����� Registration Id�� ���� ��찡 �̿� �ش��Ѵ�.)
		 */
		if (CommonUtils.isEmpty(regId)) {
			/**
			 * 3.RegstrationId�� ���� ��� GCM Server�� Regsitration ID�� �߱� ��û�Ѵ�. �߱�
			 * ��û�� ���������� �̷���� ��� Registration ID�� SharedPreference�� ����Ǹ�,
			 * GCMIntentService.class�� onRegistered�� �ݹ��Ѵ�.
			 */
			GCMRegistrar.register(appContext, PROJECT_ID);

			// SharedPreference�� ����� Registration Id�� �����ϴ� ��
		} else {
			Toast.makeText(appContext, "Exist Registration Id: " + regId,
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		/**
		 * 4.�� ����Ǳ� ���̳� �����ϱ� ���� GCMRegistrar.onDestroy�� �ݵ�� ȣ���Ѵ�. ȣ������ ���� ���
		 * unRegisterReceiver������ �߻��Ѵ�. �ش� �Լ��� null�̳� ��Ÿ ������ ���� ���������� ���� ó���ϰ�
		 * �����Ƿ�, �ƹ����� ������ ȣ���ص� �ȴ�.
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
