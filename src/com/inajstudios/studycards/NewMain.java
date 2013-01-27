package com.inajstudios.studycards;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.fragments.CardFlipFragment;
import com.inajstudios.studycards.fragments.DeckListFragment;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class NewMain extends SherlockFragmentActivity implements DeckListFragment.OnItemSelectedListener {

	private static final String LOG = "NewMain";
	
	CardFlipFragment cardFlipFragment;
	ListView lvDecks;
	DeckDataSource db;
	List<Deck> decks;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newmain);
		db = new DeckDataSource(this);
		
		
//		db.open();
//		decks = db.getAllDecks();
//		db.close();
		
		lvDecks = (ListView) findViewById(R.id.fragment_lvDecks);
		updateList();
		
//		lvDecks.setAdapter(new DeckAdapter(decks, this));
//		String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",  
//                "Jupiter", "Saturn", "Uranus", "Neptune"};    
//		lvDecks.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planets));
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		updateList();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onStart();
		updateList();
	}
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub

		// menu.add("New Deck").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
		// | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		// menu.add("DB").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM |
		// MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		// menu.add("Settings").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
		// | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// If this callback does not handle the item click,
		// onPerformDefaultAction
		// of the ActionProvider is invoked. Hence, the provider encapsulates
		// the
		// complete functionality of the menu item.
		Toast.makeText(this, item.getItemId() + " - " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
		switch (item.getItemId()) {
		case R.id.menu_newdeck:
			startActivity(new Intent(this, NewDeck.class));

			break;
		case R.id.menu_dbdebug:

			startActivity(new Intent(this, DbDebug.class));
			break;
		case R.id.menu_settings:

			break;

		case R.id.menu_swipe:

			startActivity(new Intent(this, SwipeExample.class));
			break;
		}
		return false;
	}

	
	/*** Controls ***/
	
	/*
	 *  Update the listview whenever a CRUD operation happens
	 */
	private void updateList() {
		Log.w(LOG, LOG + "updateList() called, list is refreshed!");
		db.open();
		if (db.isEmpty()) {
			Toast.makeText(this, "No decks found, make one!", Toast.LENGTH_LONG).show();
		} else {
			decks = db.getAllDecks();
			lvDecks.setAdapter(new DeckAdapter(decks, this));
//			lv.setOnItemLongClickListener(this);
		}
		db.close();
	}

	
	/*
	 *  Implementing fragment listeners
	 *  Need to override listeners on the fragment to support multiscreen functionality
	 */
	@Override
	public void onDeckItemSelected(Deck deck) {
		Log.w(LOG, LOG + "DeckListItemSelected() called, someone touched an item in the list!");
		cardFlipFragment = (CardFlipFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cardviewflipper);
		if (cardFlipFragment != null && cardFlipFragment.isInLayout()) {
			cardFlipFragment.setText("OHCRAPITWORKED");
			Toast.makeText(this, "No decks found, make one!", Toast.LENGTH_LONG).show();
		}
	}
	
}
