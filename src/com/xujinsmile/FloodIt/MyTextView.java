package com.xujinsmile.FloodIt;

import android.content.Context;
import android.widget.TextView;


public class MyTextView extends TextView{

	public MyTextView(Context context) {
		super(context);
	}
	
	@Override
	public void setBackgroundColor (int color)  {
		super.setBackgroundColor(color);
		myColor = color;
		
	}
	
	public int getBackgroundColor(){
		return myColor;
	}
	
	int myColor;
}
