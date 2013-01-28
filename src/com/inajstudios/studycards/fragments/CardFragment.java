package com.inajstudios.studycards.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.inajstudios.studycards.R;
import com.inajstudios.studycards.models.Card;

public class CardFragment extends Fragment implements OnClickListener {

	EditText etFront, etBack;
	Button bSave;
	private Card card;

	private newCardListener listener;

	public CardFragment() {
		card = new Card();
	}

	public CardFragment(Card c) {
		// TODO Auto-generated constructor stub
		card.setFront(c.getFront());
		card.setBack(c.getBack());
	}

	/*** LIFE CYCLE SHENANIGANS ***/
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof newCardListener) {
			listener = (newCardListener) activity;
		} else {
			throw new ClassCastException(activity.toString() + " must implemenet NewCardFragment.newCardListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.newcard, container, false);
		etFront = (EditText) view.findViewById(R.id.newfront);
		etBack = (EditText) view.findViewById(R.id.newback);
		bSave = (Button) view.findViewById(R.id.savecard);

		bSave.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		card.setFront(etFront.getText().toString());
		card.setBack(etBack.getText().toString());
		
		listener.newCardOnClickSave(card);
	}

	public interface newCardListener {
		public void newCardOnClickSave(Card c);
	}

}
