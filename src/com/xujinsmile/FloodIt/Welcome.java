package com.xujinsmile.FloodIt;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//��ӭ����
public class Welcome extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.welcome);
		//�ӳ�0.7���ִ��run�����е�ҳ����ת
		new Handler().postDelayed(new Runnable() {			
			@Override
			public void run() {
				Intent intent = new Intent(Welcome.this, MainActivity.class);
				startActivity(intent);
				Welcome.this.finish();
				//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
		}, 700);
	}
}