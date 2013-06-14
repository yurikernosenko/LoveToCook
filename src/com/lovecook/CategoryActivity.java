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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CategoryActivity extends ListActivity implements OnClickListener {
	
	private Button back;
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList meals;
	private String selectedItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		back = (Button)findViewById(R.id.back);
		
		back.setOnClickListener(this);
		
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
       
        
        
        
        List<String> meals = dbOpenHelper.getAllCategories();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, meals);
            setListAdapter(adapter);
       
        OnItemClickListener clickListener = new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				selectedItem = parent.getItemAtPosition(position).toString();
				Intent itemDetailListIntent = new Intent (CategoryActivity.this, CategoryDetailActivity.class);
				itemDetailListIntent.putExtra("item", selectedItem);
				
				startActivity(itemDetailListIntent);
				
			}};    
		getListView().setOnItemClickListener(clickListener);    
        database.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			Intent backIntent = new Intent(CategoryActivity.this, MainActivity.class);
			startActivity(backIntent);
			break;

		default:
			break;
		}
	}

}
