package com.batz.quotes;

import com.example.quotes_example.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class HomePage extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition ( 0 , R.anim.slide_down );
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Intent mainPage = new Intent(HomePage.this, PepupQuotes.class);
				startActivity(mainPage);
				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 3000);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition ( 0 , R.anim.slide_down );
	}

}
