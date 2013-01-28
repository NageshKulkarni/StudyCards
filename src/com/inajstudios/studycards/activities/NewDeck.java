package com.inajstudios.studycards.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.inajstudios.studycards.R;
import com.inajstudios.studycards.R.id;
import com.inajstudios.studycards.R.layout;
import com.inajstudios.studycards.adapters.CategorySpinnerAdapter;
import com.inajstudios.studycards.models.Category;
import com.inajstudios.studycards.models.Deck;
import com.inajstudios.studycards.sqlite.DeckDataSource;

public class NewDeck extends SherlockActivity implements OnClickListener {

	private static final String LOG = "NewDeck";
	private List<Category> categories;
	EditText etTitle, etDescription;
	CheckBox cbPrivate;
	Button bSave;
	Spinner spCategory;

	private Deck deck;
	private DeckDataSource db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createnewdeck);
		initializeUI();
		// loadCategories();
		db = new DeckDataSource(this);
		deck = new Deck();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initializeUI() {
		bSave = (Button) findViewById(R.id.newdeck_bSave);
		etTitle = (EditText) findViewById(R.id.newdeck_etTitle);
		etDescription = (EditText) findViewById(R.id.newdeck_etDescription);
		cbPrivate = (CheckBox) findViewById(R.id.newdeck_cbPrivate);
		spCategory = (Spinner) findViewById(R.id.newdeck_spCategory);

		bSave.setOnClickListener(this);
		cbPrivate.setOnClickListener(this);
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

	// private void loadCategories()
	// {
	// categories = new ArrayList<Category>();
	// for(int i = 0; i < CATEGORIES.length; i++)
	// {
	// categories.add(CATEGORIES[i]);
	// Log.v(LOG, "category: " + categories.get(i).getTitle());
	// }
	// spCategory.setAdapter(new CategorySpinnerAdapter(categories, this));
	// }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.newdeck_bSave:

			deck.setTitle(etTitle.getText().toString());
			deck.setDescription(etDescription.getText().toString());

			db.open();
			db.addDeck(deck);
			db.close();

			finish();
			// startActivity(new Intent(this, ViewDecks.class));
			break;

		case R.id.newdeck_cbPrivate:
			if (cbPrivate.isChecked()) {
				deck.setIsPrivate(1);
			} else {
				deck.setIsPrivate(0);
			}
			Log.d(LOG, "status is " + deck.getIsPrivate());
			break;
		}
	}

}
