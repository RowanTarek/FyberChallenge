package com.ardev.assessment.fyberchallenge.utils;


import android.util.Log;

public class AppLog {
	//----------------------------------------------------------------//	
	public static final boolean inDevelopment = true;
	//----------------------------------------------------------------//	
	public static final void e(String tag, String message){
		if(inDevelopment){
			Log.e(tag, message);
		}else{}
	}
	//----------------------------------------------------------------//
	public static final void w(String tag, String message){
		if(inDevelopment){
			Log.w(tag, message);
		}else{}
	}//----------------------------------------------------------------//
	public static final void w(String tag, Throwable exception){
		if(inDevelopment){
			Log.w(tag, exception);
		}else{}
	}
	//----------------------------------------------------------------//
	public static final void i(String tag, String message){
		if(inDevelopment){
			Log.i(tag, message);
		}else{}
	}
	//----------------------------------------------------------------//
	public static final void d(String tag, String message){
		if(inDevelopment){
			Log.d(tag, message);
		}else{}
	}
	//----------------------------------------------------------------//
	public static final void v(String tag, String message){
		if(inDevelopment){
			Log.v(tag, message);
		}else{}
	}
	//----------------------------------------------------------------//
	public static final void e(String string, String string2, Throwable e) {
		if(inDevelopment){
			Log.e(string, string2, e);
		}else{}
		
	}
}
