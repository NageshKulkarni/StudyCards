package com.inajstudios.studycards;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class DeckListFragment extends SherlockFragment implements OnItemLongClickListener {
	private static final String LOG = "DeckListFragment";
	private DeckDataSource db;
	List<Deck> decks;
	ListView lvDecks;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_decklist, container, false);

		db = new DeckDataSource(getActivity());
		List<Deck> decks = new ArrayList<Deck>();
		lvDecks = (ListView) getView().findViewById(R.id.fragment_lvDecks);
		updateList();
		return view;
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		updateList();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		// Find out which deck was long clicked
		final Deck deck = (Deck) parent.getItemAtPosition(position);

		// Create's the dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
					db.DeleteDeck(deck);
					db.close();
					updateList();
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

		return false;
	}

	private void updateList() {

		db.open();
		if (db.isEmpty()) {
			Toast.makeText(getActivity(), "No Decks found!", Toast.LENGTH_LONG).show();
		} else {
			decks = db.getAllDecks();
			lvDecks.setAdapter(new DeckAdapter(decks, getActivity()));
			lvDecks.setOnItemLongClickListener(this);
		}
		db.close();
	}

}
