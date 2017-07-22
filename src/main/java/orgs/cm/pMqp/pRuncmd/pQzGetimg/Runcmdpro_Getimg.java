package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.util.HashMap;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00;

public class Runcmdpro_Getimg extends AbsRuncmdPro {

	private final String strCname = Runcmdpro_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private static final String strInfoflg = "imgJob";
	
	private HashMap<String, Object> hmpPar = new HashMap<>();
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		try {
			UUID objUuid = UUID.randomUUID();
			HashMap<String, String> hmpAllInp = new HashMap<>();
			
			hmpAllInp.put("^anscmmd^", "openstack");
			hmpAllInp.put("^ansinfo^", "ansinfo......");
			
			hmpAllInp.put("^req_type^", "qz");
			hmpAllInp.put("^req_subtype^", "getimg");
			
			hmpAllInp.put("^pdom^", "Default");
			hmpAllInp.put("^udom^", "Default");
			hmpAllInp.put("^pname^", "admin");
			hmpAllInp.put("^uname^", "admin");
			hmpAllInp.put("^pass^", "admin");
			hmpAllInp.put("^authurl^", "http://test-controller:5000/v3");
			hmpAllInp.put("^ideapi^", "3");
			hmpAllInp.put("^imgapi^", "2");
			

			
			hmpPar.put(ProcessAttrs.strParmapKey_Inpars, hmpAllInp);
			hmpPar.put("^anscmmd^", "openstack");
			hmpPar.put("^ansinfo^", "ansinfo......");
			hmpPar.put(ProcessAttrs.strInfoKey_Cpuuid, objUuid.toString().replaceAll("-", ""));
			hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "f");
			hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunShCmmd, null);
			hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFileroot, 
					"/home/anshells/" + DatePro.disGetYear4now()+DatePro.disGetWeek4now());
			hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilename, 
					"/" 
					+ DatePro.disGetStrdate4NowObjSdf001() 
					+ "_" + hmpAllInp.get("^req_type^").toString()
					+ hmpAllInp.get("^req_subtype^").toString() 
					+ ".sh");
			
			objPrepare = new RunPrepare_Getimg();
			objPrepare.disSetAll(hmpPar);
			objBefore = new RunBefore_Getimg();
			objBefore.disSetAll(hmpPar);
			objCmd = new RunCmd_Getimg();
			objCmd.disSetAll(hmpPar);
			objAfter = new RunAfter_Getimg();
			objAfter.disSetAll(hmpPar);
			super.disRunPrepare(objPrepare);
			super.disRunBefre(objBefore);
			super.disRunCmd(objCmd);
			super.dusRunAfter(objAfter);
//			if(hmpPar!=null && hmpPar.size()>0){
//				objPrepare = new RunPrepare_Getimg();
//				objBefore = new RunBefore_Getimg();
//				objCmd = new RunCmd_Getimg();
//				objAfter = new RunAfter_Getimg();
//				
//				super.disRunPrepare(objPrepare);
//				super.disRunBefre(objBefore);
//				super.disRunCmd(objCmd);
//				super.dusRunAfter(objAfter);
//			} else {
//				throw new Exception("hmpPar Error ! is null or is empty!");
//			}
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
	
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}