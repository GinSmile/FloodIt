package com.xujinsmile.FloodIt;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.xujinsmile.FloodIt.R;

public class HighScore extends Activity{
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_score);
		
		TextView scoreTable = (TextView)findViewById(R.id.highScore);	
		
		SharedPreferences pre = MainActivity.getSharedPreferences();	
		if(pre.getString("easy", "99999") == "99999"
				&&pre.getString("medi", "99999") == "99999"
				&&pre.getString("hard", "99999") == "99999"){
			scoreTable.setText("Ŀǰ��û�м�¼");
			Log.v("pre", "null");
		}
			
		else{
			
			String result = "";
			if(pre.getString("easy", "99999") != "99999")
				result += "\nС��һ��    " + pre.getString("easy", "99999") + " ��";
			if(pre.getString("medi", "99999") != "99999")
				result += "\nʮ�þ���    " +pre.getString("medi", "99999") + " ��";
			if(pre.getString("hard", "99999") != "99999")
				result += "\n���չ���    " +pre.getString("hard", "99999") + " ��";
			scoreTable.setText(result);
			
			Log.v("pre", "not null" + result);
		}
		
	}
}
