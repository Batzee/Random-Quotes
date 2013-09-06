package com.batz.quotes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "quotes";
	private static final String QUOTES_TABLE_NAME = "quotes";
	private static final String KEY_ID = "id";
	private static final String KEY_TEXT = "quote";
	private static final String KEY_ISREAD = "isread";
	private static final String QUOTES_TABLE_CREATE = "CREATE TABLE " + QUOTES_TABLE_NAME + " (" +
													KEY_ID + " INTEGER PRIMARY KEY, " +
													KEY_TEXT + " TEXT," +
													KEY_ISREAD + " INTEGER DEFAULT 0" + ");";	
	//Quotes Array
	private static final String[] RANDOM_QUOTES = {"We are what we repeatedly do. Excellence, therefore, is not an act but a habit."
													,"Take calculated risks. That is quite different from being rash."													
													,"If you do not hope, you will not find what is beyond your hopes."
													,"The only way of finding the limits of the possible is by going beyond them into the impossible."
													,"Without inspiration the best powers of the mind remain dormant. There is a fuel in us which needs to be ignited with sparks."													
													,"He who hesitates is lost."
													,"Nothing great was ever achieved without enthusiasm."													
													,"Knowing is not enough, we must apply. Willing is not enough, we must do."
													,"We are still masters of our fate. We are still captains of our souls."
													,"Believe with all of your heart that you will do what you were made to do."
													,"Two things are infinite the universe and human stupidity; and Im not sure about the universe."
													,"Be yourself everyone else is already taken."
													,"You know you are in love when you cant fall asleep because reality is finally better than your dreams."
													,"You only live once, but if you do it right, once is enough."
													,"Be the change that you wish to see in the world."
													,"A room without books is like a body without a soul."
													,"In three words I can sum up everything I have learned about life it goes on."
													,"If you tell the truth, you dont have to remember anything."
													,"A friend is someone who knows all about you and still loves you."
													,"Insanity is doing the same thing, over and over again, but expecting different results."
													,"Always forgive your enemies nothing annoys them so much."
													,"Live as if you were to die tomorrow. Learn as if you were to live forever."
													,"I have learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel."
													,"Darkness cannot drive out darkness: only light can do that. Hate cannot drive out hate: only love can do that."
													,"Without music, life would be a mistake."
													,"To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment."
													,"It is better to be hated for what you are than to be loved for what you are not."
													,"Imperfection is beauty, madness is genius and it is better to be absolutely ridiculous than absolutely boring."
													,"Life is what happens to you while you are busy making other plans."
													,"A woman is like a tea bag, you never know how strong it is until it is in hot water."
													,"If you dont stand for something you will fall for anything."};
		
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(QUOTES_TABLE_CREATE);
		initializeQuotes(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + QUOTES_TABLE_NAME);
		onCreate(db);		
	}
	
	private void initializeQuotes(SQLiteDatabase db){
		for(String q : RANDOM_QUOTES)
			db.execSQL("insert into " + QUOTES_TABLE_NAME + "(" + KEY_TEXT + ") values('"+q+"')");        
	}
		
	public String getRandomQuote(){
		SQLiteDatabase db = this.getWritableDatabase();
	    String selectQuery = "SELECT  * FROM " + QUOTES_TABLE_NAME + " WHERE " + KEY_ISREAD + " = 0 ORDER BY RANDOM() LIMIT 1";
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    
	    cursor.moveToFirst();
	    return cursor.getString(1);
	
//	    if (cursor != null)
//	    	if(cursor.moveToFirst()){
//	    		db.execSQL("update " + QUOTES_TABLE_NAME + " set " + KEY_ISREAD + " = 1 where "+ KEY_ID +" = "+ cursor.getString(0));
//	    		return cursor.getString(1);
//	    	}
//	    return "Sorry, you ran out of quotes :D";
	    
	}
}