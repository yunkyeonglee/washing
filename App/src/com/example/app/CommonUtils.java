package com.example.app;

public class CommonUtils {
	/**
     * 인자값이 null이거나 trim의 결과가 ""인 경우 true를 리턴한다.
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
