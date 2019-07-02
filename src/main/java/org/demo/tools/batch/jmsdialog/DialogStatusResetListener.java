package org.demo.tools.batch.jmsdialog;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class DialogStatusResetListener implements StepExecutionListener {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( DialogStatusResetListener.class );
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		LOGGER.log("beforeStep : step name = " + stepExecution.getStepName() );
		LOGGER.log("beforeStep : RESET DialogStatus ---------- ");
		DialogStatus.reset(stepExecution);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.log("afterStep : step name = " + stepExecution.getStepName() );
		// Returns an ExitStatus to combine with the normal value. Return null to leave the old value unchanged.
		return null;
	}

}
