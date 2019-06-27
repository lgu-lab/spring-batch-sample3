package org.demo.batch.job;

import org.demo.batch.jms.JMS;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepSender implements Tasklet  {
	
	private long wait = 0 ;
	private long nbMsg = 0 ;
	private long nbMsgSent = 0 ;

	private void print(String msg) {
		System.out.println(this.getClass().getSimpleName() + " : " + msg);
	}
	
	public StepSender() {
		super();
		System.out.println("Step3a - Tasklet : Constructor");
	}
	
	public void setWait(long v) {
		this.wait = v ;
		print("wait set to " + v );
	}

	public void setNbMsg(long v) {
		this.nbMsg = v ;
		print("nbMsg set to " + v );
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		print("execute() : nbMsg = " + nbMsg + " ( wait = " + wait + " )" );
//		if ( chunkContext != null ) {
//			print("ChunkContext : " + chunkContext );
//		}
		
		Thread.sleep(wait);
		
		nbMsgSent++ ;
		String msg = "My message "+ nbMsgSent ;
		print("JMS : sending '" + msg );
		JMS.send(msg);
		DialogStatus.messageSent(chunkContext);
		print("Current status : " + DialogStatus.getCurrentStatus(chunkContext) ) ;


		// Return status : 
		// . RepeatStatus.CONTINUABLE : Indicates that processing can continue.
		// . RepeatStatus.FINISHED    : Indicates that processing is finished (either successful or unsuccessful)	
		if ( nbMsgSent < nbMsg ) {
			return RepeatStatus.CONTINUABLE;
		}
		else {
			print("END OF SENDING. " ) ;
			DialogStatus.endOfSending(chunkContext);
			print("Current status : " + DialogStatus.getCurrentStatus(chunkContext) ) ;
			return RepeatStatus.FINISHED ;
		}
	}

}
