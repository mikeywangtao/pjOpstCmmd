package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.pjCreate00.RunAfter_Create00;
import orgs.cm.pMqp.pRuncmd.pjCreate00.RunBefore_Create00;
import orgs.cm.pMqp.pRuncmd.pjCreate00.RunCmd_Create00;
import orgs.cm.pMqp.pRuncmd.pjCreate00.RunPrepare_Create00;
import orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00;

public class Runcmdpro_Getimg extends AbsRuncmdPro {

	private final String strCname = Runcmdpro_Create00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private static final String strInfoflg = "imgJob";
	
	private HashMap<String, Object> hmpPar;
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		try {
			objPrepare = new RunPrepare_Getimg();
			objBefore = new RunBefore_Getimg();
			objCmd = new RunCmd_Getimg();
			objAfter = new RunAfter_Getimg();
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