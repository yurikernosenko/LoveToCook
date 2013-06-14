package com.lovecook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button buttonNumbers, buttonCategories, buttonHelp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonNumbers = (Button)findViewById(R.id.numbers);
		buttonCategories = (Button)findViewById(R.id.category);
		buttonHelp = (Button)findViewById(R.id.help);
		
		buttonNumbers.setOnClickListener(this);
		buttonCategories.setOnClickListener(this);
		buttonHelp.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.numbers:
			Intent numbersIntent = new Intent(MainActivity.this,NumbersActivity.class);
			startActivity(numbersIntent);
			break;

		case R.id.category:
			Intent categoryIntent = new Intent(MainActivity.this,CategoryActivity.class);
			startActivity(categoryIntent);
			break;	
			
		case R.id.help:
			Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
	break;
		default:
			break;
		}
		
		// TODO Auto-generated method stub
		
	}

}
