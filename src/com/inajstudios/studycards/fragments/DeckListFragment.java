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
	public ListView lvDecks;
	public List<Deck> decks = new ArrayList<Deck>();
	private OnDeckItemSelectedListener deckListener;
	
	protected DeckDataSource db;

	/*** Fragment Life-cycle call back methods ***/

	// Fragment gets attached to the activity
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	
		// Need to ensure the Activity implements the Interface
		if (activity instanceof OnDeckItemSelectedListener) {
			deckListener = (OnDeckItemSelectedListener) activity;
			Log.d(LOG, LOG + ": OnDeckItemSelectedListener implemented!");
		} else {
			throw new ClassCastException(activity.toString() + " must implemenet MyListFragment.OnItemSelectedListener");
		}
		Log.w(LOG, "onAttach() CALLED!");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(LOG, "onCreate() CALLED!");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		db = new DeckDataSource(getSherlockActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.w(LOG, "onCreateView() CALLED!");
		View view = inflater.inflate(R.layout.fragment_decklistview, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.w(LOG, "onActivityCreated() CALLED!");
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.w(LOG, "onStart() CALLED!");
		// TODO Auto-generated method stub
		super.onStart();
	}

	/*** QUICK HACK FOR MYSQLITE NULLPOINTEREXCEPTIONS? FIX THIS! ***/
	@Override
	public void onResume() {
		Log.w(LOG, "onResume() CALLED!");
		super.onResume();


		//updateDB();
		
		lvDecks = (ListView) getSherlockActivity().findViewById(R.id.fragment_lvDecks);
		DeckAdapter myDeckAdapter = new DeckAdapter(getSherlockActivity(), decks);
		lvDecks.setAdapter(myDeckAdapter);
		lvDecks.setOnItemClickListener(this);
	}

	@Override
	public void onPause() {
		Log.w(LOG, "onPause() CALLED!");
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.w(LOG, "onStop() CALLED!");
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.w(LOG, "onDestroyView() CALLED!");
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.w(LOG, "onDestroy() CALLED!");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.w(LOG, "onDetach() CALLED!");
		// TODO Auto-generated method stub
		super.onDetach();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	/************************************************************************************************************/
	
	
	
	
	/*** Interfacing UI controls ***/
	public interface OnDeckItemSelectedListener {
		public void onDeckItemSelected(Deck deck); 
		public void onDeckItemLongClick(Deck deck);
	}

	/*** Action Handlers! ***/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getSherlockActivity(), "A deck was clicked!", Toast.LENGTH_SHORT).show();
		final Deck deck = (Deck) parent.getItemAtPosition(position);
		deckListener.onDeckItemSelected(deck);
	}

	/*** Public methods to be called from the outside ***/
	public void updateDB() {
		db.open();
		decks = db.getAllDecks();
		DeckAdapter myDeckAdapter = new DeckAdapter(getSherlockActivity(), decks);
		lvDecks.setAdapter(myDeckAdapter);
		db.close();
	}
}
