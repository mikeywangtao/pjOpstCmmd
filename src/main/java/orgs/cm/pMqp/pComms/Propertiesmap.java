package orgs.cm.pMqp.pComms;

import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Properties对象集合
 * */
public class Propertiesmap {

	private static final String strCname = Propertiesmap.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private static HashMap<String, Properties> hmpPpt = new HashMap<String, Properties>();
	
	/**
	 * 通过Pptfile的path获取Ppt
	 * */
	public static void setPpt(String strPptPathp){
		String strFname = " setPpt : ";
		try {
			logger.info(strCname + strFname + " Start!");
			if(strPptPathp!=null && strPptPathp.trim().length()>0){
				GetProperties objGetProperties = new GetProperties();
				Properties pptTmp = objGetProperties.disGet(strPptPathp);
				if(pptTmp!=null){
					String strKey = strPptPathp.substring(strPptPathp.lastIndexOf("/")+1, strPptPathp.length());
					if(strKey!=null && strKey.trim().length()>0){
						hmpPpt.put(strKey, pptTmp);
					} else {
						logger.info(strCname + strFname + " 获取Ppt的name失败! ----" + strPptPathp + " : " + strKey);
					}
				} else {
					logger.info(strCname + strFname + " 获取Ppt失败! ----" + strPptPathp);
				}
			} else {
				logger.info(strCname + strFname + " Ppt路径异常！ ----" + strPptPathp);
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
	/**
	 * 指定ppt获取其指定attr
	 * */
	public static Object getPptAttr(String strPptFlgp, String strAttrFlgp){
		String strFname = " getPptAttr : ";
		Object objRe = null;
		Properties pptRe = null;
		try {
			logger.info(strCname + strFname + " Start!");
			if(strPptFlgp!=null && strPptFlgp.trim().length()>0
					&& strAttrFlgp!=null && strAttrFlgp.trim().length()>0){
				if(hmpPpt.containsKey(strPptFlgp)){
					pptRe = hmpPpt.get(strPptFlgp);
					if(pptRe!=null && pptRe.containsKey(strAttrFlgp)){
						objRe = pptRe.getProperty(strAttrFlgp);
					} else {
						logger.info(strCname + strFname + strAttrFlgp +" 此Attr未在Ppt中存在!");
					}
				} else {
					logger.info(strCname + strFname + strPptFlgp +" 此标记未在map中存在!");
				}
			} else {
				logger.info(strCname + strFname + strPptFlgp + " 标记非法(为空或不存在)!");
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
		return objRe;
	}
	/**
	 * 获取指定flg的ppt
	 * */
	public static Properties getPpt(String strFlgp){
		String strFname = " getPpt : ";
		Properties pptRe = null;
		try {
			logger.info(strCname + strFname + " Start!");
			if(strFlgp!=null && strFlgp.trim().length()>0){
				if(hmpPpt.containsKey(strFlgp)){
					pptRe = hmpPpt.get(strFlgp);
				} else {
					logger.info(strCname + strFname + strFlgp +" 此标记未在map中存在!");
				}
			} else {
				logger.info(strCname + strFname + strFlgp + " 标记非法(为空或不存在)!");
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
		return pptRe;
	}
}
