package com.xujinsmile.FloodIt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class MainActivity extends Activity implements android.view.View.OnClickListener{

	//存储
	public static SharedPreferences getSharedPreferences(){
		return pre;
	}
	
	public static SharedPreferences.Editor getSharedPreferencesEditor(){
		return editor;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startmenu);	
		
		pre = this.getSharedPreferences("score", MODE_PRIVATE);	
		editor = pre.edit();
		
		//四个选项条的动画
		Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.welcome_anime);
		
		TextView startButton = (TextView) findViewById(R.id.start_button);
		startButton.startAnimation(animation);
		startButton.setOnClickListener(this);
		
		TextView scoreButton = (TextView)findViewById(R.id.score_button);
		scoreButton.startAnimation(animation);
		scoreButton.setOnClickListener(this);
		
		TextView aboutButton = (TextView)findViewById(R.id.about_button);
		aboutButton.startAnimation(animation);
		aboutButton.setOnClickListener(this);
		
		TextView exitButton = (TextView)findViewById(R.id.exit_button);
		exitButton.startAnimation(animation);
		exitButton.setOnClickListener(this);
	}
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		switch(v.getId()){			
			case R.id.start_button:
				//TextView startButton = (TextView) findViewById(R.id.start_button);
				Intent intentPlay = new Intent(MainActivity.this, ChooseLevel.class);
				MainActivity.this.startActivity(intentPlay);
				MainActivity.this.finish();
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout); 			
				break;
				
			case R.id.score_button:				
				Intent intent = new Intent(this, HighScore.class);
				startActivity(intent);
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				finish();
				break;
			case R.id.about_button:
				Intent i = new Intent(this, About.class);
				startActivity(i);
				break;			
			case R.id.exit_button:
				finish();
				break;
			
		}
		
	}
	
//	private void openStartDialog(){
//		new AlertDialog.Builder(this).setTitle(R.string.start_title).setIcon(getResources().getDrawable(R.drawable.difficult)).setItems(R.array.difficulty, 
//				new DialogInterface.OnClickListener() {					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						startGame(which);						
//					}
//				}).show();
//	}
	

	
	
	
	
	static SharedPreferences pre;
	static SharedPreferences.Editor editor;

}
