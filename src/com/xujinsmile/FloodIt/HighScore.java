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
			scoreTable.setText("目前还没有记录");
			Log.v("pre", "null");
		}
			
		else{
			
			String result = "";
			if(pre.getString("easy", "99999") != "99999")
				result += "\n小菜一碟    " + pre.getString("easy", "99999") + " 步";
			if(pre.getString("medi", "99999") != "99999")
				result += "\n十拿九稳    " +pre.getString("medi", "99999") + " 步";
			if(pre.getString("hard", "99999") != "99999")
				result += "\n涉险过关    " +pre.getString("hard", "99999") + " 步";
			scoreTable.setText(result);
			
			Log.v("pre", "not null" + result);
		}
		
	}
}
