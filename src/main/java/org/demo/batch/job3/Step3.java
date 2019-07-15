package org.demo.batch.job3;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step3 implements Tasklet  {
	
	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step3.class );

	public Step3() {
		super();
		LOGGER.log("Tasklet : Constructor");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		LOGGER.log("execute()");
		

		return RepeatStatus.FINISHED ;
	}

}
