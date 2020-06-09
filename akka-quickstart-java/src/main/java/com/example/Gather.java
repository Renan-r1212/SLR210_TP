package com.example;

public class Gather {
	
	private int identifier;
	private int impIdentifier;
	private int estimate;
	private int ID;
	
	public Gather(int _identifier, int _impIdentifier, int _ID, int _estimate) {
		identifier = _identifier;
		impIdentifier = _impIdentifier;
		ID = _ID;
		estimate = _estimate;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int _identifier) {
		identifier = _identifier;
	}

	public int getImpIdentifier() {
		return impIdentifier;
	}

	public void setImpIdentifier(int _impIdentifier) {
		impIdentifier = _impIdentifier;
	}

	public int getID() {
		return this.ID;
	}
	
	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int _estimate) {
		estimate = _estimate;
	}
}