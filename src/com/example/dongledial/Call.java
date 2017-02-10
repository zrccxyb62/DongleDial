package com.example.dongledial;

public class Call {
	public static native int dial(int number);
	public static int callnumber(int no){
		int sq = dial(no);
		return dial(sq);
	}
	static {
		
        System.loadLibrary("dial");
        
    }
}
