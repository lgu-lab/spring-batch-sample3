package org.demo.tools.batch.jmsdialog;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class DialogStatusResetAfterStep implements StepExecutionListener {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( DialogStatusResetAfterStep.class );
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		LOGGER.log("beforeStep : step name = " + stepExecution.getStepName() );
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.log("afterStep : step name = " + stepExecution.getStepName() );
		LOGGER.log("afterStep : ---------- RESET DialogStatus ---------- ");
		DialogStatus.reset(stepExecution);
		return null;
	}

}
