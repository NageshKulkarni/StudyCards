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

	private Card card;
	private ViewAnimator va;

	public SingleCardFragment() {
	}

	public SingleCardFragment(Card c) {
		card = c;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cardfragment, container, false);
		TextView tvFront = (TextView) view.findViewById(R.id.front);
		TextView tvBack = (TextView) view.findViewById(R.id.back);
		tvFront.setText(card.getFront());
		tvBack.setText(card.getBack());

		va = (ViewAnimator) view.findViewById(R.id.viewanimator);
		
		va.setOnClickListener(this);
		setRetainInstance(true);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AnimationFactory.flipTransition(va, FlipDirection.LEFT_RIGHT);
	}
}
