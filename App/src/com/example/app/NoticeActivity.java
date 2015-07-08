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
		listDataHeader.add("������ ���� �̿�ȳ�");
		listDataHeader.add("��� �� ���� ������ �˸� ����");

		// Adding child data+
		List<String> n1 = new ArrayList<String>();
		n1.add("1. ������ ���ɱⰣ : 2015. 6. 17(��) �� 6. 20(��) 16�ñ���" + "\n"
				+ "2. �� �ǹ��� ���� ��� ���� ����"  + "\n"
				+ "3. ���� ��� : �԰�(�ù�)�ڽ��� �ݵ�� �־�� �ϸ� ���� ������ �ǰų� ���� ������ ���� �Ұ�");

		List<String> n2 = new ArrayList<String>();
		n2.add("1. ���Ⱓ : 2015. 6. 17(��) �� 6. 20(��) 16�ñ���" + "\n"
				+ "- û�� �� ���� �� ħ���Ʈ �ݳ�"  + "\n"
				+ "- û�� �ҷ����� ����(3��)�� �ΰ��� �� ����"  + "\n"
				+ "2. �ϰ���� �ܷ��� �ǹ� �̵� : 2015. 6. 21(��) 13�� ��16�� ����"  + "\n"
				+ "3. �� �̵� ���� : 2015. 6. 21(��) 09�� ��12��"  + "\n"
				+ "- ������ġȸ �ְ�"  + "\n"
				+ "- ����ڴ� 6.21(��) ���� 12�ñ��� �ش� �ǹ� ���ʿ� �������� ��");
		
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
