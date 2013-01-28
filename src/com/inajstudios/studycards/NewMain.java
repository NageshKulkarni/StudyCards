package com.inajstudios.studycards;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.fragments.CardFlipFragment;
import com.inajstudios.studycards.fragments.CardFragment;
import com.inajstudios.studycards.fragments.DeckListFragment;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class NewMain extends SherlockFragmentActivity implements DeckListFragment.OnDeckItemSelectedListener {

	private static final String LOG = "NewMain";
	
	CardFlipFragment cardFlipFragment;
	CardFragment cardFragment;
	DeckListFragment deckListFragment;
	ListView lvDecks;
	List<Deck> decks;

	DeckDataSource db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newmain);
		
		db = new DeckDataSource(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
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
		// If this callback does not handle the item click, onPerformDefaultAction
		// of the ActionProvider is invoked. Hence, the provider encapsulates the
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

	
	/*
	 *  Implementing fragment listeners
	 *  Need to override listeners on the fragment to support multiscreen functionality
	 */
	@Override
	public void onDeckItemSelected(Deck deck) {
		Log.w(LOG, LOG + "DeckListItemSelected() called, someone touched an item in the list!");
		
		// Find the fragment view
		deckListFragment = (DeckListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_decklistview);
//		cardFlipFragment = (CardFlipFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cardviewflipper);
		cardFragment = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_card);
		final Deck selectedDeck = deck;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Deck Menu");
		builder.setItems(
				R.array.deck_long_click,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which)
						{
						case 0:
							Log.w(LOG,"You've hit edit!");
							break;
						case 1:
							Log.w(LOG,"You've hit delete!");
							db.open();
							db.DeleteDeck(selectedDeck);
							db.close();
							break;
						case 2:
							Log.w(LOG,"You've hit cancel!");
							dialog.cancel();
							break;
							
						}
						Log.w(LOG, "which: " + which + " | " + "dialog: " + dialog.toString());
					}
				});
		AlertDialog d = builder.create();
		d.show();
		
		deckListFragment.updateDB();
		
		// Is the other fragment in this activity?
		if (cardFlipFragment != null && cardFlipFragment.isInLayout()) {

			Toast.makeText(this, "CardFlipper found!", Toast.LENGTH_SHORT).show();
		} else {

			Toast.makeText(this, "No CardFlipper :(", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onDeckItemLongClick(Deck deck) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "LONG CLICK DETECTED", Toast.LENGTH_LONG).show();
	}
	
}
