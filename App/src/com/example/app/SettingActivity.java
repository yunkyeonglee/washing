package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class SettingActivity extends PreferenceActivity implements OnPreferenceClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting); 
		
        //Ŭ������ ����� PreferenceActivity�� �޾ƾ� �Ѵ�.
        //�����ִ� XML�� �����ϱ����� ���� ���� �޼��带 �̿��Ѵ�.
        
//        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        //�̵��� ��Ƽ��Ƽ�� intent ����. �̷� TestActivity�� �̵��ϴ� ����Ʈ �����Դϴ�.
             //�̵��� ��Ƽ��Ƽ�� �ƹ��ų� �����ݴϴ�.
        
//        Preference test = findPreference("test_pref");
//        test.setIntent(intent);
        //findPrefernece �޼���� Ű���� �̿��ؼ� ���������� ��ü�� ����
        //setIntent �޼���� ������ ����Ʈ�� �������ش�.
        
        
        
        Preference pAppName = (Preference)findPreference("keyhelp");
        Preference pAppVersion = (Preference)findPreference("keycontact");
        
        pAppName.setOnPreferenceClickListener(this);
        pAppVersion.setOnPreferenceClickListener(this);
        
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// ���� ���ý� 
        if(preference.getKey().equals("keyhelp"))
        {
            Intent intent = new Intent(SettingActivity.this, HelpActivity.class);
            startActivityForResult(intent, 0); 
        }
        else if(preference.getKey().equals("keycontact"))
        {
        }
        return false;
	}
}
