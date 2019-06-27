package org.demo.batch.job;

import org.demo.batch.tools.BasicLogger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step2 implements Tasklet  {
	
	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step2.class );

	private int count = 0 ;
	
	private int max = 0 ;

	public Step2() {
		super();
		LOGGER.log("Tasklet : Constructor");
	}

	public void setMax(int v) {
		this.max = v ;
		LOGGER.log("max set to " + v );
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		LOGGER.log("execute()");
		if ( chunkContext != null ) {
			LOGGER.log("ChunkContext : " + chunkContext );
		}
		count++ ;
		LOGGER.log("count = " + count );
		Util.incrementCount(chunkContext);
		

		// Return status : 
		// . RepeatStatus.CONTINUABLE : Indicates that processing can continue.
		// . RepeatStatus.FINISHED    : Indicates that processing is finished (either successful or unsuccessful)	
		if ( count < max ) {
			return RepeatStatus.CONTINUABLE;
		}
		else {
			return RepeatStatus.FINISHED ;
		}
	}

}