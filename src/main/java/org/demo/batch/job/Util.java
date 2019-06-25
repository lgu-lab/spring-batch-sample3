package org.demo.batch.job;

import java.util.Map;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.item.ExecutionContext;

public class Util {

	private static final String COUNT = "COUNT" ;
	
//	private static final long getCount(Map<String,Object> jobExecutionContext) {
//		Long value = (Long) jobExecutionContext.get(COUNT);
//		if ( value == null ) {
//			value = 0L ;
//		}
//		return value ;
//	}
	private static final long getCount(ExecutionContext executionContext) {
		Long value = (Long) executionContext.get(COUNT);
		if ( value == null ) {
			value = 0L ;
		}
		return value ;
	}
	
	private static final void setCount(ExecutionContext executionContext, long value) {
		executionContext.put(COUNT, Long.valueOf(value));
	}
	
	private static final ExecutionContext getExecutionContext(ChunkContext chunkContext) {
		JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
		ExecutionContext executionContext = jobExecution.getExecutionContext();
		return executionContext ;
	}

	public static final void setCount(ChunkContext chunkContext, long v) {
		
//		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
//		jobExecutionContext.put(COUNT, Long.valueOf(v));
		setCount(getExecutionContext(chunkContext), v);
	}


	public static final long getCount(ChunkContext chunkContext) {
//		JobExecution jobExecution = chunkContext.getStepContext().getStepExecution().getJobExecution();
//		ExecutionContext executionContext = jobExecution.getExecutionContext();
		
		// chunkContext.getStepContext().getJobExecutionContext() // not the actual ExecutionContext (just a Map)
		return getCount( getExecutionContext(chunkContext) );
	}

	public static final long incrementCount(ChunkContext chunkContext) {
//		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
//		long count = getCount( jobExecutionContext );
		long count = getCount( getExecutionContext(chunkContext) );
		count++ ;
//		jobExecutionContext.put(COUNT, Long.valueOf(count));
		setCount(getExecutionContext(chunkContext), count);
		return count;
	}

	
	public static final void test(ChunkContext chunkContext) {
		StepContext stepContext = chunkContext.getStepContext();
		
		Map<String,Object> jobExecutionContext = stepContext.getJobExecutionContext();
		Map<String,Object> stepExecutionContext = stepContext.getStepExecutionContext();
		
		stepContext.getStepName();
		stepContext.getJobName();
		
	}
}
