package com.example.app;

import java.util.Iterator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

/**
 * GCMBaseIntentService�� ��ӹ��� Ŭ������ ������Ʈ ��Ʈ ��Ű���� �����Ѵ�. Ŭ������ �ݵ�� ���� ������ �����ؾ� �Ѵ�.
 * 
 * 1.Ŭ�������� GCMIntentService���� �Ѵ�. 2.�ݵ�� ��Ʈ ��Ű�� ���� ����Ǿ� �־�� �Ѵ�. ��� 1,2 ������ �����ؾ�
 * �ϴ� ������ ���̺귯�� ������ GCMService�� �����ϴ� �κ��� 'GCMIntentService'��� ��Ī���� �ϵ��ڵ��Ǿ� ����Ǿ�
 * �ִ�.
 * 
 * @author Leminity
 * 
 */
public class GCMIntentService extends GCMBaseIntentService {

	public static final int NOTIFICATION_ID = 1;

	private static final String tag = "GCMIntentService";
	private static final String PROJECT_ID = "sweatak-998";

	/**
	 * GCM Server�κ��� �߱޹��� Project ID�� ���� SuperClass�� GCMBaseIntentService��
	 * �����ؾ��Ѵ�.
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
		 * GCM ���� �߻� �� ó���ؾ� �� �ڵ带 �ۼ��Ѵ�. ErrorCode�� ���ؼ� GCM Ȩ�������� GCMConstants ��
		 * static variable �����Ѵ�.
		 */
		
		Log.d(tag, "onError. errorId : "+ errorId);

	}

	/** Ǫ�÷� ���� �޽��� */
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		/**
		 * GCMServer�� �����ϴ� �޽����� ���� ó�� �� ��� �����ϴ� �޼ҵ��̴�. Notification, �� ���� ���
		 * �����ڰ� �ϰ� ���� ������ �ش� �޼ҵ忡�� �����Ѵ�. ���޹��� �޽�����
		 * Intent.getExtras().getString(key)�� ���� ������ �� �ִ�.
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
		 * GCMRegistrar.getRegistrationId(context)�� ����Ǿ� registrationId�� �߱޹��� ���
		 * �ش� �޼ҵ尡 �ݹ�ȴ�. �޽��� �߼��� ���� regId�� ������ �����ϵ��� ����.
		 */

		Log.d(tag, "onRegistered. regId : " + regId);
	}

	@Override
	protected void onUnregistered(Context arg0, String regId) {
		// TODO Auto-generated method stub
		/**
		 * GCMRegistrar.unregister(context) ȣ��� �ش� ����̽��� registrationId�� ������û��
		 * ��� �ش� �޼ҵ尡 �ݹ�ȴ�.
		 */

		Log.d(tag, "onUnregistered. regId : " + regId);
	}

}