package orgs.cm.pMqp.pComms;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00;

public class DateFlg {
	private static final String strCname = Runcmdpro_Create00.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private static SimpleDateFormat objSdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String strStartDate = "2017-06-04";
	private static String strEndDate = "2017-06-05";
	
	private static Date objDateS = new Date();
	private static Date objDateE = new Date();
	
	public static int disGetLoop(){
		String strFname = " disGetLoop : ";
		int intRe = 0;
		try{
			Date date1 = objSdf.parse(strEndDate);
			Date date2 = new Date();
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);

			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			if (year1 != year2) {// 同一年
				int timeDistance = 0;
				for (int i = year1; i < year2; i++) {
					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
						timeDistance += 366;
					} else {// 不是闰年
						timeDistance += 365;
					}
				}
				return timeDistance + (day2 - day1);
			} else {// 不同年
				System.out.println("判断day2 - day1 : " + (day2 - day1));
				return day2 - day1;
			}
		} catch(Exception ex) {
			intRe = 0;
			logger.error(strCname + strFname + ex);
		}
		return intRe;
	}
	public static Long[] disGetLSe(){
		String strFname = " disGetSe : ";
		Long[] subRe = new Long[2];
		try {
			Calendar objCalS = Calendar.getInstance();
			objDateS = objSdf.parse(strStartDate);
			objCalS.setTime(objDateS);
			objCalS.add(Calendar.DAY_OF_MONTH, 1);
			objDateS = objCalS.getTime();
			subRe[0] = objDateS.getTime();
			strStartDate = objSdf.format(objDateS);
			
			Calendar objCalE = Calendar.getInstance();
			objDateE = objSdf.parse(strEndDate);
			objCalE.setTime(objDateE);
			objCalE.add(Calendar.DAY_OF_MONTH, 1);
			objDateE = objCalE.getTime();
			subRe[1] = objDateE.getTime();
			strEndDate = objSdf.format(objDateE);
			
		} catch(Exception ex) {
			logger.error(strCname + strFname + ex);
		}
		return subRe;
	} 
	
	public static String[] disGetSSe(){
		String strFname = " disGetSSe : ";
		String[] subRe = new String[2];
		
		try {
			subRe[0] = strStartDate;
			subRe[1] = strEndDate;
		} catch(Exception ex) {
			logger.error(strCname + strFname + ex);
		}
		return subRe;
	}
}
