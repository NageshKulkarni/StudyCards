/**
 *  The home screen which is considered the root of all navigation.
 *  
 *  Here users are able to use CRUD on decks and prepare to add cards
 */

package com.inajstudios.studycards;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class MainActivity extends SherlockActivity implements OnItemLongClickListener {

	private static final String LOG = "MainActivity";
	private DeckDataSource db;
	List<Deck> decks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		// *** Database Shenanigans ***

		db = new DeckDataSource(this);
		List<Deck> decks = new ArrayList<Deck>();
		updateList();

		/*
		 * Need a nice listview of sorts
		 */
	}

	@Override
	protected void onStart() {
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

	/*
	 *  Update the listview whenever a CRUD happens
	 */
	private void updateList() {
		// Creating a new RelativeLayout
		RelativeLayout relativeLayout = new RelativeLayout(this);

		// Defining the RelativeLayout layout parameters.

		// Parent ViewHierarchy
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

		// Layout parameters for the textview if no data in DB
		RelativeLayout.LayoutParams tv_layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		tv_layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		// Layout parameters for the listview
		RelativeLayout.LayoutParams lv_layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		db.open();
		if (db.isEmpty()) {

			// Creating a new TextView
			TextView tv = new TextView(this);
			tv.setText("No decks found! Please create a deck!");

			// Setting the parameters on the TextView
			tv.setLayoutParams(tv_layoutParams);

			// Adding the TextView to the RelativeLayout as a child
			relativeLayout.addView(tv);

			// Setting the RelativeLayout as our content view
			setContentView(relativeLayout, rlp);
		} else {
			ListView lv = new ListView(this);
			lv.setLayoutParams(lv_layoutParams);
			decks = db.getAllDecks();
			lv.setAdapter(new DeckAdapter(decks, this));
			lv.setOnItemLongClickListener(this);

			// Adding the TextView to the RelativeLayout as a child
			relativeLayout.addView(lv);

			// Setting the RelativeLayout as our content view
			setContentView(relativeLayout, rlp);
		}
		db.close();
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

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Find out which deck was long clicked
		final Deck deck = (Deck) parent.getItemAtPosition(position);
		
		// Create's the dialog
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
							db.DeleteDeck(deck);
							db.close();
							updateList();
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
		
		return false;
	}
}
