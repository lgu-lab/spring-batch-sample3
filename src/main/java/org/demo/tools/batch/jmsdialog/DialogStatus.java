package org.demo.tools.batch.jmsdialog;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;

public class DialogStatus { 
	
	/**
	 * Private constructor
	 */
	private DialogStatus() {
		// NOP
	}
	
	//------------------------------------------------------------------------------------
	/**
	 * Returns the current DialogStatusBean 
	 * @param chunkContext
	 * @return
	 */
	public static DialogStatusBean getCurrentStatus(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext);
	}
	/**
	 * Returns the current DialogStatusBean 
	 * @param stepExecution
	 * @return
	 */
	public static DialogStatusBean getCurrentStatus(StepExecution stepExecution) {
		return getDialogStatus(stepExecution);
	}

	//------------------------------------------------------------------------------------
	/**
	 * Reset the current DialogStatusBean 
	 * @param chunkContext
	 */
	public static void reset(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).reset();
	}
	/**
	 * Reset the current DialogStatusBean 
	 * @param stepExecution
	 */
	public static void reset(StepExecution stepExecution) {
		getDialogStatus(stepExecution).reset();
	}

	//------------------------------------------------------------------------------------
	// Sender methods
	//------------------------------------------------------------------------------------
	/**
	 * Notify that a message has been sent (increment the counter)
	 * @param chunkContext
	 */
	public static void messageSent(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).incrementSentCount();
	}
	/**
	 * Notify that a message has been sent (increment the counter)
	 * @param stepExecution
	 */
	public static void messageSent(StepExecution stepExecution) {
		getDialogStatus(stepExecution).incrementSentCount();
	}
	
	/**
	 * Notify end of sending (all messages have been sent)
	 * @param chunkContext
	 */
	public static void endOfSending(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).setAllMessagesSent();
	}	
	/**
	 * Notify end of sending (all messages have been sent)
	 * @param stepExecution
	 */
	public static void endOfSending(StepExecution stepExecution) {
		getDialogStatus(stepExecution).setAllMessagesSent();
	}	
	
	//------------------------------------------------------------------------------------
	// Receiver methods
	//------------------------------------------------------------------------------------
	/**
	 * Notify that a message has been received (increment the counter)
	 * @param chunkContext
	 */
	public static void messageReceived(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).incrementReceivedCount();
	}
	/**
	 * Notify that a message has been received (increment the counter)
	 * @param stepExecution
	 */
	public static void messageReceived(StepExecution stepExecution) {
		getDialogStatus(stepExecution).incrementReceivedCount();
	}
	
	/**
	 * Returns true if the dialog is completed (true if the receiver has received all the expected messages)
	 * @param chunkContext
	 * @return
	 */
	public static boolean dialogCompleted(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext).dialogCompleted();
	}
	/**
	 * Returns true if the dialog is completed (true if the receiver has received all the expected messages)
	 * @param stepExecution
	 * @return
	 */
	public static boolean dialogCompleted(StepExecution stepExecution) {
		return getDialogStatus(stepExecution).dialogCompleted();
	}

	//------------------------------------------------------------------------------------
	// Status storage in job context
	//------------------------------------------------------------------------------------
	private static final String DIALOG_STATUS = "DIALOG_STATUS" ;

	private static final synchronized DialogStatusBean getDialogStatus(ChunkContext chunkContext) {
		JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
		return getDialogStatus( jobExecution );
	}
	
	private static final synchronized DialogStatusBean getDialogStatus(StepExecution stepExecution) {
		return getDialogStatus( stepExecution.getJobExecution() );
	}
	
	private static final synchronized DialogStatusBean getDialogStatus(JobExecution jobExecution) {
		ExecutionContext executionContext = jobExecution.getExecutionContext();
		DialogStatusBean status = (DialogStatusBean) executionContext.get(DIALOG_STATUS);
		if ( status == null ) {
			status = new DialogStatusBean() ;
			executionContext.put(DIALOG_STATUS, status);
		}
		return status ;
	}
}
