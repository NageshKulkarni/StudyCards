package com.inajstudios.studycards;

import com.actionbarsherlock.app.SherlockActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class Boot extends SherlockActivity {

	private static final int TABLET_SIZE = 6;
	private static final String LOG = "Boot";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isTablet();

		startActivity(new Intent(this, NewMain.class));
	}

	private boolean isTablet() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		double inches = Math.sqrt((metrics.widthPixels * metrics.widthPixels) + (metrics.heightPixels * metrics.heightPixels)) / metrics.densityDpi;
		if (inches > TABLET_SIZE) {

			Log.w(LOG, "*** TABLET DETECTED ***");
			return true;
		} else {
			Log.w(LOG, "*** NON-TABLET DETECTED ***");
			return false;
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
