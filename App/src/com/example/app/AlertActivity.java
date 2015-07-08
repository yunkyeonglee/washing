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


//�ϴ��� MainActivity���� ���� ��ư ������ AlertActivity�� ����ǵ��� �ӽ� ����

/*
 * ī�� �޽����� �����ϰų�, ������Ʈ�� ���� �� �»�ܿ� ���� ǥ�ð� �ߴ°��� Notification�̶�� �Ѵ�.
 * [ Notification ] �˸� ����ϱ�
 *  
 */

public class AlertActivity extends Activity {
	// �˸� �޴��� ��ü
	NotificationManager notiManager;
	// ������ �ຸ�� ������ü
	Vibrator vibrator;
	// �˸� �ĺ���
	final static int MyNoti = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert);
		// �˸� �޴��� ��ü ������
		notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// ���� ��ü ����
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	// ��ư�� �������� ����Ǵ� �޼ҵ�
	public void push(View v) {
		// ��ư�� ������ �� �׽�Ʈ �ϱ�
		Toast.makeText(this, "10���Ŀ� �˸��� �� �����Դϴ�", 0).show();
		handler.sendEmptyMessageDelayed(0, 10000);
	}

	// ���� �ð� �Ŀ� �˸��� �߻���ų �ڵ鷯
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// notification ��ü ����(��ܹٿ� ������ ������, �޼���, �����ð� ����)
			Notification noti = new Notification(R.drawable.icon1// �˸�â�� ��� ������
					, "��Ź�Ͻ� �ð��Դϴ�.", // ���� �޼���
					System.currentTimeMillis()); // ���� �ð�
			// �⺻���� ������ �Ҹ��� ���� ����
			noti.defaults = Notification.DEFAULT_SOUND;
			// �˸� �Ҹ��� �ѹ��� ������
			noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;
			// Ȯ���ϸ� �ڵ����� �˸��� ���� �ǵ���
			noti.flags = Notification.FLAG_AUTO_CANCEL;
			// ����ڰ� �˶��� Ȯ���ϰ� Ŭ�������� ���ο� ��Ƽ��Ƽ�� ������ ����Ʈ ��ü
			Intent intent = new Intent(AlertActivity.this, AlertingActivity.class);
			// ���ο� �½�ũ(Task) �󿡼� ����ǵ���(������ �½�ũ1�� �������� �½�ũ2�� ���� ���� �ٸ� ��������
			// �����Ѵ�)
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// ����Ʈ ��ü�� �����ؼ� ������ ����Ʈ ������ ��ü
			PendingIntent pendingI = PendingIntent.getActivity(	AlertActivity.this, 0, intent, 0);
			// ��ܹٸ� �巡�� ������ ������ ���� �����ϱ�
			noti.setLatestEventInfo(AlertActivity.this, "SWEATAK", "�����Ͻ� ��Ź �ð��� �Ǿ����ϴ�.",
					pendingI);
			// �˸�â ����(�˸��� �������ϼ��� ������ �˸��� ������ �����, ��������� ������� �޸� ��� �Ѵ�.)
			notiManager.notify(MyNoti, noti);
			// �����ֱ�(** �۹̼��� �ʿ��� **)
			vibrator.vibrate(1000); // 1�� ���� ����
		}
	};

}
