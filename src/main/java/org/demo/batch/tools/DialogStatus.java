package org.demo.batch.tools;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;

public class DialogStatus {
	
	//------------------------------------------------------------------------------------
	public static DialogStatusBean getCurrentStatus(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext);
	}

	//------------------------------------------------------------------------------------
	public static void reset(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).reset();
	}
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
	 * Notify end of sending (all messages have been sent)
	 * @param chunkContext
	 */
	public static void endOfSending(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).setAllMessagesSent();
	}	
	
	//------------------------------------------------------------------------------------
	// Receiver methods
	//------------------------------------------------------------------------------------
	/**
	 * Notify that a message has been sent (increment the counter)
	 * @param chunkContext
	 */
	public static void messageReceived(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).incrementReceivedCount();
	}
	
	/**
	 * Returns true if the dialog is completed (the receiver has received all the expected messages)
	 * @param chunkContext
	 * @return
	 */
	public static boolean dialogCompleted(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext).dialogCompleted();
	}

	//------------------------------------------------------------------------------------
	// Status storage in job context
	//------------------------------------------------------------------------------------
	private static final String DIALOG_STATUS = "DIALOG_STATUS" ;

	private synchronized static final DialogStatusBean getDialogStatus(ChunkContext chunkContext) {
		JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
		return getDialogStatus( jobExecution );
	}
	
	private synchronized static final DialogStatusBean getDialogStatus(StepExecution stepExecution) {
		return getDialogStatus( stepExecution.getJobExecution() );
	}
	
	private synchronized static final DialogStatusBean getDialogStatus(JobExecution jobExecution) {
		ExecutionContext executionContext = jobExecution.getExecutionContext();
		DialogStatusBean status = (DialogStatusBean) executionContext.get(DIALOG_STATUS);
		if ( status == null ) {
			status = new DialogStatusBean() ;
			executionContext.put(DIALOG_STATUS, status);
		}
		return status ;
	}
}
