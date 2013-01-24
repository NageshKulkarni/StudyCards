package com.inajstudios.studycards.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inajstudios.studycards.R;
import com.inajstudios.studycards.models.Category;

public class CategorySpinnerAdapter extends BaseAdapter {

	private List<Category> categories;
	Context context;

	public CategorySpinnerAdapter(List<Category> categories, Context context) {
		this.categories = categories;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.adapter_category, null);
		}
		
		ImageView image = (ImageView) v.findViewById(R.id.thumbnail_image);
		TextView tvTitle = (TextView) v.findViewById(R.id.title);
		TextView tvDescription = (TextView) v.findViewById(R.id.description);
		
		Category category = (Category) categories.get(position);
		
		image.setImageResource(category.getImageID());
		tvTitle.setText(category.getTitle());
		tvDescription.setText(category.getDescription());
		
		return v;

//		Deck deck = (Deck) decks.get(position);
//		image.setImageResource(R.drawable.matrix_thumb);
//		tvTitle.setText(deck.getTitle());
//		tvDescription.setText("Description: " + deck.getDescription());
//		tvCategory.setText(deck.getCategory());
//		tvIsPrivate.setText(deck.getIsPrivate() + " lol");

		
	}
}
