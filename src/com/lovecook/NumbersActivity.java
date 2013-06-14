package com.lovecook;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class NumbersActivity extends ListActivity implements OnClickListener {
	
		private SQLiteDatabase database;
		private ListView listView;
		private ArrayList meals;
		private Button back;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numbers);
		
		back = (Button)findViewById(R.id.back);
		back.setOnClickListener(this);		
		
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
       

        List<String> meals = dbOpenHelper.getAllNumbers();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, meals);
            setListAdapter(adapter);
        
        database.close();
		
	}
	
	/*private void setUpList() {
		//Используем стандартный адаптер и layout элемента для краткости
		setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, meals));
		listView = getListView();

		//Подарим себе тост — для души
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view,
							int position,long id) {
				Toast.makeText(getApplicationContext(),
							((TextView) view).getText() +
							 " could be iDev's friend",
							 Toast.LENGTH_SHORT).show();
			}
		});
	}*/

	//Извлечение элементов из базы данных
	/*private void fillFreinds() {
		meals = new ArrayList<String>();
		Cursor mealsCursor = database.query(TABLE_NAME, new String[]{MEALS_ID, MEALS_RECIPE}, null, null, null, null, null);
		mealsCursor.moveToFirst();
			if(!mealsCursor.isAfterLast()) {
				do {
					String name = mealsCursor.getString(1);
					meals.add(name);
				} while (mealsCursor.moveToNext());
			}
			mealsCursor.close();
		}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.numbers, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.back:
			Intent backIntent = new Intent(NumbersActivity.this, MainActivity.class);
			startActivity(backIntent);
			break;

		default:
			break;
		}
		
	}

}
