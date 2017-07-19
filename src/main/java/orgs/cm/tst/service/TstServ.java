package orgs.cm.tst.service;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orgs.cm.pMqp.pRuncmd.pQzGetflv.RunCmd_Getflv;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.Runcmdpro_Getimg;
import orgs.cm.pMqp.pRuncmd.pQzGetnetw.RunCmd_Getnetw;
import orgs.cm.tst.dao.TstDao;
import orgs.cm.tst.model.RunCmmd000;
import orgs.cm.tst.model.RunCmmd001;
import orgs.cm.tst.model.RunCmmd002;

@Service("tstServ")
public class TstServ {
	
	private final String strCname = TstServ.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	@Autowired
	private TstDao tstDao;
	@Autowired
	private RunCmmd000 runCmmd000;
	@Autowired
	private RunCmmd001 runCmmd001;
	@Autowired
	private RunCmmd002 runCmmd002;
	
	public String disTstServ_Pro(LinkedHashMap<String, Object> lhpParp){
		String strFname = " disTstServ_Pro : ";
		String strRe = "";
		
		System.out.println(strCname + strFname);
		tstDao.disTstDao_Pro(lhpParp);
		
		//
//		runCmmd000.disPro000();
//		runCmmd001.disPro000();
//		runCmmd002.disPro000();
		
		try {
			for(int i=0; i<2000; i++){
				
				Runcmdpro_Getimg objRcGetimg = new Runcmdpro_Getimg();
				objRcGetimg.disRuncmdPro();
				Thread.sleep(3000);
				RunCmd_Getflv objRunCmd_Getflv = new RunCmd_Getflv();
				objRunCmd_Getflv.disRunCmd();
				Thread.sleep(3000);
				RunCmd_Getnetw objRunCmd_Getnetw = new RunCmd_Getnetw();
				objRunCmd_Getnetw.disRunCmd();
				
				System.out.println(" disRuncmdPro Run ----" + i);
				Thread.sleep(10000);
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

		return strRe;
	}
}
