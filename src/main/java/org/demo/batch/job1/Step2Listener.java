package org.demo.batch.job1;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class Step2Listener implements StepExecutionListener {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step2Listener.class );

	@Override
	public void beforeStep(StepExecution stepExecution) {
		LOGGER.log("beforeStep : step name = " + stepExecution.getStepName() ) ;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.log("afterStep : step name = " + stepExecution.getStepName() ) ;
		return null;
	}

}
