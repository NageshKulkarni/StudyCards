package com.inajstudios.studycards.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inajstudios.studycards.models.Card;
public class CardDataSource {

	private static final String LOG = "CardDataSource";

	// The database itself to query against with methods
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;

	public CardDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		Log.w(LOG, LOG + ": OPEN() called fine");
		db = dbHelper.getWritableDatabase();
		Log.w(LOG, LOG + ": OPEN() ended fine");
	}

	public void close() throws SQLException {
		dbHelper.close();
	}

	/*
	 * Creates a new card and inserts it into the DB
	 */

	public void createCard(Card card) {
		// Preparing data to insert
		ContentValues cv = new ContentValues();
		cv.put(MySQLiteHelper.CARDS_COLUMN_DID, card.getDid());
		cv.put(MySQLiteHelper.CARDS_COLUMN_CID, card.getCid());
		cv.put(MySQLiteHelper.CARDS_COLUMN_FRONT, card.getFront());
		cv.put(MySQLiteHelper.CARDS_COLUMN_BACK, card.getBack());
		long insertID = db.insert(MySQLiteHelper.TABLE_CARDS, null, cv);

		if (insertID == -1) {
			Log.e(LOG, "Error creating new card");
		}

		Log.w(LOG, "Added CARD DID: " + card.getDid() + ", CID: " + card.getCid() + ", Front: " + card.getFront() + " Back: " + card.getBack());
	}

	/*
	 * Creates a new card and inserts it into the DB
	 */

	public void DeleteCard(Card card) {
		// Preparing data to insert
		long deleteID = db.delete(MySQLiteHelper.TABLE_CARDS, MySQLiteHelper.CARDS_COLUMN_CID + " = " + card.getCid(), null);

		if (deleteID == -1) {
			Log.e(LOG, "Error deleting new card");
		}

		Log.w(LOG, "Deleting CARD DID: " + card.getDid() + ", CID: " + card.getCid() + ", Front: " + card.getFront() + " Back: " + card.getBack());
	}

	/*
	 * Gets all cards and returns it in a list
	 */	
	public List<Card> getAllCards() {
		List<Card> cards = new ArrayList<Card>();

		Cursor cursor = db.query(MySQLiteHelper.TABLE_CARDS, null, null, null, null, null, null);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Card card = cursorToCard(cursor);
			cards.add(card);
			Log.v(LOG, cursor.getPosition() + " | " + card.getCid());
		}

		cursor.close();
		return cards;
	}

	/*
	 * Turns a cursor into a full card
	 */
	private Card cursorToCard(Cursor cursor) {
		Card card = new Card();

		card.setDid(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.CARDS_COLUMN_DID)));
		card.setCid(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.CARDS_COLUMN_CID)));
		card.setFront(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.CARDS_COLUMN_FRONT)));
		card.setBack(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.CARDS_COLUMN_BACK)));

		return card;
	}

	public boolean isEmpty() {
		Cursor cursor = db.query(MySQLiteHelper.TABLE_CARDS, null, null, null, null, null, null);

		if (cursor != null) {
			if (cursor.getCount() == 0) {
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
	public void clearDB() {
		db.delete(MySQLiteHelper.TABLE_CARDS, null, null);
	}
}
