package com.inajstudios.studycards.models;

public class Card {
	
	private int cid;
	private int did;
	private String front;
	private String back;
	
	/*
	 * All cards MUST have a DeckID, cheap way to enforce it
	 */
	public Card()
	{
		this.did = -1;
		this.cid = -1;
		this.front = "NO FRONT";
		this.back = "NO BACK";
	}
	
	public Card(int did)
	{
		this.did = did;
	}
	
	public Card (String f, String b)
	{
		this.did = -1;
		this.cid = -1;
		this.front = f;
		this.back = b;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getFront() {
		return front;
	}
	public void setFront(String front) {
		this.front = front;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	
	
	
}
