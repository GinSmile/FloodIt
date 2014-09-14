package com.xujinsmile.FloodIt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseLevel extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels);
		
		//返回按钮设置
		TextView back_btn = (TextView)findViewById(R.id.back_to_startmenu);
		back_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseLevel.this, MainActivity.class);
	            startActivity(intent);
	            ChooseLevel.this.finish();
	        	
			}
			
		});
		
		//简易模式意图
		TextView easy = (TextView)findViewById(R.id.easy);
		easy.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(0);
			}
			
		});
		
		//中等模式意图
		TextView medium = (TextView)findViewById(R.id.medium);
		medium.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(1);
			}
			
		});
		
		//困难模式意图
		TextView hard = (TextView)findViewById(R.id.hard);
		hard.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(2);
			}
			
		});
	}
	
	//重写返回
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
            Intent intent = new Intent(ChooseLevel.this, MainActivity.class);
            this.startActivity(intent);
            this.finish();
        	return true;  
        } else  
            return super.onKeyDown(keyCode, event);  
    } 
	
	
	
	private void startGame(int i){
//		Toast toast = Toast.makeText(ChooseLevel.this,"start..." + i, 
//    			Toast.LENGTH_SHORT);
//		toast.show();
	
		Intent intent = new Intent(this, GameBoard.class);
		intent.putExtra("difficulty", i+"");
		startActivity(intent);
		
		ChooseLevel.this.finish();
		overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		
	}
}
