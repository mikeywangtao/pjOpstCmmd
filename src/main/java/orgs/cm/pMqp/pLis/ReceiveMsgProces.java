package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;
import orgs.cm.pMqp.pMqlst.ThrdReceiveMsg;
import orgs.cm.pMqp.pMqlst.ThreadReceiveMsgManage;

/**
 * lis调用
 * 1.构建Mq监听集合，并启动Mq监听。
 * 2.通过web增加Mq监听、启动，并置入集合中。
 * */
public class ReceiveMsgProces {
	
	private final String strCname = ReceiveMsgProces.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	/**
	 * Listener启动时调用
	 * Mq监听对象集合
	 * */
	public void disInitReceive(){
		String strFname = " disSetReceive : ";
		try{
			logger.info(strCname + strFname + " Start!");
			String strServerTarget = null;
			String strSysFlgs = null;
			Object objServerTarget = Propertiesmap.getPptAttr("sysTarget.properties", "serverTarget");
			Object objSysFlgs = Propertiesmap.getPptAttr("sysTarget.properties", "sysflgs");
			strServerTarget = objServerTarget==null? null:objServerTarget.toString();
			strSysFlgs = objSysFlgs==null? null:objSysFlgs.toString();
			
			if(strServerTarget!=null && strServerTarget.trim().length()>0
					&& strSysFlgs!=null && strSysFlgs.trim().length()>0){
				String[] subStrSysFlg = strSysFlgs.split(",");
				if(subStrSysFlg!=null && subStrSysFlg.length>0){
					for(String flg : subStrSysFlg){
						if(flg!=null && flg.trim().length()>0){
							ThreadReceiveMsgManage.chmReceive.put(flg+","+strServerTarget, new ThrdReceiveMsg());
							ThreadReceiveMsgManage.chmReceive.get(flg+","+strServerTarget).setFlg(flg);
							ThreadReceiveMsgManage.chmReceive.get(flg+","+strServerTarget).start();
						}
					}
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
	
//	/**
//	 * 追加Receive监听及处理
//	 * */
//	public boolean disAddReceive(String strResp){
//		String strFname = " disAddReceive : ";
//		boolean booRe = false;
//		try{
//			String strServerTarget = null;
//			strServerTarget = PropetiesManage.getPptSysFlg();
//			if(strServerTarget!=null && strServerTarget.trim().length()>0){
//				if(strResp!=null && strResp.trim().length()>0
//						&& ThreadReceiveMsgManage.chmReceive!=null){
//					ThreadReceiveMsgManage.chmReceive.put(strResp+strServerTarget, new ThrdReceiveMsg());
//					ThreadReceiveMsgManage.chmReceive.get(strResp+strServerTarget).setFlg(strResp);
//					ThreadReceiveMsgManage.chmReceive.get(strResp+strServerTarget).start();
//					booRe = true;
//				}
//			}
//		} catch(Exception ex) {
//			long lonFlg = System.currentTimeMillis();
//			logger.error(strCname + strFname + ex + "||" + lonFlg);
//			StackTraceElement[] subSte = ex.getStackTrace();
//			for(int i=0; i<subSte.length; i++){
//				logger.error(
//						subSte[i].getClassName() 
//						+ subSte[i].getMethodName() 
//						+ ":" + subSte[i].getLineNumber() 
//						+ "||" + lonFlg );
//			}
//			booRe = false;
//		}
//		return booRe;
//	}
}
