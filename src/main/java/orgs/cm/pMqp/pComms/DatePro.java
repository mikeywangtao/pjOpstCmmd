package orgs.cm.pMqp.pComms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.tst.service.TstServ;

/**
 *  日期处理集合
 * */
public class DatePro {

	private static final String strCname = DatePro.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private static SimpleDateFormat objSdf000 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	private static SimpleDateFormat objSdf001 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static void disOutputLog(String strFnamep, Exception exp){
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}
	
	/** 获取当前i日期的yyyyMMddHHmmssS格式的字符串 */
	public static String disGetStrdate4NowObjSdf001(){
		String strFname = " disGetStrdate4NowObjSdf001 : ";
		String strRe = null;
		try {
			strRe = objSdf001.format(new Date());
		} catch(Exception ex) {
			strRe = null;
			disOutputLog(strFname, ex);
		}
		return strRe;
	}
	/** 获取当前周数 */
	public static int disGetWeek4now(){
		String strFname = " disGetWeek4now : ";
		int intRe = -1;
		try {
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(new Date());
			intRe = objCal.get(Calendar.WEEK_OF_YEAR);
		} catch(Exception ex) {
			intRe = -1;
			disOutputLog(strFname, ex);
		}
		return intRe;
	}
	/** 获取当前年 */
	public static int disGetYear4now(){
		String strFname = " disGetyear4now : ";
		int intRe = -1;
		try {
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(new Date());
			intRe = objCal.get(Calendar.YEAR);
		} catch(Exception ex) {
			intRe = -1;
			disOutputLog(strFname, ex);
		}
		return intRe;
	}
	
	public static void main(String[] args){
		System.out.println(DatePro.objSdf000.format(new Date()));
		System.out.println(DatePro.disGetYear4now());
		System.out.println(DatePro.disGetWeek4now());
		System.out.println(DatePro.disGetStrdate4NowObjSdf001());
	}

}
