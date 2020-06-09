package com.example;

import akka.actor.Actor.*;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class Process extends UntypedAbstractActor{
	private References pRef;
	
	private int N;//number of processes
    private int ID;//id of current process
    private boolean alive;
    private boolean hold;
    private int identifier;
    private int impIdentifier;
    private int proposal;
    private int estimate;
    
	
	public Process(int _ID, int _N, boolean _alive) {
		N = _N;
		ID = _ID;
		alive = _alive;
		
		hold = false;
		identifier = N - ID;	
		proposal = -1;
		estimate = -1;
	}
	
    public static Props createActor(int _ID, int _N, boolean _alive) {
        return Props.create(Process.class, () -> {
            return new Process(_ID, _N, _alive);
        });
    }
    
    public void propose(int proposedVal) {
    	if(!hold) {
        	proposal = proposedVal;
        	identifier += N;
        	
        	 for (ActorRef process : pRef.getReferences())
        		 process.tell(new CheckIdentifier(identifier), getSelf());
    	}

    }
    
    /*
    public void propose(int v) {
    	this.flagGather = false;
    	this.flagACK = false;
    	this.proposeState = true;
    	this.proposal= v;
    	this.ballot = ballot + this.N;
    	for(ActorRef actors : this.processes.references) {
    		actors.tell(new ReadMsg(this.ballot), getSelf());
    	}
    }
	*/
    
    
	@Override
	public void onReceive(Object message) throws Throwable {
		if(message instanceof References) {
			pRef = (References) message;
		} 
		else if(message instanceof CheckIdentifier) {
			CheckIdentifier IDCheck = (CheckIdentifier) message;
      	  	if(identifier >= IDCheck.getIdentifier() || impIdentifier >= IDCheck.getIdentifier())
      	  		getSender().tell(new Abort(IDCheck.getIdentifier()), getSelf());
      	  	else {
      	  		identifier = IDCheck.getIdentifier();
      	  		getSender().tell(new Gather(identifier, impIdentifier, estimate, ID), getSelf());
      	  	}
		} else if(message instanceof Abort) {
            Abort abort = (Abort) message;
            if (abort.getIdentifier() == identifier)
                propose(proposal);   
		} else if(message instanceof Gather) {
			
		}
	}
}
