package com.inajstudios.studycards.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class DeckListFragment extends SherlockFragment {

	private static final String LOG = "DeckListFragment";
	private DeckDataSource db;
	ListView lvDecks;
	List<Deck> decks;

	private OnItemSelectedListener selectListener;

	/*** Fragment Life-cycle call back methods ***/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.w(LOG, LOG + ": onCreateView() Called");
		View view = inflater.inflate(R.layout.fragment_decklistview, container, false);

		Log.d(LOG, LOG + ": Inflated DeckListFragment");
//		lvDecks = (ListView) view.findViewById(R.id.fragment_lvDecks);
//		db = new DeckDataSource(getSherlockActivity());
//		db.open();
//		decks = db.getAllDecks();
//		db.close();
//		
//		lvDecks.setAdapter(new DeckAdapter(decks, getActivity()));
		
//		lvDecks.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				lvDecksClicked();
//			}
//		});
		
		lvDecks.setOnItemSelectedListener(new ListView.OnItemSelectedListener()
		{
			public void onItemSelected(android.widget.AdapterView<?> a, View v, int i, long l) {
				Toast.makeText(getSherlockActivity(), "DeckListFragment onItemSelected!", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> a) {
				// TODO Auto-generated method stub
				
			};
		});
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			selectListener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString() + " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//updateList();
	}

	/*** Other Methods ***/
	/*
	 * Update the listview whenever a CRUD happens
	 */
	private void updateList() {
		db.open();
		if (db.isEmpty()) {
			Toast.makeText(getSherlockActivity(), "No decks found!", Toast.LENGTH_LONG).show();
		} else {
			decks = db.getAllDecks();
			lvDecks.setAdapter(new DeckAdapter(decks, getSherlockActivity()));
		}
		db.close();
	}

	private void lvDecksClicked() {
		Deck d = new Deck();
		selectListener.onDeckItemSelected(d);
	}

	/*** Interfacing UI controls ***/
	public interface OnItemSelectedListener {
		public void onDeckItemSelected(Deck deck);
	}
}
