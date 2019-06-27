package org.demo.batch.job;

import org.demo.batch.jms.JMS;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepReceiver implements Tasklet  {
	
	private void print(String msg) {
		System.out.println(this.getClass().getSimpleName() + " : " + msg);
	}
	
	public StepReceiver() {
		super();
		System.out.println("Step3b - Tasklet : Constructor");
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		print("execute()");
//		if ( chunkContext != null ) {
//			print("ChunkContext : " + chunkContext );
//		}
//		Thread.sleep(wait);
		
		// Wait for next message from JMS
		print("JMS receive : waiting...");
		String msg = JMS.receive(); 
		DialogStatus.messageReceived(chunkContext);
		
		// Process message
		print("message received : " + msg);

		// Return status : 
		print("Current status : " + DialogStatus.getCurrentStatus(chunkContext) ) ;
		if ( DialogStatus.dialogCompleted(chunkContext) ) {
			print("dialog completed => FINISHED ");
			return RepeatStatus.FINISHED ; // Indicates that processing is finished (either successful or unsuccessful)
		}
		else {
			print("dialog not completed => CONTINUABLE ");
			return RepeatStatus.CONTINUABLE; // Indicates that processing can continue.
		}
	}

}
