package orgs.cm.pMqp.pProlst;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.comm.RuncmdproFactory;

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
				AbsRuncmdPro objRuncmdPro = RuncmdproFactory.disGetObj(null);
				objRuncmdPro.disRuncmdPro();

				
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
