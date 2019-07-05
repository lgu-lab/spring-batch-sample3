package org.demo.batch.job1;

import org.demo.batch.fakejms.JMS;
import org.demo.tools.batch.jmsdialog.DialogStatus;
import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepSender implements Tasklet  {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( StepSender.class );

	private long wait = 0 ;
	private long nbMsg = 0 ;
	private long nbMsgSent = 0 ;

	public StepSender() {
		super();
		LOGGER.log("Tasklet : Constructor");
	}
	
	public void setWait(long v) {
		this.wait = v ;
		LOGGER.log("wait set to " + v );
	}

	public void setNbMsg(long v) {
		this.nbMsg = v ;
		LOGGER.log("nbMsg set to " + v );
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		LOGGER.log("execute() : nbMsg = " + nbMsg + " ( wait = " + wait + " )" );
		
		// wait before send
		Thread.sleep(wait);
		
		nbMsgSent++ ;
		String msg = "My message "+ nbMsgSent ;
		LOGGER.log("JMS : sending '" + msg );
		JMS.send(msg);
		DialogStatus.messageSent(chunkContext);
		LOGGER.log("Current status : " + DialogStatus.getCurrentStatus(chunkContext) ) ;


		// Return status : 
		// . RepeatStatus.CONTINUABLE : Indicates that processing can continue.
		// . RepeatStatus.FINISHED    : Indicates that processing is finished (either successful or unsuccessful)	
		if ( nbMsgSent < nbMsg ) {
			return RepeatStatus.CONTINUABLE;
		}
		else {
			LOGGER.log("END OF SENDING. " ) ;
			DialogStatus.endOfSending(chunkContext);
			LOGGER.log("Current status : " + DialogStatus.getCurrentStatus(chunkContext) ) ;
			return RepeatStatus.FINISHED ;
		}
	}

}
