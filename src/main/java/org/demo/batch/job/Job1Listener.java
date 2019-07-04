package org.demo.batch.job;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class Job1Listener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		BasicLogger.setLogStatus(true);		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
	} 

}
