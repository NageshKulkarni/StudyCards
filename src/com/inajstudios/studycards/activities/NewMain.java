package com.inajstudios.studycards.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.adapters.CardFragmentStatePagerAdapter;
import com.inajstudios.studycards.fragments.CardPagerFragment;
import com.inajstudios.studycards.fragments.DeckListFragment;
import com.inajstudios.studycards.models.Card;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class NewMain extends SherlockFragmentActivity implements DeckListFragment.OnDeckItemSelectedListener
{

	private static final String LOG = "NewMain";

	// Fragments
	CardPagerFragment cardPagerFragment;
	DeckListFragment deckListFragment;

	private List<Card> cards;
	private CardFragmentStatePagerAdapter cardAdapter;

	// Our Singleton Database
	DeckDataSource db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newmain);

		db = new DeckDataSource(this);

		// Debug values
		cards = new ArrayList<Card>();
		cards.add(new Card("What are activities?", "IDK DONT FIRE ME PLZ"));
		cards.add(new Card("What is the Android Manifest?", "Activities, Intent Filters, App Version"));
		cards.add(new Card("What are the four layout densities?", "hdpi mdpi ldpi xhdpi"));
		cards.add(new Card("Do you stink?", "Only on Thursdays"));
		cards.add(new Card("U GON' GET IT", "oh god no not again"));
	}

	@Override
	protected void onResume() {
		super.onResume();

		deckListFragment = (DeckListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_decklistview);
		cardPagerFragment = (CardPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cardpagerview);

		// Need to check layout and do the proper stuff to each fragment
		if (cardPagerFragment == null || !cardPagerFragment.isInLayout()) {
			Log.w(LOG, "cardPagerFragment NOT FOUND!");
		} else {
			Log.w(LOG, "cardPagerFragment FOUND!");
			cardAdapter = new CardFragmentStatePagerAdapter(getSupportFragmentManager(), cards);
			cardPagerFragment.setAdapter(cardAdapter);
		}
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
		}
		return false;
	}

	/*
	 * Implementing fragment listeners Need to override listeners on the
	 * fragment to support multiscreen functionality
	 */
	@Override
	public void onDeckItemSelected(Deck deck) {
		Log.w(LOG, LOG + "onDeckItemLongClick() called, someone long clicked an item in the list!");
		final Deck selectedDeck = deck;

		// Is the other fragment in this activity?
		if (cardPagerFragment != null && cardPagerFragment.isInLayout()) {

		} else {
			startActivity(new Intent(this, ViewCardsActivity.class));
		}
	}

	@Override
	public void onDeckItemLongClick(Deck deck) {
		Log.w(LOG, LOG + "DeckListItemSelected() called, someone touched an item in the list!");
		final Deck selectedDeck = deck;

		// Find the fragment view
		deckListFragment = (DeckListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_decklistview);
		cardPagerFragment = (CardPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cardpagerview);
		// singleCardFragment = (SingleCardFragment)
		// getSupportFragmentManager().
		// cardFragment = (ViewCardFragment)
		// getSupportFragmentManager().findFragmentById(R.id.fragment_card);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Deck Menu");
		builder.setItems(R.array.deck_long_click, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Log.w(LOG, "You've hit edit!");
					break;
				case 1:
					Log.w(LOG, "You've hit delete!");
					db.open();
					db.DeleteDeck(selectedDeck);
					db.close();
					deckListFragment.updateDB();
					break;
				case 2:
					Log.w(LOG, "You've hit cancel!");
					dialog.cancel();
					break;

				}
				Log.w(LOG, "which: " + which + " | " + "dialog: " + dialog.toString());
			}
		});
		AlertDialog d = builder.create();
		d.show();

		deckListFragment.updateDB();

		if (cardPagerFragment != null && cardPagerFragment.isInLayout()) {

		} else {

		}

	}

}
