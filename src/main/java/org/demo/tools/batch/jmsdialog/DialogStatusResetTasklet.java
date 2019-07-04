package org.demo.tools.batch.jmsdialog;

import org.demo.tools.log.BasicLogger;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DialogStatusResetTasklet implements Tasklet  {
	
	private static final BasicLogger LOGGER = BasicLogger.getLogger( DialogStatusResetTasklet.class );

//	public DialogStatusResetTasklet() {
//		super();
//	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOGGER.log("Tasklet : ---------- RESET DialogStatus ---------- ");
		DialogStatus.reset(chunkContext);
		return RepeatStatus.FINISHED ;
	}

}
