package com.auction.daomgr;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronJobToGetBidWinner {
	
public static void startJob() {
		
		try {
			
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(WinnerJob.class)
			    .withIdentity("testJob")
			    .build();
			
			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("crontrigger","crontriggergroup1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 45 23 ? * *"))
                    .build(); 
	    	
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
	    	sch.start();	    	
	    	sch.scheduleJob(job, cronTrigger);		
		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	
/*extends TimerTask {
	private final static int HOURS = 01;
	private final static int MINUTES = 26;
	private ArrayList<UserDetails> userDetails;

	public ArrayList<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(ArrayList<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public void run() {
		long currennTime = System.currentTimeMillis();
		long stopTime = currennTime + 1000*60*20;
		System.out.println("Stop time check : "+stopTime);
		while (stopTime != System.currentTimeMillis()) {
			System.out.println("Stop time check : "+stopTime);
			userDetails = new ItemsDaoManager().getBidderWinners();
			System.out.println("Start Job" + stopTime);
			System.out.println("End Job" + System.currentTimeMillis());
		}
	}

	@SuppressWarnings("deprecation")
	private static Date get12AMTmrw() {

		Date date = new java.util.Date();
		date.setHours(HOURS);
		date.setMinutes(MINUTES);
		return date;
	}

	// call this method from your servlet init method
	public static void startTask() {
		MyTimerTask task = new MyTimerTask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, get12AMTmrw(),1000*60*60*24);
	}

	public static void main(String args[]) {
		startTask();

	}
*/

public static void main(String args[]) {
	 startJob();
}
}
