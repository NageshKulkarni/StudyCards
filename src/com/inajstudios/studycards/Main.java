package com.inajstudios.studycards;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class Main extends SherlockFragmentActivity implements OnItemLongClickListener
{
	private static final String LOG = "MainActivity";
	private DeckDataSource db;
	List<Deck> decks;
	ListView lvDecks;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		
		db = new DeckDataSource(this);
		List<Deck> decks = new ArrayList<Deck>();
		
		lvDecks = (ListView) findViewById(R.id.fragment_lvDecks);
		
		updateList();
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


	private void updateList() {

		db.open();
		if (db.isEmpty()) {
			Toast.makeText(this, "No Decks found!", Toast.LENGTH_LONG).show();
		} else {
			decks = db.getAllDecks();
			lvDecks.setAdapter(new DeckAdapter(decks, this));
			lvDecks.setOnItemLongClickListener(this);
		}
		db.close();
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
