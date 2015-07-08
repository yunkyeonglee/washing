package com.example.app;

import java.util.Iterator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

/**
 * GCMBaseIntentService를 상속받은 클래스를 프로젝트 루트 패키지에 생성한다. 클래스는 반드시 다음 조건을 충족해야 한다.
 * 
 * 1.클래스명은 GCMIntentService여야 한다. 2.반드시 루트 패키지 내에 선언되어 있어아 한다. 상기 1,2 조건을 만족해야
 * 하는 이유는 라이브러리 내에서 GCMService를 시작하는 부분이 'GCMIntentService'라는 명칭으로 하드코딩되어 적용되어
 * 있다.
 * 
 * @author Leminity
 * 
 */
public class GCMIntentService extends GCMBaseIntentService {

	public static final int NOTIFICATION_ID = 1;

	private static final String tag = "GCMIntentService";
	private static final String PROJECT_ID = "sweatak-998";

	/**
	 * GCM Server로부터 발급받은 Project ID를 통해 SuperClass인 GCMBaseIntentService를
	 * 생성해야한다.
	 */
	public GCMIntentService() {
		this(PROJECT_ID);
	}

	public GCMIntentService(String project_id) {
		super(project_id);
	}

	protected void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		/**
		 * GCM 오류 발생 시 처리해야 할 코드를 작성한다. ErrorCode에 대해선 GCM 홈페이지와 GCMConstants 내
		 * static variable 참조한다.
		 */
		
		Log.d(tag, "onError. errorId : "+ errorId);

	}

	/** 푸시로 받은 메시지 */
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		/**
		 * GCMServer가 전송하는 메시지가 정상 처리 된 경우 구현하는 메소드이다. Notification, 앱 실행 등등
		 * 개발자가 하고 싶은 로직을 해당 메소드에서 구현한다. 전달받은 메시지는
		 * Intent.getExtras().getString(key)를 통해 가져올 수 있다.
		 */
		Bundle b = intent.getExtras();

		Iterator<String> iterator = b.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = b.get(key).toString();
			Log.d(tag, "onMessage. " + key + " : " + value);
		}

	}

	@Override
	protected void onRegistered(Context arg0, String regId) {
		// TODO Auto-generated method stub
		/**
		 * GCMRegistrar.getRegistrationId(context)가 실행되어 registrationId를 발급받은 경우
		 * 해당 메소드가 콜백된다. 메시지 발송을 위해 regId를 서버로 전송하도록 하자.
		 */

		Log.d(tag, "onRegistered. regId : " + regId);
	}

	@Override
	protected void onUnregistered(Context arg0, String regId) {
		// TODO Auto-generated method stub
		/**
		 * GCMRegistrar.unregister(context) 호출로 해당 디바이스의 registrationId를 해지요청한
		 * 경우 해당 메소드가 콜백된다.
		 */

		Log.d(tag, "onUnregistered. regId : " + regId);
	}

}