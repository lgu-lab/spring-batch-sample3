package org.demo.batch.job3;

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
public class Step2SenderProcessor implements ItemProcessor<String, String> {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step2SenderProcessor.class );

	public Step2SenderProcessor() {
		super();
		LOGGER.log("ItemProcessor - Constructor");
	}

	@Override
	public String process(String input) throws Exception {
		String output = input + "-XYZ" ; // the transformed item to be written 
		// LOGGER.log("process(" + input + ") --> " + output );
		return output ;
	}
	
}
