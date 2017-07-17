package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import orgs.cm.pMqp.pQz.QuartzBaseJob;

public class QuartzJob_Getimg extends QuartzBaseJob {
	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);
	
	String strJobname = null;
	public void setSchedulername(String strSchedulernamep){
		this.strJobname = strSchedulernamep;
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(returnstr + "★★★★★★★★★★★");

//		int intLoop = DateFlg.disGetLoop();
		System.out.println("strJobname : " + strJobname + " Run---->" + DateFormat.format(new Date()));
//		for (int i = 0; i < intLoop; i++) {
//			LogSer02 objLogSer02 = new LogSer02();
//			objLogSer02.disCreateFile(Attrs.strLogFlg_CF);
//		}
		Runcmdpro_Getimg objRcGetimg = new Runcmdpro_Getimg();
		objRcGetimg.disRuncmdPro();
	}
}
