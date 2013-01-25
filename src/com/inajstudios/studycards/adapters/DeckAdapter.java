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
import com.inajstudios.studycards.models.Deck;



public class DeckAdapter extends BaseAdapter {

	private List<Deck> decks;
	Context context;

	public DeckAdapter(List<Deck> decks, Context c) {
		this.decks = decks;
		context = c;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return decks.size();
	}

	public Deck getItem(int position) {
		// TODO Auto-generated method stub
		return decks.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.adapter_deck, null);
		}

		ImageView image = (ImageView) v.findViewById(R.id.icon);
		TextView tvTitle = (TextView) v.findViewById(R.id.title);
		TextView tvDescription = (TextView) v.findViewById(R.id.description);
		TextView tvCategory = (TextView) v.findViewById(R.id.category);
		TextView tvIsPrivate = (TextView) v.findViewById(R.id.isprivate);

		Deck deck = (Deck) decks.get(position);
		image.setImageResource(R.drawable.category_debug_icon);
		tvTitle.setText(deck.getTitle());
		tvDescription.setText("Description: " + deck.getDescription());
		tvCategory.setText(deck.getCategory());
		tvIsPrivate.setText("DID" + deck.getDid());

		return v;
	}
}