package orgs.cm.pMqp.pDblst;


import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * repetition 重复, 反复
 * 循环对象池
 * */
public class ObjRepePool01<T> {
	private final String strCname = ObjRepePool01.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private int intBSize;
	private Vector<T> altObj;

	private T t;
	private ObjRepePool01(){}
	/**
	 * @param num 缓存对象数
	 * @param cls 缓存对象Class
	 * */
	public ObjRepePool01(String strNum, T obj){
		String strFname = " ThreadPool01 : ";
		try {
			if(strNum!=null && strNum.trim().length()>0){
				this.intBSize = strNum==null? 0:Integer.parseInt(strNum);
				if(this.intBSize>0){
					this.t = obj;
					if(this.altObj==null){
						this.altObj = new Vector<T>(intBSize); 
					}
					altObj.add(obj);
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
			throw ex;
		}
	}
	
	public void setObject(T t){
		String strFname = " setObject : ";
		try{
			if(t!=null){
				this.t = t;
				if(this.altObj==null){
					this.altObj = new Vector<T>(intBSize); 
				}
				synchronized (this.altObj) {
					if(altObj.size()<intBSize){
						altObj.add(t);
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
			throw ex;
		}
	}

	public T getObject() {
		String strFname = " getObject ; ";
		T rnbRe = null;
		try {
			synchronized (altObj) {
				if (altObj.size()>0) {
					rnbRe = altObj.remove(0);
					if(rnbRe==null){
						rnbRe = (T)t.getClass().newInstance();
					}
				} else {
					rnbRe = (T)t.getClass().newInstance();
				} 
			}
		} catch (Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
		return rnbRe;
	}
	public boolean reObject (T obj){
		String strFname = " reObject ; ";
		boolean booRe = false;
		try{
			synchronized (altObj) {
				if(altObj.size()<intBSize){
					altObj.add(obj);
					obj = null;
					booRe = true;
				} else {
					obj = null;
					booRe = false;
				}
			}
		} catch(Exception ex) {
			booRe = false;
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
			throw ex;
		}
		return booRe;
	}
}
