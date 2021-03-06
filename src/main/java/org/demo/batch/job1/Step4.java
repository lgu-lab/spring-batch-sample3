package org.demo.batch.job1;

import org.demo.tools.batch.util.JobUtil;
import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step4 implements Tasklet  {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step4.class );
	
	public Step4() {
		super();
		LOGGER.log("Tasklet : Constructor");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		LOGGER.log("execute()");
		if ( chunkContext != null ) {
			LOGGER.log("ChunkContext : " + chunkContext );
		}
		LOGGER.log("This is the end." );
		LOGGER.log("Job context Count =  " + JobUtil.getCount(chunkContext) );

		return RepeatStatus.FINISHED ;
	}

}
