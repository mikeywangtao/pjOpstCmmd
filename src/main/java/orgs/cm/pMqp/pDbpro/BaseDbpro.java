package orgs.cm.pMqp.pDbpro;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pDblst.DbContManage;

/** 无事物数据库操作 */
public class BaseDbpro extends AbsDbpro implements IBaseDbpro {

	private final String strCname = BaseDbpro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private void disBasepro(){}
	
	public BaseDbpro(String strDbFlgp){
		disGetStmt(strDbFlgp);
	}
	
	/** 使用单一sql查询返回ArrayList<LinkedHashMap<String, Object>>。 */
	public ArrayList<LinkedHashMap<String, Object>> disSearch(String strSqlp){
		String strFname = " disSearch : ";
		ResultSet objRes = null;
		ArrayList<String> altCol = null;
		LinkedHashMap<String, Object> lhpRow = new LinkedHashMap<>();
		ArrayList<LinkedHashMap<String, Object>> altRe = new ArrayList<>();
		try {
			if(objStmt==null && strDbFlg!=null && strDbFlg.trim().length()>0){
				objStmt = DbContManage.getDbstmt(strDbFlg);
			}
			if(objStmt!=null 
					&& strSqlp!=null && strSqlp.trim().length()>0){
				objRes = objStmt.executeQuery(strSqlp);
				while(objRes.next()){
					if(altCol==null){
						altCol = new ArrayList<>();
						int intColCnts = objRes.getMetaData().getColumnCount();
						if(intColCnts>0){
							for(int i=1; i<=intColCnts; i++){
								altCol.add(objRes.getMetaData().getColumnLabel(i));
							}
						}
					}
					for(String strkey : altCol){
						lhpRow.put(strkey, objRes.getObject(strkey));
					}
					altRe.add((LinkedHashMap<String, Object>)lhpRow.clone());
					lhpRow.clear();
				}
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		} finally {
			try {
				if(!objRes.isClosed()){
					objRes.close();
					objRes = null;
				}
				disStmtdel();
			} catch(Exception ex) {
				disOutputLog(strFname, ex);
			}
		}
		return altRe;
	}
	
	private void disOutputLog(String strFnamep, Exception exp){
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}
}
