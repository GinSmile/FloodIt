package com.xujinsmile.FloodIt;

import com.xujinsmile.FloodIt.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity implements android.view.View.OnClickListener{

	
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
		
		View startButton = findViewById(R.id.start_button);
		startButton.setOnClickListener(this);
		View scoreButton = findViewById(R.id.score_button);
		scoreButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){			
			case R.id.start_button:
				openStartDialog();
				break;
			case R.id.score_button:				
				Intent intent = new Intent(this, HighScore.class);
				startActivity(intent);
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
	
	private void openStartDialog(){
		new AlertDialog.Builder(this).setTitle(R.string.start_title).setIcon(getResources().getDrawable(R.drawable.difficult)).setItems(R.array.difficulty, 
				new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						startGame(which);						
					}
				}).show();
	}
	
	private void startGame(int i){
		//Toast toast = Toast.makeText(MainActivity.this,"start..." + i, 
    	//		Toast.LENGTH_SHORT);
		//toast.show();
		Intent intent = new Intent(this, GameBoard.class);
		intent.putExtra("difficulty", i+"");
		startActivity(intent);
		
	}
	
	
	
	
	static SharedPreferences pre;
	static SharedPreferences.Editor editor;

}
