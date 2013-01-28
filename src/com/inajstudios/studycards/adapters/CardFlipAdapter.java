package com.inajstudios.studycards.adapters;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.inajstudios.studycards.fragments.CardFragment;
import com.inajstudios.studycards.models.Card;

public class CardFlipAdapter extends FragmentStatePagerAdapter {

		List<Card> cards = new ArrayList<Card>();
		private FragmentManager fm;

		public CardFlipAdapter(FragmentManager fm, List<Card> c) {
			super(fm);
			this.fm = fm;
			this.cards = c;
		}

		@Override
		public int getCount() {
			return cards.size() + 1;
		}

		@Override
		public Fragment getItem(int position) {
			if (position < cards.size()) {
				CardFragment cf = new CardFragment(cards.get(position));
				cf.setRetainInstance(true);
				return cf;
			} else {
				return new CardFragment();
			}
		}

		public void addedNewCard(Card newCard) {
			cards.add(newCard);
			
			fm.beginTransaction().remove(this.getItem(cards.size())).commit();
			//fm.beginTransaction().add(new CardFragment(newCard), "IDK").commit();
			notifyDataSetChanged();
		}

	}