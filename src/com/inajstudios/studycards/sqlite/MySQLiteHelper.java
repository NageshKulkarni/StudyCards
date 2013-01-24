package com.inajstudios.studycards.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String LOG = "DeckDataSource";

	// DB name to be stored in internal memory
	private static final String DATABASE_NAME = "studycards.db";
	private static final int DATABASE_VERSION = 4;

	// Table for "DECKS"
	public static final String TABLE_DECKS = "decks";
	public static final String DECKS_COLUMN_DID = "DID";
	public static final String DECKS_COLUMN_UID = "UID";
	public static final String DECKS_COLUMN_TITLE = "title";
	public static final String DECKS_COLUMN_DESCRIPTION = "description";
	public static final String DECKS_COLUMN_CATEGORY = "category";
	public static final String DECKS_COLUMN_ISPRIVATE = "private";

	// Table for "CARDS"
	public static final String TABLE_CARDS = "cards";
	public static final String CARDS_COLUMN_CID = "CID";
	public static final String CARDS_COLUMN_DID = "DID";
	public static final String CARDS_COLUMN_FRONT = "front";
	public static final String CARDS_COLUMN_BACK = "back ";

	private static final String CREATE_TABLE_DECKS = "CREATE TABLE "
			+ TABLE_DECKS + " (" + DECKS_COLUMN_DID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DECKS_COLUMN_UID
			+ " INTEGER," + DECKS_COLUMN_TITLE + " TEXT,"
			+ DECKS_COLUMN_DESCRIPTION + " TEXT," + DECKS_COLUMN_CATEGORY
			+ " TEXT," + DECKS_COLUMN_ISPRIVATE + " INT);";

	private static final String CREATE_TABLE_CARDS = "CREATE TABLE "
			+ TABLE_CARDS + " (" + CARDS_COLUMN_CID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + CARDS_COLUMN_DID
			+ " INTEGER NOT NULL, " + CARDS_COLUMN_FRONT + " TEXT NOT NULL,"
			+ CARDS_COLUMN_BACK + " TEXT NOT NULL);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_DECKS);
		db.execSQL(CREATE_TABLE_CARDS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
		onCreate(db);
	}

}
