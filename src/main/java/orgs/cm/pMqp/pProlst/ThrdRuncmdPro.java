package orgs.cm.pMqp.pProlst;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.pDest00.Runcmdpro_Dest00;
import orgs.cm.pMqp.pRuncmd.pEdit00.Runcmdpro_Edit00;
import orgs.cm.pMqp.pRuncmd.pStatrt00.Runcmdpro_Start00;
import orgs.cm.pMqp.pRuncmd.pStop00.Runcmdpro_Stop00;
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
			if(mapMsg.containsKey("^req_type^")
					&& mapMsg.containsKey("^req_subtype^")){
				Object objProflg = mapMsg.get("^req_type^");
				strProflg = objProflg==null? null:objProflg.toString();
				Object objSubProflg = mapMsg.get("^req_subtype^");
				strSubProflg = objSubProflg==null? null:objSubProflg.toString();
				
				//create bus:subbbus
				//get filepath from db 
//				AbsRuncmdPro objRuncmdPro = RuncmdproFactory.disGetObj(null);
//				objRuncmdPro.disRuncmdPro();
/*
{
^pname^=1, 
^ansid^=1, 
msgId=2017073114024650501533, 
^intImaId^=12, 
^strSshKey^=1, 
vmname=vm, 
^uname^=1, 
vmids=, 
^strVmUser^=1, 
^intNwId^=1, 
^netwids^=aedbece2-0b64-4879-94e5-461439cd6930, 
usr_name=username, 
^authurl^=1, 
^flvids^=0, ^imgapi^=1, 
^uksids^=1, 
sysflg=cms, devids=, 
^ideapi^=1, shell_allpath=, 
^intTemId^=0, 
^pdom^=1, 
^pass^=1, 
^customerids^=1, 
^imgids^=4c367d93-fbe5-4c58-ac85-f7aab0310740, 
serverTarget=test, 
login_name=wode, 
^udom^=1, 
^anscmmd^=test, 
^req_type^=CREATE, 
^req_subtype^=CREATE00, 
^strVmPassword^=1}
				 */
//				HashMap<String, Object> hmpInputPar = new HashMap<>();
//				hmpInputPar.put("^anscmmd^", "openstack");
//				hmpInputPar.put("^ansinfo^", "ansinfo......");
//				
//				hmpInputPar.put("^req_type^", "CREATE");
//				hmpInputPar.put("^req_subtype^", "CREATE00");
//				
//				hmpInputPar.put("^customerids^", "20170724000");
//				
//				hmpInputPar.put("^pdom^", "Default");
//				hmpInputPar.put("^udom^", "Default");
//				hmpInputPar.put("^pname^", "admin");
//				hmpInputPar.put("^uname^", "admin");
//				hmpInputPar.put("^pass^", "admin");
//				hmpInputPar.put("^authurl^", "http://test-controller:5000/v3");
//				hmpInputPar.put("^ideapi^", "3");
//				hmpInputPar.put("^imgapi^", "2");
				
				if("CREATE".equals(strProflg.trim())){
					AbsRuncmdPro objRcCreate00 = null;
					if("CREATE00".equals(strSubProflg.trim())){
						objRcCreate00 = new Runcmdpro_Create00();
						objRcCreate00.disSetPars(mapMsg);
//						objRcCreate00.disRuncmdPro();
					}
					if(objRcCreate00!=null){
						ThrdRunManage.chmthdrMang_RuncmdPro.get("CREATE").putThread2Mlt((Runnable)objRcCreate00);
					}
				}
				
				if("STOP".equals(strProflg.trim())){
					AbsRuncmdPro objRcStop00 = null;
					if("STOP00".equals(strSubProflg.trim())){
						objRcStop00 = new Runcmdpro_Stop00();
						objRcStop00.disSetPars(mapMsg);
//						objRcStop00.disRuncmdPro();
					}
					if(objRcStop00!=null){
						ThrdRunManage.chmthdrMang_RuncmdPro.get("SSRD").putThread2Mlt((Runnable)objRcStop00);
					}
				}
				
				if("START".equals(strProflg.trim())){
					AbsRuncmdPro objRcStart00 = null;
					if("START00".equals(strSubProflg.trim())){
						objRcStart00 = new Runcmdpro_Start00();
						objRcStart00.disSetPars(mapMsg);
//						objRcStop00.disRuncmdPro();
					}
					if(objRcStart00!=null){
						ThrdRunManage.chmthdrMang_RuncmdPro.get("SSRD").putThread2Mlt((Runnable)objRcStart00);
					}
				}
				
				if("DEST".equals(strProflg.trim())){
					AbsRuncmdPro objRcDest00 = null;
					if("DEST00".equals(strSubProflg.trim())){
						objRcDest00 = new Runcmdpro_Dest00();
						objRcDest00.disSetPars(mapMsg);
//						objRcStop00.disRuncmdPro();
					}
					if(objRcDest00!=null){
						ThrdRunManage.chmthdrMang_RuncmdPro.get("SSRD").putThread2Mlt((Runnable)objRcDest00);
					}
				}
				
				if("EDIT".equals(strProflg.trim())){
					AbsRuncmdPro objEdit00 = null;
					if("EDIT00".equals(strSubProflg.trim())){
						objEdit00 = new Runcmdpro_Edit00();
						objEdit00.disSetPars(mapMsg);
//						objRcStop00.disRuncmdPro();
					}
					if(objEdit00!=null){
						ThrdRunManage.chmthdrMang_RuncmdPro.get("SSRD").putThread2Mlt((Runnable)objEdit00);
					}
				}
				
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
