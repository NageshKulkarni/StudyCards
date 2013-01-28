package com.inajstudios.studycards.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inajstudios.studycards.R;
import com.inajstudios.studycards.models.Deck;

public class DeckAdapter extends ArrayAdapter<Deck> {

	Context context;
	List<Deck> decks;
	
	public DeckAdapter(Context c, List<Deck> d) {
		super(c, R.layout.adapter_deck, d);
		
		this.context = c;
		this.decks = d;
	}
	
	public int getCount() {
		return decks.size();
	}

	public Deck getItem(int position) {
		return decks.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent)
//	{
//		View v = convertView;
//		if (v == null) {
//			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			v = vi.inflate(R.layout.adapter_deck, null);
//		}
//
//		ImageView image = (ImageView) v.findViewById(R.id.icon);
//		TextView tvTitle = (TextView) v.findViewById(R.id.title);
//		TextView tvDescription = (TextView) v.findViewById(R.id.description);
//		TextView tvCategory = (TextView) v.findViewById(R.id.category);
//		TextView tvIsPrivate = (TextView) v.findViewById(R.id.isprivate);
//
//		Deck deck = (Deck) decks.get(position);
//		image.setImageResource(R.drawable.category_debug_icon);
//		tvTitle.setText(deck.getTitle());
//		tvDescription.setText("Description: " + deck.getDescription());
//		tvCategory.setText(deck.getCategory());
//		tvIsPrivate.setText("DID" + deck.getDid());
//
//		return v;
//	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		tvIsPrivate.setText("Deck ID: " + deck.getDid());

		return v;
	}
	
	public void setList(List<Deck> d)
	{
		this.decks = d;
	}
	
	@Override
	public void add(Deck deck) {
		// TODO Auto-generated method stub
		super.add(deck);
		this.decks.add(deck);
	}
	
//	@Override
//	public void remove(Deck deck) {
//		// TODO Auto-generated method stub
//		super.remove(deck);
//	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
		this.decks.clear();
	}
}