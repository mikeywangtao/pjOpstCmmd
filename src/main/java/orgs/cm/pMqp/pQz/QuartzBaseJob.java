package orgs.cm.pMqp.pQz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class QuartzBaseJob implements Job {
	public abstract void execute(JobExecutionContext arg0) throws JobExecutionException ;
	public abstract void setSchedulername(String strSchedulername);

}
