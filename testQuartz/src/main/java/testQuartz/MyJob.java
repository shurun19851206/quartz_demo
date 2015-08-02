package testQuartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

class MyJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("==============");
	}
	
	public void execute () {
		System.out.println("++++++++++++++");
	}
	
}
