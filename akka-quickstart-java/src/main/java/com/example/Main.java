package com.example;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {
	
	private static ArrayList<ActorRef> processRefList;
	
	private static int timeOutArray[] = {500, 1000, 1500, 2000};
    private static int numOfProcess[] = {3, 10, 100};
    
	
	public static void main(String[] args) {
		processRefList = new ArrayList<ActorRef>();
		
		for(int timeOut : timeOutArray)
			for(int N :numOfProcess) {
				int f = N - (N/2 + 1);
				
		        final ActorSystem system = ActorSystem.create("system");
				
			    // Instantiate processes
		        for(int processID = 1; processID <= N; processID++) 
		        	processRefList.add(system.actorOf(Process.createActor(processID, N, true), "Process " + processID));
		        
		        for(int processID = 0; processID < processRefList.size(); processID++)
		        	processRefList.get(processID).tell(new References(processRefList), ActorRef.noSender());
		        
		        

			}
	}
}
