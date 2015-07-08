package com.example.app;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PersonalSettingActivity extends Activity  {

	ArrayList<String> arraylist;
//	
//	private String keyName = "name";
//    private String keyDesc = "desc";
//    private String TAG;
//	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.personal_setting);
	
	    // TODO Auto-generated method stub
	    String[] optionLavala = getResources().getStringArray(R.array.spinnerArray1);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, optionLavala);
	    Spinner obj = (Spinner)findViewById(R.id.spinner1);
	    obj.setAdapter(adapter);
	    
	    	    
//	    TAG = getClass().getName();
	    
	    findViewById(R.id.btn_optalert).setOnClickListener(mClickListener);
	    
//	    setListAdapter(new SimpleAdapter(this, getListValues(), 
//                android.R.layout.simple_list_item_2, new String[] {
//                keyName, keyDesc }, new int[] { android.R.id.text1, android.R.id.text2 }));
//   
	}
	
	OnClickListener mClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final String items[] = { "의암관", "한서관", "예지원" };
	        AlertDialog.Builder ab = new AlertDialog.Builder(PersonalSettingActivity.this);
	        ab.setTitle("기숙사");
	        ab.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                // 각 리스트를 선택했을때
	            }
	        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.
	            }
	        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                // Cancel 버튼 클릭시
	            }
	        });
	        ab.show();
			
		}
	};
	
//	private void DialogSelectOption() {
//        
//    }
//	
}


