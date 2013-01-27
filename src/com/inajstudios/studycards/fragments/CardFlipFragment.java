package com.inajstudios.studycards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.SherlockFragment;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.R.id;
import com.inajstudios.studycards.R.layout;

public class CardFlipFragment extends SherlockFragment {

	/*** UI Components ***/
	ViewFlipper vfCards;
	TextView tvTest;

	/*** Life-cycle callback methods ***/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cardviewflipper, container, false);
		vfCards = (ViewFlipper) view.findViewById(R.id.fragment_vfCards);
		tvTest = (TextView) view.findViewById(R.id.fragment_tvTest);
		return view;
	}

	public void setText(String s) {
		tvTest.setText(s);
	}

}
