package com.inajstudios.studycards.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class DeckListFragment extends SherlockFragment implements OnItemClickListener {

	private static final String LOG = "DeckListFragment";
	private DeckDataSource db;
	ListView lvDecks;
	List<Deck> decks = new ArrayList<Deck>();
	private OnDeckItemSelectedListener deckListener;

	/*** Fragment Life-cycle call back methods ***/

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnDeckItemSelectedListener) {
			deckListener = (OnDeckItemSelectedListener) activity;
			Log.d(LOG, LOG + ": OnDeckItemSelectedListener implemented!");
		} else {
			throw new ClassCastException(activity.toString() + " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_decklistview, container, false);
		Log.d(LOG, LOG + ": onCreateView(), Inflated DeckListFragment");

		db = new DeckDataSource(getActivity());
		db.open();
		decks = db.getAllDecks();
		db.close();
		Log.d(LOG, LOG + ": size of decks - " + decks.size());
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		updateDB();
	}

	/*** QUICK HACK FOR MYSQLITE NULLPOINTEREXCEPTIONS? FIX THIS! ***/
	@Override
	public void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();

		lvDecks = (ListView) getSherlockActivity().findViewById(R.id.fragment_lvDecks);
		DeckAdapter myDeckAdapter = new DeckAdapter(decks, getSherlockActivity());
		lvDecks.setAdapter(myDeckAdapter);
		lvDecks.setOnItemClickListener(this);
	}

	/*** Interfacing UI controls ***/
	public interface OnDeckItemSelectedListener 
	{
		public void onDeckItemSelected(Deck deck);
	}

	/*** Action Handlers! ***/
	@Override
	public void onItemClick (AdapterView<?> parent, View view, int position, long id) 
	{
		Toast.makeText(getSherlockActivity(), "A deck was clicked!", Toast.LENGTH_SHORT).show();
		final Deck deck = (Deck) parent.getItemAtPosition(position);
		deckListener.onDeckItemSelected(deck);
	}
	
	/*** DB ***/
	public void updateDB()
	{
		db.open();
		decks = db.getAllDecks();
		DeckAdapter myDeckAdapter = new DeckAdapter(decks, getSherlockActivity());
		lvDecks.setAdapter(myDeckAdapter);
		db.close();
	}
}
