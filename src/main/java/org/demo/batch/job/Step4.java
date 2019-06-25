package org.demo.batch.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step4 implements Tasklet  {
	
	private void print(String msg) {
		System.out.println(this.getClass().getSimpleName() + " : " + msg);
	}
	
	public Step4() {
		super();
		System.out.println("Step4 - Tasklet : Constructor");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		print("execute()");
		if ( chunkContext != null ) {
			print("ChunkContext : " + chunkContext );
		}
		print("This is the end." );
		print("Job context Count =  " + Util.getCount(chunkContext) );

		return RepeatStatus.FINISHED ;
	}

}
