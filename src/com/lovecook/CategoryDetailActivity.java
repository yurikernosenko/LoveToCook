package com.lovecook;

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


public class CategoryDetailActivity extends ListActivity implements OnClickListener {
	
	private Button back;
	private ListView categoryDetail;
	private SQLiteDatabase database;
	private List<String> categoryDeatailList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_detail);
		
		back = (Button)findViewById(R.id.back);
				
		String category = getIntent().getStringExtra("item");
		
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        database = dbOpenHelper.openDataBase();
		
        categoryDeatailList = dbOpenHelper.getDetailCategory(category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categoryDeatailList);
            setListAdapter(adapter);
		
		
		back.setOnClickListener(this);
		
		database.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category_detail, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			Intent backIntent = new Intent (CategoryDetailActivity.this,CategoryActivity.class);
			startActivity(backIntent);
			break;

		default:
			break;
		}
		
	}

}
