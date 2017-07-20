package orgs.cm.pMqp.pDbpro;

import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pDblst.DbContManage;

/**
 * 检查信息记录表是否存在
 * */
public class DbInfotablePro4Cmmd {

	private static final String strCname = DbInfotablePro4Cmmd.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private static Statement objStmt = null;
	private static final String strDbflg = "Cmdpool";
	private static String strBusname = null;
	private static String strYear = null;
	private static String strWeeknum = null;
	
//	private DbInfotablePro(){}
//	public DbInfotablePro(String strBusnamep){
//		strBusname = strBusnamep;
//		super.disGetStmt(strDbflg);
//	}
	
	public static synchronized void disInfotablePro(String strBusnamep){
		String strFname = " disInfotablePro : ";
		try {
			if(strBusnamep!=null && strBusnamep.trim().length()>0){
				strYear = DatePro.disGetYear4now()+"";
				strWeeknum = DatePro.disGetWeek4now()+"";
				strBusname = strBusnamep;
				disGetStmt(strDbflg);
				disCpCheck();
				disRunCheck();
				strBusname = null;
				strWeeknum = null;
				strYear = null;
			}
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		} finally{
			disStmtdel();
		}
	}
	
	private static void disRunCheck(){
		String strFname = " disRunCheck : ";
		String[] subStrSql = null;
		boolean booCheck = true;
		
		try{
			if(objStmt==null){
				return ;
			}
			if(strYear!=null && strYear.trim().length()>0
					&& strWeeknum!=null && strWeeknum.trim().length()>0){
				String strSqlCheck = DbInfotableAttrs.strSqltemp_Se_CheckinfoRuntab;
				strSqlCheck = strSqlCheck.replace("YYYY", strYear);
				strSqlCheck = strSqlCheck.replace("XX", strWeeknum);
				strSqlCheck = strSqlCheck.replace("CCCCCC", strBusname);
			
				java.sql.ResultSet objRes = objStmt.executeQuery(strSqlCheck);
				while(objRes.next()){
					Object objCnts = objRes.getObject("CNTS");
					int intCnts = Integer.parseInt(objCnts.toString());
					if(intCnts!=0){
						booCheck = false;
					}
					break;
				}
				if(booCheck){
					subStrSql = DbInfotableAttrs.subStrSqlTemp_Cr_Runtab;
					if(subStrSql!=null && subStrSql.length>0){
						for(String strSql : subStrSql){
							strSql = strSql.replace("YYYY", strYear);
							strSql = strSql.replace("XX", strWeeknum);
							strSql = strSql.replace("CCCCCC", strBusname);
						
							objStmt.execute(strSql);
							strSql = null;
						}
					}
					subStrSql = null;
				}
			}
		} catch(Exception ex){
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		} 
	}
	private static void disCpCheck(){
		String strFname = " disCpCheck : ";
		String[] subStrSql = null;
		boolean booCheck = true;
		
		try{
			if(objStmt==null){
				return ;
			}
			if(strYear!=null && strYear.trim().length()>0
					&& strWeeknum!=null && strWeeknum.trim().length()>0){
				String strSqlCheck = DbInfotableAttrs.strSqltemp_Se_CheckinfoCptab;
				strSqlCheck = strSqlCheck.replace("YYYY", strYear);
				strSqlCheck = strSqlCheck.replace("XX", strWeeknum);
				strSqlCheck = strSqlCheck.replace("CCCCCC", strBusname);
			
				java.sql.ResultSet objRes = objStmt.executeQuery(strSqlCheck);
				while(objRes.next()){
					Object objCnts = objRes.getObject("CNTS");
					int intCnts = Integer.parseInt(objCnts.toString());
					if(intCnts!=0){
						booCheck = false;
					}
					break;
				}
				if(booCheck){
					subStrSql = DbInfotableAttrs.subStrSqlTemp_Cr_Cptab;
					if(subStrSql!=null && subStrSql.length>0){
						for(String strSql : subStrSql){
							strSql = strSql.replace("YYYY", strYear);
							strSql = strSql.replace("XX", strWeeknum);
							strSql = strSql.replace("CCCCCC", strBusname);
						
							objStmt.execute(strSql);
							strSql = null;
						}
					}
					subStrSql = null;
				}
			}
		} catch(Exception ex){
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		} 
	}
	
	private static void disGetStmt(String strDbFlgp){
		objStmt = DbContManage.getDbstmt(strDbFlgp);
	}
	
	private static void disStmtdel(){
		String strFname = " disStmtdel : ";
		try {
			if(objStmt!=null && !objStmt.isClosed()){
				objStmt.close();
				objStmt = null;
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
