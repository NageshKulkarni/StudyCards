package com.inajstudios.studycards.adapters;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.inajstudios.studycards.fragments.SingleCardFragment;
import com.inajstudios.studycards.models.Card;

public class CardFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

	List<Card> cards = new ArrayList<Card>();
	private FragmentManager fm;

	public CardFragmentStatePagerAdapter(FragmentManager fm, List<Card> c) {
		super(fm);
		this.fm = fm;
		this.cards = c;
	}

	@Override
	public int getCount() {
		return cards.size();
	}

	@Override
	public Fragment getItem(int position) {
		if (position < cards.size()) {
			SingleCardFragment cf = new SingleCardFragment(cards.get(position));
			cf.setPageNumber(position+1 + "/" + cards.size());
			return cf;
		} else {
			return null;
		}
	}

	public void addedNewCard(Card newCard) {
		cards.add(newCard);

		fm.beginTransaction().remove(this.getItem(cards.size())).commit();
		// fm.beginTransaction().add(new CardFragment(newCard), "IDK").commit();
		notifyDataSetChanged();
	}
}