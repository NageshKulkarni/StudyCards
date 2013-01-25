package com.inajstudios.studycards.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inajstudios.studycards.models.Deck;

public class DeckDataSource {

	private static final String LOG = "DeckDataSource";

	// The database itself to query against with methods
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;

	public DeckDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() throws SQLException {
		dbHelper.close();
	}

	/*
	 * Creates a new deck and inserts it into the DB
	 */
	public void createDeck(Deck deck) {
		// Preparing data to insert
		ContentValues cv = new ContentValues();
		cv.put(MySQLiteHelper.DECKS_COLUMN_UID, deck.getUid());
		cv.put(MySQLiteHelper.DECKS_COLUMN_TITLE, deck.getTitle());
		cv.put(MySQLiteHelper.DECKS_COLUMN_DESCRIPTION, deck.getDescription());
		cv.put(MySQLiteHelper.DECKS_COLUMN_CATEGORY, deck.getCategory());
		cv.put(MySQLiteHelper.DECKS_COLUMN_ISPRIVATE, deck.getIsPrivate());
		long insertID = db.insert(MySQLiteHelper.TABLE_DECKS, null, cv);

		if (insertID == -1) {
			Log.e(LOG, "Error creating new deck");
		}

		Log.w(LOG, "Added DID:" + deck.getDid() + " Title:" + deck.getTitle());
	}

	/*
	 * Creates a new deck and inserts it into the DB
	 */
	public void DeleteDeck(Deck deck) {
		// Preparing data to insert
		long deleteID = db.delete(MySQLiteHelper.TABLE_DECKS, MySQLiteHelper.DECKS_COLUMN_DID + " = " + deck.getDid(), null);

		if (deleteID == -1) {
			Log.e(LOG, "Error creating new deck");
		}

		Log.w(LOG, "Deleted DID:" + deck.getDid() + " Title:" + deck.getTitle());
	}

	/*
	 * Gets all Decks and returns it in a list
	 */
	public List<Deck> getAllDecks() {
		List<Deck> decks = new ArrayList<Deck>();

		Cursor cursor = db.query(MySQLiteHelper.TABLE_DECKS, null, null, null,
				null, null, null);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Deck deck = cursorToDeck(cursor);
			decks.add(deck);
			Log.v(LOG, cursor.getPosition() + " | " + deck.getTitle());
		}
		
		cursor.close();
		return decks;
	}

	/*
	 * Turns a cursor into a full deck
	 */
	private Deck cursorToDeck(Cursor cursor) {
		Deck deck = new Deck();
				
		deck.setDid(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.DECKS_COLUMN_DID)));
		deck.setUid(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.DECKS_COLUMN_UID)));
		deck.setTitle(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.DECKS_COLUMN_TITLE)));
		deck.setDescription(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.DECKS_COLUMN_DESCRIPTION)));
		deck.setCategory(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.DECKS_COLUMN_CATEGORY)));
		deck.setIsPrivate(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.DECKS_COLUMN_ISPRIVATE)));
		
		return deck;
	}
	
	public boolean isEmpty()
	{
		Cursor cursor = db.query(MySQLiteHelper.TABLE_DECKS, null, null, null,
				null, null, null);
		
		if(cursor != null)
		{
			if (cursor.getCount() == 0)
			{
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/*
	 * Clears the DB
	 */
	public void clearDB()
	{
		db.delete(MySQLiteHelper.TABLE_DECKS, null, null);
	}
}
