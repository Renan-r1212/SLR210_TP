package com.example;

import java.util.ArrayList;
import akka.actor.ActorRef;

public class References {
	private ArrayList<ActorRef> processRefList;
	
	public References(ArrayList<ActorRef> _processRefList) {
		processRefList = _processRefList;
	}
	
	public void setReferences(ArrayList<ActorRef> _processRefList) {
		processRefList = _processRefList;
	}
	
	public ArrayList<ActorRef> getReferences() {
		return processRefList;
	}
	
}
