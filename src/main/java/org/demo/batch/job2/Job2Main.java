package org.demo.batch.job2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Job2Main {

//	@SuppressWarnings("resource")
	public static void main(String args[]) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-job2.xml");

		runJob(context, "job2") ;
		
	}
	
	public static void runJob(ApplicationContext context, String jobName) {
		
		Job job = (Job) context.getBean(jobName);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Exit Status : "+ execution.getStatus());
	 
		} catch (JobExecutionException e) {
			System.out.println("Job ExamResult failed");
			e.printStackTrace();
		}
	}
}
