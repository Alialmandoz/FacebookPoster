package programador;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Programador {

	public static void run() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		// define the job and tie it to our HelloJob class
		JobDetail job = newJob(Trabajo.class)
			    .withIdentity("job1", "group1")
			    .usingJobData("someProp", "someValue")
			    .build();
		
		
		// Trigger the job to run on the next round minute
		Trigger trigger = newTrigger()
			    .withIdentity("trigger3", "group1")
			    .startNow()
			    .withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.WEDNESDAY, 15, 0)) // fire every wednesday at 15:00
			    .build();
		
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);
		
		sched.start();
		
		try {
			Thread.sleep(90L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sched.shutdown(true);

	}
	
}
