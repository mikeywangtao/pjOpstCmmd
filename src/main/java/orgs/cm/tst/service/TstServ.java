package orgs.cm.tst.service;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orgs.cm.pMqp.pRuncmd.pQzGetimg.Runcmdpro_Getimg;
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
		

//		ArrayList<String> altPar = new ArrayList<>();
//		altPar.add("alt1");
//		altPar.add("alt2");
//		altPar.add("alt3");
//		Map<String, Object> mapPar = new HashMap<>();
//		mapPar.put("key", "val");
//		mapPar.put("keyy", "vall");
//		mapPar.put("keyyy", altPar);
//		
//		String strPar = JSON.toJSONString(mapPar);
//		HttpClientUtil.sendHttpPostJson("http://127.0.0.1:8080/pjOpStCmmd/tstCtrl/dishtpcReq", strPar);
		
//		runCmmd000.disPro000();
//		runCmmd001.disPro000();
//		runCmmd002.disPro000();
		Runcmdpro_Getimg objRcGetimg = new Runcmdpro_Getimg();
		objRcGetimg.disRuncmdPro();
		
//		try {
//			for(int i=0; i<2000; i++){
//				
//				Runcmdpro_Getimg objRcGetimg = new Runcmdpro_Getimg();
//				objRcGetimg.disRuncmdPro();
//				Thread.sleep(3000);
//				Runcmdpro_Getflv objRuncmdpro_Getflv = new Runcmdpro_Getflv();
//				objRuncmdpro_Getflv.disRuncmdPro();
//				Thread.sleep(3000);
//				Runcmdpro_Getnetw objRuncmdpro_Getnetw = new Runcmdpro_Getnetw();
//				objRuncmdpro_Getnetw.disRuncmdPro();
//				
//				System.out.println(" disRuncmdPro Run ----" + i);
//				Thread.sleep(10000);
//			}
//		} catch(Exception ex) {
//			long lonFlg = System.currentTimeMillis();
//			logger.error(strCname + strFname + ex + "||" + lonFlg);
//			StackTraceElement[] subSte = ex.getStackTrace();
//			for(int i=0; i<subSte.length; i++){
//				logger.error(
//						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
//			}
//		}

		return strRe;
	}
}
