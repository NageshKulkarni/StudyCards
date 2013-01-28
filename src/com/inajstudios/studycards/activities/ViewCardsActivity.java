package com.inajstudios.studycards.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.adapters.CardFragmentStatePagerAdapter;
import com.inajstudios.studycards.fragments.CardPagerFragment;
import com.inajstudios.studycards.models.Card;

public class ViewCardsActivity extends SherlockFragmentActivity{

	ViewPager vpCards;
	List<Card> cards;;
	FragmentManager fragmentManager = getSupportFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

	CardPagerFragment cardPagerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewcards);
		
		// Actionbar stuff
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// Debug values
		cards = new ArrayList<Card>();
		cards.add(new Card("DEBUG STATEMENT TEST1", "DEBUG STATEMENT TEST2"));
		cards.add(new Card("What is the Android Manifest?", "Activities, Services, Broadcast Receivers, Content Providers"));
		cards.add(new Card("What are the four layout densities?", "hdpi mdpi ldpi xhdpi"));
		cards.add(new Card("WWWWWWWWWWWWWWLONGSTRINGWWWWWWWWWWWW", "a"));

	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();

		cardPagerFragment = (CardPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cardpagerview);
		CardFragmentStatePagerAdapter cardAdapter = new CardFragmentStatePagerAdapter(getSupportFragmentManager(), cards);
		cardPagerFragment.setAdapter(cardAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}
}
