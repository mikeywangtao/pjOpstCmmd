package orgs.cm.pMqp.pQz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import orgs.cm.pMqp.pLis.QuartzSett;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.QuartzJob_Getimg;

public class QuartzManager {

	private static final String strCname = QuartzSett.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private static SchedulerFactory sf; // = new StdSchedulerFactory();
//	private static SchedulerFactory sf = new StdSchedulerFactory();  

	private static String JOB_GROUP_NAME = "group1";
	private static String TRIGGER_GROUP_NAME = "trigger1";

	/** */
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param job
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	 public static void addJob(String jobName, Job jobD, String time) throws
	 SchedulerException, ParseException{

//		Scheduler sched = sf.getScheduler();
//		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, jobD.getClass());// 任务名，任务组，任务执行类
		// 触发器
//		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
//		trigger.setCronExpression(time);// 触发器时间设定
//		sched.scheduleJob(jobDetail, trigger);
		// 启动
//		if (!sched.isShutdown())
//			sched.start();
		SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		String strSchedulername = "job" + jobName + objSdf.format(new Date());

		Properties props = new Properties();
		props.put("org.quartz.scheduler.instanceName", strSchedulername);//
		props.put("org.quartz.scheduler.instanceId", "AUTO");//
		props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");//
		props.put("org.quartz.threadPool.threadCount", "10");//
		props.put("org.quartz.threadPool.threadPriority", "5");//
		sf = new StdSchedulerFactory(props);

		// scheduler = sf.getScheduler();
		// System.out.println(scheduler.getSchedulerName());
		// scheduler.shutdown();

		Scheduler sched = null;
		// Scheduler sched = sf.getScheduler();
		// Scheduler sched = sf.getScheduler(); //sf.getScheduler();
		// Collection list = sf.getAllSchedulers();
		// sched = StdSchedulerFactory.getDefaultScheduler() ;
		
//		QuartzJob_Getimg jobs = new QuartzJob_Getimg();
		
		sched = sf.getScheduler();
		// System.out.println(list);
		System.out.println(sched.getSchedulerName());
		if (sched != null) {
//			JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, jobs.getClass());// 任务名，任务组，任务执行类
			JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, jobD.getClass());// 任务名，任务组，任务执行类
			// 触发器
			CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
			if (jobDetail != null && trigger != null) {
				trigger.setCronExpression(time);// 触发器时间设定
				sched.scheduleJob(jobDetail, trigger);
				// 启动
				if (!sched.isShutdown()) {
					sched.start();
				}
			}
		}

	}

	/** */
	/**
	 * 添加一个定时任务
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param job
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Job job,
			String time) throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		JobDetail jobDetail = new JobDetail(jobName, jobGroupName, job.getClass());// 任务名，任务组，任务执行类
		// 触发器
		CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
		trigger.setCronExpression(time);// 触发器时间设定
		sched.scheduleJob(jobDetail, trigger);
		if (!sched.isShutdown())
			sched.start();
	}

	/** */
	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void modifyJobTime(String jobName, String time) throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			ct.setCronExpression(time);
			sched.resumeTrigger(jobName, TRIGGER_GROUP_NAME);
		}
	}

	/** */
	/**
	 * 修改一个任务的触发时间
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void modifyJobTime(String triggerName, String triggerGroupName, String time)
			throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(triggerName, triggerGroupName);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			// 修改时间
			ct.setCronExpression(time);
			// 重启触发器
			sched.resumeTrigger(triggerName, triggerGroupName);
		}
	}

	/** */
	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName) throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
		sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
		sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
	}

	/** */
	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName)
			throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
		sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
		sched.deleteJob(jobName, jobGroupName);// 删除任务
	}
}