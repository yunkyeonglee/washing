package com.example.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class NoticeActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notice);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("짐보관 서비스 이용안내");
		listDataHeader.add("퇴사 및 방학 거주자 알림 사항");

		// Adding child data+
		List<String> n1 = new ArrayList<String>();
		n1.add("1. 짐보관 가능기간 : 2015. 6. 17(수) ∼ 6. 20(토) 16시까지" + "\n"
				+ "2. 각 건물에 보관 장소 부착 예정"  + "\n"
				+ "3. 보관 방법 : 규격(택배)박스에 반드시 넣어야 하며 오염 예측이 되거나 고가의 물건은 보관 불가");

		List<String> n2 = new ArrayList<String>();
		n2.add("1. 퇴사기간 : 2015. 6. 17(수) ∼ 6. 20(토) 16시까지" + "\n"
				+ "- 청소 후 열쇠 및 침대시트 반납"  + "\n"
				+ "- 청소 불량으로 벌점(3점)이 부과될 수 있음"  + "\n"
				+ "2. 하계방학 잔류자 건물 이동 : 2015. 6. 21(일) 13시 ∼16시 까지"  + "\n"
				+ "3. 짐 이동 서비스 : 2015. 6. 21(일) 09시 ∼12시"  + "\n"
				+ "- 관생자치회 주관"  + "\n"
				+ "- 희망자는 6.21(일) 오전 12시까지 해당 건물 앞쪽에 내놓으면 됨");
		
//		yejiwon.add("Despicable Me 2");
//		yejiwon.add("Turbo");
//		yejiwon.add("Grown Ups 2");
//		yejiwon.add("Red 2");
//		yejiwon.add("The Wolverine");


		listDataChild.put(listDataHeader.get(0), n1); // Header, Child data
		listDataChild.put(listDataHeader.get(1), n2);
//		listDataChild.put(listDataHeader.get(2), btl);

	}

}
