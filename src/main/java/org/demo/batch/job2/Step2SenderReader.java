package org.demo.batch.job2;

import org.demo.tools.log.BasicLogger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Reads a piece of input data and advance to the next one. 
 * Implementations must return null at the end of the input data set. 
 * In a transactional setting, caller might get the same item twice from successive calls (or otherwise), 
 * if the first call was in a transaction that rolled back.
 *
 */
public class Step2SenderReader implements ItemReader<String> {

	private static final BasicLogger LOGGER = BasicLogger.getLogger( Step2SenderReader.class );

	private int n = 0 ;
	
	public Step2SenderReader() {
		super();
		LOGGER.log("ItemReader - Constructor");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		String item = null ;
		n++ ;
		if ( n <= 55 ) {
			item = "ABCD" + n ;
		}
		else { 
			item = null ;
		}
		System.out.println("read() --> " + item);		
		return item ;
		
	}

}
