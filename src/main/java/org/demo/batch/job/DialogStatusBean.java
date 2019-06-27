package org.demo.batch.job;

import java.io.Serializable;

public class DialogStatusBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Sender data
	private boolean allMessagesSent = false ;
	private long sentCount = 0 ;
	
	// Receiver data
	private long receivedCount = 0 ;
	
	public DialogStatusBean() {
		super();
	}

	//------------------------------------------------------------------------------------
	// Sender methods
	//------------------------------------------------------------------------------------
	public synchronized void incrementSentCount() {
		this.sentCount++;
	}
	public long getSentCount() {
		return sentCount;
	}
	
	//------------------------------------------------------------------------------------
	public synchronized void allMessagesSent() {
		allMessagesSent = true;
	}	
	public boolean areAllMessagesSent() {
		return allMessagesSent ;
	}
	
	//------------------------------------------------------------------------------------
	// Receiver methods
	//------------------------------------------------------------------------------------
	public void incrementReceivedCount() {
		this.receivedCount++;
	}
	public long getReceivedCount() {
		return receivedCount;
	}
	
	//------------------------------------------------------------------------------------
	public synchronized boolean dialogCompleted() {
		if ( allMessagesSent && ( receivedCount == sentCount) ) {
			return true ;
		}
		else {
			return false ;
		}
	}

	@Override
	public String toString() {
		return "DialogStatusBean [allMessagesSent=" + allMessagesSent + ", sentCount=" + sentCount + ", receivedCount="
				+ receivedCount + "]";
	}
	
}
