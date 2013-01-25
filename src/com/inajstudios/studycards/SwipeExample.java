package com.inajstudios.studycards;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class SwipeExample extends Activity implements OnGestureListener, OnDoubleTapListener {

	private GestureDetector detector;
	ViewFlipper flippy;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewcards);
		flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
		detector = new GestureDetector(this, this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		this.detector.onTouchEvent(me);
		return super.onTouchEvent(me);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.d("---onDown----", e.toString());
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		Log.d("---onFling---", e1.toString() + e2.toString());
		Toast.makeText(getApplicationContext(), "FLING!", Toast.LENGTH_SHORT).show();
		flippy.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));
        flippy.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));
		flippy.showNext();
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		Log.d("---onLongPress---", e.toString());
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		Log.d("---onScroll---", e1.toString() + e2.toString());
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.d("---onShowPress---", e.toString());
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.d("---onSingleTapUp---", e.toString());
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		Log.d("---onDoubleTap---", e.toString());
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		Log.d("---onDoubleTapEvent---", e.toString());
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		Log.d("---onSingleTapConfirmed---", e.toString());
		return false;
	}

}