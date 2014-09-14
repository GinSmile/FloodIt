package com.xujinsmile.FloodIt;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class GameBoard extends Activity{	

	SharedPreferences pre;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);

		pre = MainActivity.getSharedPreferences();
		editor = MainActivity.getSharedPreferencesEditor();
		
		
		//���ذ�ť����
		TextView back_btn = (TextView)findViewById(R.id.back_to_startmenu);
		back_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameBoard.this, ChooseLevel.class);
	            startActivity(intent);
	            GameBoard.this.finish();
	        	
			}
			
		});
		
		//��һ�ν�����ʾһ��������Ϣ
		int first = Integer.parseInt(pre.getString("first", "1"));
		if(first == 1){
			//��һ��
			Toast toast = Toast.makeText(GameBoard.this,"�����Ļ�·��Ĵ�ɫ������ :-)", 
	    			Toast.LENGTH_LONG);
			View toastView = toast.getView();
			ImageView image = new ImageView(GameBoard.this);
			image.setImageResource(R.drawable.info);
			LinearLayout ll = new LinearLayout(GameBoard.this);
			ll.addView(image);
			ll.addView(toastView);
			toast.setView(ll);
			toast.show();
			
			editor.putString("first", "0");
			editor.commit();
		}
		
		Button refresh = (Button)findViewById(R.id.refresh);
		final View ref = refresh;
		refresh.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initTheTable();
				Animation animRefresh = AnimationUtils.loadAnimation(GameBoard.this, R.anim.press_anime);
				
				ref.startAnimation(animRefresh);
				
			}
			
		});
		
		
		//step���
		stepText = (TextView)findViewById(R.id.step);
		step = 0;
		
		
		//������Ӧ���
		Intent intent = this.getIntent();
		String difficulty = intent.getStringExtra("difficulty");
		createBoard(difficulty);
		
		//��ʼ�����̣��������Ͻǵ�TextView���м��TableView
		initTheTable();
		

		//��ʼ��ÿ��Button�Ĺ���
		initButton();
	}
	
	
	//��ʼ��Button
	public void initButton(){
		int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();

		LinearLayout linear = (LinearLayout)findViewById(R.id.linearLayout1);		
		Button[] btns =  new Button[6];
		for(int i = 0; i < 6; i++){
			btns[i] = new Button(this);
			linear.addView(btns[i]);
			TextView spaceText = new TextView(this);
			spaceText.setText(" ");
			linear.addView(spaceText);
			
	
			
		}
		
		
		for(int i = 0; i < 6; i++){
			btns[i].setBackgroundColor(getResources().getColor(colors[i]));
			btns[i].setWidth(screenWidth/7);
			btns[i].setHeight(screenWidth/7);
			//btns[i].setBackground(background)
			//ע�⣬getColor��colors[i]�ܲ�һ��
			final int currentColor = i;
			
			final View tmpBut = btns[i];
			
			btns[i].setOnClickListener(new OnClickListener(){
				//�����ť�Ķ���
				//Animation animPress = AnimationUtils.loadAnimation(GameBoard.this, R.anim.press_anime);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stud
					
					Animation animPress = AnimationUtils.loadAnimation(GameBoard.this, R.anim.press_anime);
					tmpBut.startAnimation(animPress);

					
					changeColor(getResources().getColor(colors[currentColor]));
					
					if(isFinish()){
						Log.v("the log", "ok..........");						
						showWinDialog();
					}
					
					else if(step >= MAX){
						showLoseDialog();
					}
				}


			});
		}
	}
	
	public void showLoseDialog(){
		//������Dialog						
		AlertDialog.Builder builderLose = new AlertDialog.Builder(GameBoard.this);
		builderLose.setTitle("You Lose!");
		builderLose.setIcon(getResources().getDrawable(R.drawable.lose));
		builderLose.setMessage("���ź�����սʧ�ܣ�\n������ս��");
		builderLose.setPositiveButton( "������ս",  new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				initTheTable();
				step = 0;
				
			}
			
		});
		builderLose.setNegativeButton( "�����Ѷ�",  new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				//GameBoard.this.finish();
				Intent intent = new Intent(GameBoard.this, ChooseLevel.class);
				startActivity(intent);
				GameBoard.this.finish();
				//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
			
		});
		
		builderLose.create().show();
	}
	
	public void showWinDialog(){
		//������Dialog	
		AlertDialog.Builder builder = new AlertDialog.Builder(GameBoard.this);
		builder.setTitle("You Win!");
		builder.setIcon(getResources().getDrawable(R.drawable.win));
		
		int lastRecord;
		int currentRecord;
		
		switch(WIDTH){
		case EASY:
			lastRecord = Integer.parseInt(pre.getString("easy", "9999"));
			record(step);
			currentRecord = Integer.parseInt(pre.getString("easy", "9999"));
			if( lastRecord > currentRecord)//�ɹ�ˢ�¼�¼
				builder.setMessage("����" + step + "���󣬳ɹ�ˢ�¼�¼��\n������ս��¼��");
			else
				builder.setMessage("����" + step + "������ս�ɹ���\n��ʷ��߼�¼Ϊ " + lastRecord +" ����������ս��¼��");
			break;
			
		case MEDI:
			lastRecord = Integer.parseInt(pre.getString("medi", "9999"));
			record(step);
			currentRecord = Integer.parseInt(pre.getString("medi", "9999"));
			if( lastRecord > currentRecord)//�ɹ�ˢ�¼�¼
				builder.setMessage("����" + step + "���󣬳ɹ�ˢ�¼�¼��\n������ս��¼��");
			else
				builder.setMessage("����" + step + "������ս�ɹ���\n��ʷ��߼�¼Ϊ " + lastRecord +" ����������ս��¼��");
			break;
			
		case HARD:
			lastRecord = Integer.parseInt(pre.getString("hard", "9999"));
			record(step);
			currentRecord = Integer.parseInt(pre.getString("hard", "9999"));
			if( lastRecord > currentRecord)//�ɹ�ˢ�¼�¼
				builder.setMessage("����" + step + "���󣬳ɹ�ˢ�¼�¼��\n������ս��¼��");
			else
				builder.setMessage("����" + step + "������ս�ɹ���\n��ʷ��߼�¼Ϊ " + lastRecord +" ����������ս��¼��");
			break;
		
			
		}
		
		
		builder.setPositiveButton( "������ս",  new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				initTheTable();
			}
			
		});
		builder.setNegativeButton( "�����Ѷ�",  new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameBoard.this, ChooseLevel.class);
				startActivity(intent);
				GameBoard.this.finish();
				//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
			
		});
		
		builder.create().show();
		
	
	}
	
	public void colorTheTable(){
		//Ⱦɫ
				Random random = new Random();
				for(int i = 0; i < WIDTH; i++){
					for(int j = 0; j < WIDTH; j++){
						texts[i][j].setBackgroundColor(getResources().getColor(colors[random.nextInt(6)]));
					}
				}
	}
	
	public void initTheTable(){
		colorTheTable();
		step = 0;
		stepText.setText("���� " + step + "/" + (MAX));
	}
	
	public void changeColor(int currentColor){//��ʱcurrentColorΪʵ����ɫ��ֵ
		int lastColor = texts[0][0].getBackgroundColor();
		if(lastColor == currentColor)
			return;
		
		step++;
		stepText.setText("���� " + step + "/" + (MAX));
		
		texts[0][0].setBackgroundColor(currentColor);
		
		search(0, 0, lastColor, currentColor);
		
		
		
	}
	
	public void search(int x, int y, int color, int currentColor){
		//up
		if(x > 0){
			if(texts[x - 1][y].getBackgroundColor() == color){
				texts[x - 1][y].setBackgroundColor(currentColor);
				search(x - 1, y, color, currentColor);
			}
		}
		
		//down
		if(x < WIDTH -1){
			if(texts[x + 1][y].getBackgroundColor() == color){
				texts[x + 1][y].setBackgroundColor(currentColor);
				search(x + 1, y, color, currentColor);
			}
		}
		
		//right
		if(y < WIDTH - 1){
			if(texts[x][y + 1].getBackgroundColor() == color){
				texts[x][y + 1].setBackgroundColor(currentColor);
				search(x, y + 1, color, currentColor);
			}
		}
		
		//left
		if(y > 0){
			if(texts[x][y - 1].getBackgroundColor() == color){
				texts[x][y - 1].setBackgroundColor(currentColor);
				search(x, y - 1, color, currentColor);
			}
		}
	}
	
	public boolean isFinish(){
		Log.v("the log", "...............");
		for(int i = 0; i< WIDTH; i++){
			for(int j = 0; j < WIDTH; j++){
				if(texts[0][0].getBackgroundColor() != texts[i][j].getBackgroundColor())
					return false;
			}
		}
		return true;
	}
	
	public void record(int step){
		//hightscore���
		
		if(WIDTH == EASY){
			int recordEasy = Integer.parseInt(pre.getString("easy", "99999"));
			if(step < recordEasy)
				editor.putString("easy", step + "");				
		}
			
		else if(WIDTH == MEDI){
			int recordMedi = Integer.parseInt(pre.getString("medi", "99999"));
			if(step < recordMedi)
				editor.putString("medi", step + "");
		}
		else {
			int recordHard = Integer.parseInt(pre.getString("hard", "99999"));
			if(step < recordHard)
				editor.putString("hard", step + "");
		}
		editor.commit();
	}
	
	
	public void createBoard(String difficulty){
		TextView stepsText = (TextView)findViewById(R.id.step);
		switch(Integer.parseInt(difficulty)){
		case 0:
			WIDTH = EASY;
			MAX = 20;			
			break;
		case 1:
			WIDTH = MEDI;
			MAX = 22;
			break;
		case 2:
			WIDTH = HARD;
			MAX = 33;
			break;
		}
		stepsText.setText("���� 0/" + MAX);
		
		
		//���table��TextView[i][j]����һ������
		table = (TableLayout)findViewById(R.id.tablelayout);
		TableRow[] tableRows = new TableRow[WIDTH];
		texts = new MyTextView[WIDTH][WIDTH];
		int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		
		
		for(int i = 0; i < WIDTH; i++){
			tableRows[i] = new TableRow(this);
			table.addView(tableRows[i]);//��ÿһ�мӵ����
			
			//���ÿһ�е�TextView
			
			for(int j = 0; j < WIDTH; j++){
				texts[i][j] = new MyTextView(this);
				texts[i][j].setWidth(screenWidth/(WIDTH + 3));
				texts[i][j].setHeight(screenWidth/(WIDTH + 3));				
				tableRows[i].addView(texts[i][j]);
			}
		}
	
	}
	
	
	
	
	TableLayout table;
	public static String KEY_DIFFICULTY;
	public static int WIDTH = 5;  //default
	
	final int[] colors = new int[]{
			R.color.color0,
			R.color.color1,
			R.color.color2,
			R.color.color3,
			R.color.color4,
			R.color.color5,		
	};


	MyTextView[][] texts;
	TextView stepText;
	int step;
	public static final int EASY = 8;
	public static final int MEDI = 12;
	public static final int HARD = 17;
	public static int MAX = 0;
}
