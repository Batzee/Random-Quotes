package com.batz.quotes;

import java.util.List;

import com.example.quotes_example.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PepupQuotes extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//overridePendingTransition ( 0 , R.anim.slide_up );
		setContentView(R.layout.activity_random_quote);

		final DBHelper db = new DBHelper(this);
      
		final TextView quote_display_area = (TextView) findViewById(R.id.quote_display_area);

		Button genarateQuote = (Button) findViewById(R.id.button1);
		ImageButton share = (ImageButton) findViewById(R.id.imageButtonshare);
		genarateQuote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				quote_display_area.setText(db.getRandomQuote());

			}
		});

		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String shareData = quote_display_area.getText().toString();
				//String fbData= ("http://www.mysite.com/myPicPage.html?extraText="+ shareData).toString();
				String fbData = shareData;
				  ShareHelper sHelp = new ShareHelper(PepupQuotes.this, "Quote from my App", shareData, shareData, shareData, fbData);
				  sHelp.share();
			}
		});

	}

	@Override
	protected void onPause() {
		overridePendingTransition ( 0 , R.anim.slide_down );
		super.onPause();
	}
	
}
