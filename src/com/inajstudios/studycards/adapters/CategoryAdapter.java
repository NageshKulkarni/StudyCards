package com.inajstudios.studycards.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.inajstudios.studycards.models.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {

	public CategoryAdapter(Context context, int textViewResourceId,
			Category[] objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
