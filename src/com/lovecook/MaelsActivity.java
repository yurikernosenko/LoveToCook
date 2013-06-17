package com.lovecook;

import java.util.List;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MaelsActivity extends Activity {
	private TextView text;
	private SQLiteDatabase database;
	private TextView ingridients;
	private TextView steps;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maels);
		
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
       
		ingridients = (TextView)findViewById(R.id.ingridients);
		steps = (TextView)findViewById(R.id.steps);
		
		String mealName = getIntent().getStringExtra("item");
		text = (TextView)findViewById(R.id.textForMealsActivity);
		text.setText(mealName);
		
	
        
        
        
        Meals meal = dbOpenHelper.getMeal(mealName);
        
        ingridients.setText(meal.getRecipe());
        steps.setText(meal.getPreparation());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maels, menu);
		return true;
	}

}
