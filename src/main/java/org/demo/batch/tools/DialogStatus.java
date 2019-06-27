package org.demo.batch.tools;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;

public class DialogStatus {
	
	public static void reset(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).reset();
	}
	public static void reset(StepExecution stepExecution) {
		getDialogStatus(stepExecution).reset();
	}

	//------------------------------------------------------------------------------------
	// Sender methods
	//------------------------------------------------------------------------------------
	public static void messageSent(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).incrementSentCount();
	}
	public static long getSentCount(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext).getSentCount();
	}
	
	//------------------------------------------------------------------------------------
	public static void endOfSending(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).allMessagesSent();
	}	
	public static boolean areAllMessagesSent(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext).areAllMessagesSent();
	}
	
	//------------------------------------------------------------------------------------
	// Receiver methods
	//------------------------------------------------------------------------------------
	public static void messageReceived(ChunkContext chunkContext) {
		getDialogStatus(chunkContext).incrementReceivedCount();
	}
	public static long getReceivedCount(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext).getReceivedCount();
	}
	
	//------------------------------------------------------------------------------------
	public static synchronized boolean dialogCompleted(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext).dialogCompleted();
	}

	//------------------------------------------------------------------------------------
	public static DialogStatusBean getCurrentStatus(ChunkContext chunkContext) {
		return getDialogStatus(chunkContext);
	}

	//------------------------------------------------------------------------------------
	// Status storage in job context
	//------------------------------------------------------------------------------------
	private static final String DIALOG_STATUS = "DIALOG_STATUS" ;

//	private synchronized static final DialogStatusBean getDialogStatus(ChunkContext chunkContext) {
//		JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
//		ExecutionContext executionContext = jobExecution.getExecutionContext();
//		DialogStatusBean status = (DialogStatusBean) executionContext.get(DIALOG_STATUS);
//		if ( status == null ) {
//			status = new DialogStatusBean() ;
//			executionContext.put(DIALOG_STATUS, status);
//		}
//		return status ;
//	}
	
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
