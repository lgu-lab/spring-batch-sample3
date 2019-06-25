package org.demo.batch.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step1 implements Tasklet  {
	
	private int count = 0 ;

	private void print(String msg) {
		System.out.println(this.getClass().getSimpleName() + " : " + msg);
	}
	
	public Step1() {
		super();
		System.out.println("Step1 - Tasklet : Constructor");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		print("execute()");
		if ( chunkContext != null ) {
			print("ChunkContext : " + chunkContext );
		}
		count++ ;
		print("count = " + count );
		Util.incrementCount(chunkContext);
		

		// Return status : 
		// . RepeatStatus.CONTINUABLE : Indicates that processing can continue.
		// . RepeatStatus.FINISHED    : Indicates that processing is finished (either successful or unsuccessful)	
		if ( count < 10 ) {
			return RepeatStatus.CONTINUABLE;
		}
		else {
			return RepeatStatus.FINISHED ;
		}
	}

}
