package org.demo.batch.job;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.item.ItemProcessor;

/**
 * Interface for item transformation. 
 * Given an item as input, this interface provides an extension point 
 * which allows for the application of business logic in an item oriented processing scenario. 
 * It should be noted that while it's possible to return a different type than the one provided, 
 * it's not strictly necessary. 
 * Furthermore, returning null indicates that the item should not be continued to be processed.
 */
public class BasicProcessor implements ItemProcessor<String, String> {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( BasicProcessor.class );

	public BasicProcessor() {
		super();
		LOGGER.log("ItemProcessor - Constructor");
	}

	@Override
	public String process(String input) throws Exception {
		String output = null ;
		if ( input.endsWith("7") ) {
			output = null ; // null means "do not write"
		}
		else {
			output = input + "-XYZ" ; // the transformed item to be written 
		}
		LOGGER.log("process(" + input + ") --> " + output );
		return output ;
	}
	
}
