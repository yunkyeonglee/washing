package com.example.app;

public class CommonUtils {
	/**
     * ���ڰ��� null�̰ų� trim�� ����� ""�� ��� true�� �����Ѵ�.
     * @param String
     * @return boolean
     */
    public static boolean isEmpty(String value){
         
        boolean isEmpty = false;
         
        if((value == null) || value == null)
            isEmpty = true;
         
        return isEmpty;
    }
}
