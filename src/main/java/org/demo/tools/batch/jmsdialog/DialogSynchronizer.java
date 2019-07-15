package org.demo.tools.batch.jmsdialog;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;

public class DialogSynchronizer { 
	
	private static final long SLEEP_DURATION = 500 ; // 500 ms
	/**
	 * Private constructor
	 */
	private DialogSynchronizer() {
		// NOP
	}
	
	//------------------------------------------------------------------------------------
	/**
	 * Wait until the sender starts 
	 * @param chunkContext
	 */
	public static void waitForSender(ChunkContext chunkContext) {
		while ( ! dialogHasStarted( DialogStatus.getCurrentStatus(chunkContext) ) ) {
			try {
				Thread.sleep(SLEEP_DURATION);
			} catch (InterruptedException e) {
				// If interrupted : stop waiting
				return ;
			}
		}
	}
	/**
	 * Wait until the sender starts 
	 * @param stepExecution
	 */
	public static void waitForSender(StepExecution stepExecution) {
		while ( ! dialogHasStarted( DialogStatus.getCurrentStatus(stepExecution) ) ) {
			try {
				Thread.sleep(SLEEP_DURATION);
			} catch (InterruptedException e) {
				// If interrupted : stop waiting
				return ;
			}
		}
	}

	/**
	 * Returns true if the dialog has started
	 * @param status
	 * @return
	 */
	private static boolean dialogHasStarted(DialogStatusBean status) {
		// If the sender sent at least 1 message
		if ( status.getSentCount() > 0 ) {
			return true ;
		}
		// If the sender said "endOfSending" ( even if 0 message sent ) 
		if ( status.getAllMessagesSent() ) {
			return true ;
		}
		// Otherwise 
		return false ;
	}

}
