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
		
        //클래스의 상속은 PreferenceActivity를 받아야 한다.
        //보여주는 XML을 지정하기위해 위와 같은 메서드를 이용한다.
        
//        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        //이동할 액티비티로 intent 생성. 이럼 TestActivity로 이동하는 인텐트 생성입니다.
             //이동할 액티비티는 아무거나 정해줍니다.
        
//        Preference test = findPreference("test_pref");
//        test.setIntent(intent);
        //findPrefernece 메서드로 키값을 이용해서 프레프런스 객체를 생성
        //setIntent 메서드로 생성한 인텐트를 지정해준다.
        
        
        
        Preference pAppName = (Preference)findPreference("keyhelp");
        Preference pAppVersion = (Preference)findPreference("keycontact");
        
        pAppName.setOnPreferenceClickListener(this);
        pAppVersion.setOnPreferenceClickListener(this);
        
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// 도움말 선택시 
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
