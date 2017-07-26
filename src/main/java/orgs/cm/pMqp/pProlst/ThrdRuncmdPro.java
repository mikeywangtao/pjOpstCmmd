package orgs.cm.pMqp.pProlst;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.comm.RuncmdproFactory;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.Runcmdpro_Getimg;
import orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00;

/**
 * 获取cmd处理逻辑，并使runcmd排队。
 * */
public class ThrdRuncmdPro extends AbsThrdRuncmdPro {

	private final String strCname = ThrdRuncmdPro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> mapMsg;
	public void setMsg(HashMap<String, Object> mapMsgp){
		this.mapMsg = mapMsgp;
	}
	public void run() {
		String strFname = " run : ";
		String strProflg = null;
		String strSubProflg = null;
		
		try {
			logger.info(strCname + strFname + " Start!");
			if(mapMsg.containsKey("proflg")
					&& mapMsg.containsKey("subProflg")){
				Object objProflg = mapMsg.get("proflg");
				strProflg = objProflg==null? null:objProflg.toString();
				Object objSubProflg = mapMsg.get("subProflg");
				strSubProflg = objSubProflg==null? null:objSubProflg.toString();
				
				//create bus:subbbus
				//get filepath from db 
//				AbsRuncmdPro objRuncmdPro = RuncmdproFactory.disGetObj(null);
//				objRuncmdPro.disRuncmdPro();
				
				HashMap<String, Object> hmpInputPar = new HashMap<>();
				hmpInputPar.put("^anscmmd^", "openstack");
				hmpInputPar.put("^ansinfo^", "ansinfo......");
				
				hmpInputPar.put("^req_type^", "CREATE");
				hmpInputPar.put("^req_subtype^", "CREATE00");
				
				hmpInputPar.put("^customerids^", "20170724000");
				
				hmpInputPar.put("^pdom^", "Default");
				hmpInputPar.put("^udom^", "Default");
				hmpInputPar.put("^pname^", "admin");
				hmpInputPar.put("^uname^", "admin");
				hmpInputPar.put("^pass^", "admin");
				hmpInputPar.put("^authurl^", "http://test-controller:5000/v3");
				hmpInputPar.put("^ideapi^", "3");
				hmpInputPar.put("^imgapi^", "2");
				
				AbsRuncmdPro objRcCreate00 = new Runcmdpro_Create00();
				objRcCreate00.disRuncmdPro();
//				AbsRuncmdPro objRuncmdPro  = new Runcmdpro_Getimg();
//				objRuncmdPro.disSetPars(hmpInputPar);
//				objRuncmdPro.disRuncmdPro();
				
				logger.info(strCname + strFname + " MsgInfo ----!" + mapMsg);
				
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
	

}
