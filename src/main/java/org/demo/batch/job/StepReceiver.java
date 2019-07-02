package org.demo.batch.job;

import org.demo.batch.fakejms.JMS;
import org.demo.tools.batch.jmsdialog.DialogStatus;
import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepReceiver implements Tasklet  {
	
	private static final BasicLogger LOGGER = BasicLogger.getLogger( StepReceiver.class );

//	private void print(String msg) {
//		System.out.println(this.getClass().getSimpleName() + " : " + msg);
//	}
	
	public StepReceiver() {
		super();
//		System.out.println("Step3b - Tasklet : Constructor");
		LOGGER.log("Tasklet : Constructor");
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		LOGGER.log("execute()");
		
		// Wait for next message from JMS
		LOGGER.log("JMS receive : waiting...");
		String msg = JMS.receive();  // Possible timeout
		if ( msg != null ) {
			DialogStatus.messageReceived(chunkContext);
		}		
		// Timeout 
		// Option 1 => throw Exception => FAILED => Arret du job
		// Option 2 (ex générer 1 fichier retour ) => FINISHED => "Decider" (Step d'aiguillage) : Info ds context ( 1 classe dédiée + job.xml )
		
		
		// Process message
		LOGGER.log("message received : " + msg);

		// Return status : 
		LOGGER.log("Current status : " + DialogStatus.getCurrentStatus(chunkContext) ) ;
		if ( DialogStatus.dialogCompleted(chunkContext) ) {
			// TODO : DialogStatus.reset()  in step listener ?
			LOGGER.log("dialog completed => FINISHED ");
			return RepeatStatus.FINISHED ; // Indicates that processing is finished (either successful or unsuccessful)
		}
		else {
			LOGGER.log("dialog not completed => CONTINUABLE ");
			return RepeatStatus.CONTINUABLE; // Indicates that processing can continue.
		}
	}

}
