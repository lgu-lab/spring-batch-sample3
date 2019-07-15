package org.demo.batch.job3;

import java.io.PrintStream;
import java.util.List;

import org.demo.batch.fakejms.JMS;
import org.demo.tools.batch.jmsdialog.DialogStatus;
import org.demo.tools.log.BasicLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

public class Step2SenderWriter implements ItemWriter<String>, StepExecutionListener {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step2SenderWriter.class );
	
	private StepExecution stepExecution ;

	public Step2SenderWriter() {
		super();
		LOGGER.log("ItemWriter - Constructor");
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution ;
		LOGGER.log("WRITER beforeStep ========== " ) ;
		LOGGER.log("Current status : " + DialogStatus.getCurrentStatus(stepExecution) ) ;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.log("WRITER afterStep ========== " ) ;
		DialogStatus.endOfSending(stepExecution);
		LOGGER.log("Current status : " + DialogStatus.getCurrentStatus(stepExecution) ) ;
		return null;
	}
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		// Write on the console
		PrintStream out = System.out ;
		out.println("write chunk list : " + items.size() + " items");
		
		// Build JMS message
		StringBuffer sb = new StringBuffer();
		for ( String s : items ) {
			sb.append(s);
			sb.append("\n");
		}

		String message = sb.toString() ;
		// Send JMS message
		LOGGER.log("sending message : \n" + message);
		JMS.send(message, 500);
		DialogStatus.messageSent(stepExecution);

		out.println("end of write = end of chunk processing. " );
		out.println("--------------------- " );
	}


}
