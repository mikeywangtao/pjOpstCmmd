package orgs.cm.pMqp.pComms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdsPro {
	private static final String strCname = IdsPro.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	/** 获取格式为:yyyyMMddHHmmssSXXXX的字符串。 */
	public static synchronized String disGetIds(){
		return DatePro.disGetStrdate4NowObjSdf001() + SeqPro.getNum();
	}
}
