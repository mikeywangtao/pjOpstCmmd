package orgs.cm.pMqp.pQz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {//extends QuartzBaseJob {
	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);
	

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(returnstr + "★★★★★★★★★★★");

//		int intLoop = DateFlg.disGetLoop();
//		System.out.println("strJobname : " + strJobname + " Run---->" + DateFormat.format(new Date()));
//		for (int i = 0; i < intLoop; i++) {
//			LogSer02 objLogSer02 = new LogSer02();
//			objLogSer02.disCreateFile(Attrs.strLogFlg_CF);
//		}
	}
}
