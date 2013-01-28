package com.inajstudios.studycards.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.adapters.CardFragmentStatePagerAdapter;
import com.inajstudios.studycards.models.Card;
import com.inajstudios.studycards.models.Deck;

public class CardPagerFragment extends SherlockFragment {

	private static final String LOG = "CardPagerFragment";

	public Deck currentDeck;
	public List<Card> cards = new ArrayList<Card>();
	public ViewPager vpCards;

	/**********************************************************
	 * Fragment Life-cycle call back methods
	 **********************************************************/

	// Initialize the DB and bind views
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cardviewpager, container, false);
		
		vpCards = (ViewPager) view.findViewById(R.id.vpCards);
		setRetainInstance(true);

		return view;
	}

	public void setAdapter(CardFragmentStatePagerAdapter cardAdapter) {
		vpCards.setAdapter(cardAdapter);
	}

}
