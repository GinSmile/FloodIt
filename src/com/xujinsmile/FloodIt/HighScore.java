package com.xujinsmile.FloodIt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HighScore extends Activity{
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_score);
		
		TextView back_btn = (TextView)findViewById(R.id.back_to_startmenu);
		back_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HighScore.this, MainActivity.class);
	            startActivity(intent);
	            HighScore.this.finish();
	        	
			}
			
		});

		
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
