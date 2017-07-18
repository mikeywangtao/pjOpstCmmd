package orgs.cm.pMqp.pDblst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;
import orgs.cm.pMqp.pLis.ReceiveMsgProces;

/** 数据库链接对象 */
public class DbCont {

	private final String strCname = ReceiveMsgProces.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private Connection cont = null;
	private String strDbFlg = null;
	
	private DbCont(){}
	
	public DbCont(String strDbFlgp){
		this.strDbFlg = strDbFlgp;
		disInitCont();
	}
	
	private void disInitCont(){
		String strFname = " disInitCont :";
		try {
			logger.info(strCname + strFname + " Start!");
			Properties objPptJdbc = Propertiesmap.getPpt("jdbc.properties");
			if(strDbFlg!=null && strDbFlg.trim().length()>0
					&& objPptJdbc!=null){
				String strDriver = objPptJdbc.getProperty(strDbFlg + ".driverClassName");
				String strUrl    = objPptJdbc.getProperty(strDbFlg + ".url");
				String strUser   = objPptJdbc.getProperty(strDbFlg + ".username");
				String strPass   = objPptJdbc.getProperty(strDbFlg + ".password");
				if(strDriver!=null && strDriver.trim().length()>0
						&& strUrl!=null && strUrl.trim().length()>0
						&& strUser!=null && strUser.trim().length()>0
						&& strPass!=null && strPass.trim().length()>0){
					Class.forName(strDriver);
					cont = DriverManager.getConnection(strUrl, strUser, strPass);
				}
			}
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
		
	}
	
	public Statement getSmt(){
		String strFname = " getSmt : ";
		Statement objRe = null;
		try {
			logger.info(strCname + strFname + " Start!");
			if(cont!=null && !cont.isClosed()){
				objRe = cont.createStatement(); 
			} else {
				objRe = null;
			}
		} catch(Exception ex) {
			objRe = null;
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
		return objRe;
	}
}
