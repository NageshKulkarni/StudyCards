package com.inajstudios.studycards;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class DbDebug extends SherlockActivity implements OnClickListener {
	
	private static final String LOG = "ViewDecks";
	private DeckDataSource db;
	Button bAdd, bDelete, bReset;
	ListView lvDecks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_debug_dbdecks);

		initializeUI();
		db = new DeckDataSource(this);
		updateList();
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub

		menu.add("Add 1").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.add("Add 10").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.add("Add 100").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		Deck deck = new Deck();
		String itemTitle = item.getTitle().toString();

		switch (itemId) {
		case android.R.id.home:
			finish();
			break;
		}

		if (itemTitle == "Add 1") {

			Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
			db.open();
			db.addDeck(deck);
			db.close();
		} else if (itemTitle == "Add 10") {

			Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
			db.open();
			for (int i = 0; i < 10; i++) {
				db.addDeck(deck);
			}
			db.close();
		} else if (itemTitle == "Add 100") {

			Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
			db.open();
			for (int i = 0; i < 100; i++) {
				db.addDeck(deck);
			}
			db.close();
		}

		updateList();
		return true;
	}

	private void initializeUI() {
		bAdd = (Button) findViewById(R.id.viewdecks_bAdd);
		bDelete = (Button) findViewById(R.id.viewdecks_bDelete);
		bReset = (Button) findViewById(R.id.viewdecks_bClearDB);
		lvDecks = (ListView) findViewById(R.id.viewdecks_list);

		bAdd.setOnClickListener(this);
		bDelete.setOnClickListener(this);
		bReset.setOnClickListener(this);
		// lvDecks.setOnItemClickListener(this);
	}

	private void updateList() {
		db.open();

		List<Deck> decks = new ArrayList<Deck>();
		decks = db.getAllDecks();
		lvDecks.setAdapter(new DeckAdapter(this, decks));

		
		db.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.viewdecks_bAdd:
			Log.d(LOG, "bAdd pressed");

			Deck deck = new Deck();

			db.open();
			db.addDeck(deck);
			db.close();
			updateList();
			break;
		case R.id.viewdecks_bDelete:
			Log.d(LOG, "bDelete pressed");
			break;
		case R.id.viewdecks_bClearDB:
			Log.d(LOG, "bClearDB pressed");
			db.open();
			db.clearDB();
			db.close();
			updateList();

			break;
		case R.id.viewdecks_list:
			Log.d(LOG, "Pressed the list");
			break;
		default:
			break;
		}
	}

	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// // TODO Auto-generated method stub
	// Toast.makeText(getApplicationContext(),
	// "Click ListItem Number " + position, Toast.LENGTH_SHORT).show();
	// Deck d = (Deck) parent.getItemAtPosition(position);
	//
	// Intent intent = new Intent(this, ViewDeck.class);
	// Bundle extras = new Bundle();
	// extras.putSerializable("DECK", d);
	//
	// intent.putExtras(extras);
	// startActivity(intent);
	//
	// Log.w(LOG, "GOT DECK "+d.getTitle());
	// }

}
