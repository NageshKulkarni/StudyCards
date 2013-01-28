package com.inajstudios.studycards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.actionbarsherlock.app.SherlockFragment;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.models.Card;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

public class SingleCardFragment extends SherlockFragment implements OnClickListener {

	String pagenumber;
	private Card card;
	private ViewAnimator va;
	TextView tvPageNumber, tvFront, tvBack;

	public SingleCardFragment() {
	}

	public SingleCardFragment(Card c) {
		card = c;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cardfragment, container, false);
		tvPageNumber = (TextView) view.findViewById(R.id.pagenumbers);
		if (card != null) {
			tvFront = (TextView) view.findViewById(R.id.front);
			tvBack = (TextView) view.findViewById(R.id.back);
			tvPageNumber = (TextView) view.findViewById(R.id.pagenumbers);
			tvFront.setText(card.getFront());
			tvBack.setText(card.getBack());
			tvPageNumber.setText(pagenumber);
		}

		va = (ViewAnimator) view.findViewById(R.id.viewanimator);

		va.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AnimationFactory.flipTransition(va, FlipDirection.LEFT_RIGHT);
	}
	
	public void setPageNumber(String s)
	{
		pagenumber = s;
	}
}
