package com.lovecook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public static String DB_PATH;
	public static String DB_NAME = "ILoveCookin.sqlite3";
	public SQLiteDatabase db;
	public final Context context;
	   
	 //Хорошей практикой является задание имен полей БД константами
	private static final String TABLE_NAME = "meals";
	private static final String MEALS_ID = "_id";
	private static final String MEALS_NUMBER = "number";
	private static final String MEALS_CATEGORY = "category";
	private static final String MEALS_NAME = "name";
	private static final String MEALS_RECIPE = "recipe";
	private static final String MEALS_PREPARATION = "preparation";
		
	
	public SQLiteDatabase getDb(){
		return db;
	}

	public DBOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;
		String packageName = context.getPackageName();
		DB_PATH = String.format("//data//data//%s//databases//", packageName);
		openDataBase();	
	}

	//create db if it not exists
		public void createDataBase() {
			boolean dbExist = checkDataBase();
			if (!dbExist) {
				this.getReadableDatabase();
				try {
					copyDataBase();
				} catch (IOException e) {
					Log.e(this.getClass().toString(), "Copying error");
					throw new Error("Error copying database!");
				}
			} else {
				Log.i(this.getClass().toString(), "Database already exists");
			}
		}
		//check if db exists
		private boolean checkDataBase() {
			SQLiteDatabase checkDb = null;
			try {
				String path = DB_PATH + DB_NAME;
				checkDb = SQLiteDatabase.openDatabase(path, null,
						SQLiteDatabase.OPEN_READONLY);
			} catch (SQLException e) {
				Log.e(this.getClass().toString(), "Error while checking db");
			}

			if (checkDb != null) {
				checkDb.close();
			}
			return checkDb != null;
		}
		//copy db
		private void copyDataBase() throws IOException {
			// Открываем поток для чтения из уже созданной нами БД
			//источник в assets
			InputStream externalDbStream = context.getAssets().open(DB_NAME);

			// Путь к уже созданной пустой базе в андроиде
			String outFileName = DB_PATH + DB_NAME;

			// Теперь создадим поток для записи в эту БД побайтно
			OutputStream localDbStream = new FileOutputStream(outFileName);

			// Собственно, копирование
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = externalDbStream.read(buffer)) > 0) {
				localDbStream.write(buffer, 0, bytesRead);
			}
			// закроем потоки
			localDbStream.close();
			externalDbStream.close();

		}

		public SQLiteDatabase openDataBase() throws SQLException {
			String path = DB_PATH + DB_NAME;
			if (db == null) {
				createDataBase();
				db = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READWRITE);
			}
			return db;
		}
		@Override
		public synchronized void close() {
			if (db != null) {
				db.close();
			}
			super.close();
		}
		
		public Meals getMeal(int id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	 
	        Cursor cursor = db.query(TABLE_NAME, new String[] { MEALS_ID,
	                MEALS_NUMBER, MEALS_CATEGORY, MEALS_NAME, MEALS_RECIPE, MEALS_PREPARATION }, MEALS_ID + "=?",
	                new String[] { String.valueOf(id) }, null, null, null, null);
	 
	        if (cursor != null){
	            cursor.moveToFirst();
	        }
	 
	        Meals meal = new Meals(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
	        cursor.close();
	        return meal;
	    }
		
		private Meals cursorToComment(Cursor cursor) {
			Meals meal = new Meals();
            meal.set_id(Integer.parseInt(cursor.getString(0)));
            meal.setNumber(cursor.getString(1));
            meal.setCategory(cursor.getString(2));                
            meal.setName(cursor.getString(3));
            meal.setRecipe(cursor.getString(6));
            meal.setPreparation(cursor.getString(7));
		    return meal;
		  }
		
		//get all categories
				public List<String> getAllCategories(){
					List<String> categoriesList = new ArrayList<String>();
					String category;
					SQLiteDatabase db = this.getWritableDatabase();
					Cursor categoriesCursor = db.query(TABLE_NAME, new String[]{MEALS_CATEGORY}, null, null, MEALS_CATEGORY, null, null);
					categoriesCursor.moveToFirst();
					while(!categoriesCursor.isAfterLast()){
						category = categoriesCursor.getString(0);
						
						categoriesList.add(category);
						categoriesCursor.moveToNext();
					}
					categoriesCursor.close();
					return categoriesList;
					
				}
		//get detail categories
				public List<String> getDetailCategory(String detailCategory){
					List<String> detailCategoriesList = new ArrayList<String>();
					String category;
					SQLiteDatabase db = this.getWritableDatabase();
					Cursor detailCategoriesCursor = db.query(TABLE_NAME, new String[]{MEALS_NAME}, MEALS_CATEGORY + "=?", new String[] { detailCategory }, null, null, null, null);
					
					detailCategoriesCursor.moveToFirst();
					while(!detailCategoriesCursor.isAfterLast()){
						category = detailCategoriesCursor.getString(0);
						
						detailCategoriesList.add(category);
						detailCategoriesCursor.moveToNext();
					}
					detailCategoriesCursor.close();
					return detailCategoriesList;
					
				}
		
		//get all numbers
		public List<String> getAllNumbers(){
			List<String> numbersList = new ArrayList<String>();
			String number;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor numbersCursor = db.query(TABLE_NAME, new String[]{MEALS_NUMBER}, null, null, MEALS_NUMBER, null, null);
			numbersCursor.moveToFirst();
			while(!numbersCursor.isAfterLast()){
				number = numbersCursor.getString(0);
				
				numbersList.add(number);
				numbersCursor.moveToNext();
			}
			numbersCursor.close();
			return numbersList;
			
		}
				
		public List<Meals> getAllMeals() {
	        List<Meals> mealsList = new ArrayList<Meals>();
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.query(TABLE_NAME,
	        		new String[] { MEALS_ID, MEALS_NUMBER, MEALS_CATEGORY, MEALS_NAME, MEALS_RECIPE, MEALS_PREPARATION }, null, null, null, null, null);

	            cursor.moveToFirst();
	            while (!cursor.isAfterLast()) {
	            Meals meal = cursorToComment(cursor);
	            mealsList.add(meal);
	              cursor.moveToNext();
	            }
	            // Make sure to close the cursor
	            cursor.close();
	            return mealsList;
	            
	    }
		
		@Override
		public void onCreate(SQLiteDatabase db) {}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
		
		/*public List<Meals> getAllNumbers(){
		List<Meals> numbersList = new ArrayList<Meals>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor numbersCursor = db.query(TABLE_NAME, new String[]{MEALS_NUMBER}, null, null, null, null, null);
		numbersCursor.moveToFirst();
		while(!numbersCursor.isAfterLast()){
			Meals meal = new Meals();
			meal.setNumber(numbersCursor.getString(0));
			numbersList.add(meal);
			numbersCursor.moveToNext();
		}
		numbersCursor.close();
		return numbersList;
		
	}*/

}
