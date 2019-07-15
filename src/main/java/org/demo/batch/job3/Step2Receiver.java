package org.demo.batch.job3;

import org.demo.batch.fakejms.JMS;
import org.demo.tools.batch.jmsdialog.DialogStatus;
import org.demo.tools.batch.jmsdialog.DialogSynchronizer;
import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Step2Receiver implements Tasklet, StepExecutionListener{
	
	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step2Receiver.class );

	public Step2Receiver() {
		super();
		LOGGER.log("Tasklet : Constructor");
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		//LOGGER.log("----- beforeStep");
		LOGGER.log("----- beforeStep : waitForSender(stepExecution)");
		DialogSynchronizer.waitForSender(stepExecution);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.log("----- afterStep");
		return null;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		LOGGER.log("execute()");
		
		if ( ! DialogStatus.dialogCompleted(chunkContext) ) {
			LOGGER.log("JMS receive ...");
			String msg = JMS.receive();  // Possible timeout
			if ( msg != null ) {
				DialogStatus.messageReceived(chunkContext);
			}		
			// Timeout 
			// Option 1 => throw Exception => FAILED => Arret du job
			// Option 2 (ex générer 1 fichier retour ) => FINISHED => "Decider" (Step d'aiguillage) : Info ds context ( 1 classe dédiée + job.xml )

			// Process message
			LOGGER.log("message received : \n" + msg);
		}

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
