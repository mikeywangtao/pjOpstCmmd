package orgs.cm.pMqp.pRuncmd.pCreate00;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;

/**
 * 创建 Vm 虚拟机。
 * 创建一个。
 * */
public class Runcmdpro_Create00 extends AbsRuncmdPro {

	private final String strCname = Runcmdpro_Create00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpPar;
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		try {
			if(hmpPar!=null && hmpPar.size()>0){
				objPrepare = new RunPrepare_Create00();
				objBefore = new RunBefore_Create00();
				objCmd = new RunCmd_Create00();
				objAfter = new RunAfter_Create00();
				
				super.disRunPrepare(objPrepare);
				super.disRunBefre(objBefore);
				super.disRunCmd(objCmd);
				super.dusRunAfter(objAfter);
			} else {
				throw new Exception("hmpPar Error ! is null or is empty!");
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
	
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}
