package com.inajstudios.studycards.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.widget.IcsAdapterView;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.adapters.DeckAdapter;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class DeckListFragment extends SherlockFragment implements OnItemClickListener, OnItemLongClickListener {

	private static final String LOG = "DeckListFragment";
	private OnDeckItemSelectedListener deckListener;
	public ListView lvDecks;
	public List<Deck> decks = new ArrayList<Deck>();
	protected DeckDataSource db;

	public DeckListFragment() {
		// TODO Auto-generated constructor stub
	}
	/**********************************************************
	 * Listener interface to be implemented by the activity
	 **********************************************************/
	public interface OnDeckItemSelectedListener {
		public void onDeckItemSelected(Deck deck); 
		public void onDeckItemLongClick(Deck deck);
	}
	
	/**********************************************************
	 *  Fragment Life-cycle call back methods 
	 **********************************************************/

	// Check to see if the Activity has implemented the interface
	@Override
	public void onAttach(Activity activity) {
		Log.w(LOG, "onAttach() CALLED!");
		super.onAttach(activity);
	
		// Need to ensure the Activity implements the Interface
		if (activity instanceof OnDeckItemSelectedListener) {
			deckListener = (OnDeckItemSelectedListener) activity;
			Log.d(LOG, LOG + ": OnDeckItemSelectedListener implemented!");
		} else {
			throw new ClassCastException(activity.toString() + " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.w(LOG, "onCreate() CALLED!");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	// Initialize the DB and bind views
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.w(LOG, "onCreateView() CALLED!");
		
		db = new DeckDataSource(getSherlockActivity());
		
		View view = inflater.inflate(R.layout.fragment_decklistview, container, false);

		lvDecks = (ListView) view.findViewById(R.id.fragment_lvDecks);
		
		DeckAdapter myDeckAdapter = new DeckAdapter(getSherlockActivity(), decks);
		lvDecks.setAdapter(myDeckAdapter);
		lvDecks.setOnItemClickListener(this);
		lvDecks.setOnItemLongClickListener(this);
		
		return view;
	}

	// bind listeners here?
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
	
	// Whenever we revisit this fragment, refresh the ListView
	@Override
	public void onResume() {
		Log.w(LOG, "onResume() CALLED!");
		super.onResume();
		updateDB();
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

	/*
	 * To be implemented by the parent Activity
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getSherlockActivity(), "A deck was clicked!", Toast.LENGTH_SHORT).show();
		final Deck deck = (Deck) parent.getItemAtPosition(position);
		deckListener.onDeckItemSelected(deck);
		updateDB();
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getSherlockActivity(), "A deck was long held!", Toast.LENGTH_SHORT).show();
		final Deck deck = (Deck) parent.getItemAtPosition(position);
		deckListener.onDeckItemLongClick(deck);
		updateDB();
		return true;
	}
	
	/*
	 * Fragment specific methods
	 */
	public void updateDB() {
		db.open();
		decks = db.getAllDecks();
		DeckAdapter myDeckAdapter = new DeckAdapter(getSherlockActivity(), decks);
		lvDecks.setAdapter(myDeckAdapter);
		db.close();
	}


}
