package testQuartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

class MyJob2 implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("==============2222222222222");
	}
	
	public void execute () {
		System.out.println("++++++++++++++222222222222");
	}

}

