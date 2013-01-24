package com.inajstudios.studycards.models;

import java.io.Serializable;

public class Deck implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long did;
	private long uid;
	private String title;
	private String description;
	private String category;
	private int isPrivate;
	
	public Deck()
	{
		this.title = "no title";
		this.description = "no description";
		this.category = "no category";
		this.isPrivate = 0;
	}
	
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public String print()
	{
		String s = "Deck info: " + getTitle() + " | " + getDescription() + " | " + getCategory() + " | " + getIsPrivate();
		return s;
	}
	
	
}
