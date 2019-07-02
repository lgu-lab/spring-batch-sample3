package org.demo.tools.batch.util;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;

public class JobUtil {

	private static final String COUNT = "COUNT" ;
	
	private static final long getValue(ExecutionContext executionContext, String varName) {
		Long value = (Long) executionContext.get(varName);
		if ( value == null ) {
			value = 0L ;
		}
		return value ;
	}
	
	private static final void setValue(ExecutionContext executionContext, String varName, long value) {
		executionContext.put(varName, Long.valueOf(value));
	}
	
	private static final ExecutionContext getJobExecutionContext(ChunkContext chunkContext) {
		JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
		return jobExecution.getExecutionContext();
	}

	//--------------------------------------------------------------------------------------
	
	/**
	 * Get the current value of the given variable stored in the Job execution context <br>
	 * If not defined, the variable is initialized at ZERO and set in the context
	 * @param chunkContext
	 * @param varName
	 * @return
	 */
	public static final long getLong(ChunkContext chunkContext, String varName) {
		return getValue( getJobExecutionContext(chunkContext), varName );
	}

	/**
	 * Set the current value of the given variable stored in the Job execution context <br>
	 * @param chunkContext
	 * @param varName
	 * @param value
	 */
	public static final void setLong(ChunkContext chunkContext, String varName, long value) {
		setValue(getJobExecutionContext(chunkContext), varName, value);
	}

	//--------------------------------------------------------------------------------------
	public static final long getCount(ChunkContext chunkContext) {
//		return getValue( getJobExecutionContext(chunkContext), COUNT );
		return getLong(chunkContext, COUNT);
	}

	public static final void setCount(ChunkContext chunkContext, long v) {
//		setValue(getJobExecutionContext(chunkContext), COUNT, v);
		setLong(chunkContext, COUNT, v);
	}

	public static final long incrementCount(ChunkContext chunkContext) {
		ExecutionContext context = getJobExecutionContext(chunkContext);
		long count = getValue(context, COUNT );
		count++ ;
		setValue(context, COUNT, count);
		return count;
	}

	
}
