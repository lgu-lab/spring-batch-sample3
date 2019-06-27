package org.demo.batch.job;

import java.util.List;

import org.demo.batch.tools.BasicLogger;
import org.springframework.batch.item.ItemWriter;

public class BasicWriter implements ItemWriter<String> {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( BasicWriter.class );

	public BasicWriter() {
		super();
		LOGGER.log("ItemWriter - Constructor");
	}
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("write chunk list : " + items.size() + " items");
		for ( String s : items ) {
			System.out.println(" . item : " + s);
		}

		System.out.println("end of write = end of chunk processing. " );
		System.out.println("--------------------- " );
	}
	

}
