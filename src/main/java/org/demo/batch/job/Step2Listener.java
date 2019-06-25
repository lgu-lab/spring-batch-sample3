package org.demo.batch.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class Step2Listener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("beforeStep : step name = " + stepExecution.getStepName() ) ;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("afterStep : step name = " + stepExecution.getStepName() ) ;
		return null;
	}

}
