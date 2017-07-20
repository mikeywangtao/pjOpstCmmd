package orgs.cm.pMqp.pDbpro;

import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pDblst.DbContManage;

public class AbsDbpro {

	private final String strCname = AbsDbpro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	protected Statement objStmt = null;
	
	public void disGetStmt(String strDbFlgp){
		objStmt = DbContManage.getDbstmt(strDbFlgp);
	}
	
	public void disStmtdel(){
		String strFname = " disStmtdel : ";
		try {
			if(this.objStmt!=null && !this.objStmt.isClosed()){
				this.objStmt.close();
				this.objStmt = null;
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
