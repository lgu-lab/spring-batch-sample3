package org.demo.batch.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step2 implements Tasklet  {
	
	private int count = 0 ;
	
	private int max = 0 ;

	public Step2() {
		super();
		System.out.println("Step2 - Tasklet : Constructor");
	}

	public void setMax(int v) {
		this.max = v ;
		print("max set to " + v );
	}
	
	private void print(String msg) {
		System.out.println(this.getClass().getSimpleName() + " : " + msg);
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
		if ( count < max ) {
			return RepeatStatus.CONTINUABLE;
		}
		else {
			return RepeatStatus.FINISHED ;
		}
	}

}
