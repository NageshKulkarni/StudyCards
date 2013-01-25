package com.inajstudios.studycards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SideFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view;
		
		view = inflater.inflate(R.layout.activitytablet_main, container, false);
		
		return view;
	}
	
}
