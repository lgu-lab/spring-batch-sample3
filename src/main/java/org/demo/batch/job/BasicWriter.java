package org.demo.batch.job;

import java.io.PrintStream;
import java.util.List;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.item.ItemWriter;

public class BasicWriter implements ItemWriter<String> {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( BasicWriter.class );

	public BasicWriter() {
		super();
		LOGGER.log("ItemWriter - Constructor");
	}
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		// Write on the console
		PrintStream out = System.out ;
		out.println("write chunk list : " + items.size() + " items");
		for ( String s : items ) {
			out.println(" . item : " + s);
		}

		out.println("end of write = end of chunk processing. " );
		out.println("--------------------- " );
	}
	

}
