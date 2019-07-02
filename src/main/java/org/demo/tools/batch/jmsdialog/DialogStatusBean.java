package org.demo.tools.batch.jmsdialog;

import java.io.Serializable;

public class DialogStatusBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Sender data
	private boolean allMessagesSent = false ;
	private long sentCount = 0 ;
	
	// Receiver data
	private long receivedCount = 0 ;
	
	protected DialogStatusBean() {
		super();
	}

	protected synchronized void reset() {
		this.allMessagesSent = false ;
		this.sentCount = 0;
		this.receivedCount = 0 ;
	}

	//------------------------------------------------------------------------------------
	// Sender methods
	//------------------------------------------------------------------------------------
	protected synchronized void incrementSentCount() {
		this.sentCount++;
	}
	public long getSentCount() {
		return sentCount;
	}
	
	//------------------------------------------------------------------------------------
	protected synchronized void setAllMessagesSent() {
		allMessagesSent = true;
	}	
	public boolean getAllMessagesSent() {
		return allMessagesSent ;
	}
	
	//------------------------------------------------------------------------------------
	// Receiver methods
	//------------------------------------------------------------------------------------
	protected synchronized void incrementReceivedCount() {
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
