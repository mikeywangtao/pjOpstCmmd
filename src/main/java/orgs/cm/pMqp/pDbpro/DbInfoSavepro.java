package orgs.cm.pMqp.pDbpro;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDblst.DbContManage;

public class DbInfoSavepro {

	private static final String strCname = DbInfotablePro4Cmmd.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
	private Statement objStmt = null;
	private String strBusname = null;
	private String strYear = null;
	private String strWeeknum = null;
	
	private DbInfoSavepro(){}
	
	public DbInfoSavepro(String strDbflgp, String strBusnamep){
		disGetStmt(DbproAttrs.strDbflg_Cmd);
		strBusname = strBusnamep;
	}
	
	private void disGetStmt(String strDbFlgp){
		objStmt = DbContManage.getDbstmt(strDbFlgp);
	}
	
	public int disSaveCpinfo(ArrayList<LinkedHashMap<String, String>> altRuncp){
		String strFname = " disSaveCpinfo : ";
		int intNum = 0;
		try {
			if(altRuncp!=null && altRuncp.size()>0){
				strYear = DatePro.disGetYear4now()+"";
				strWeeknum = DatePro.disGetWeek4now()+"";
				if(strYear!=null && strYear.trim().length()>0
						&& strWeeknum!=null && strWeeknum.trim().length()>0
						&& strBusname!=null && strBusname.trim().length()>0){
						String strSqlCheck = DbInfoSaveAttrs.strSqltemp_Se_SaveCpinfo;
						if(strSqlCheck!=null && strSqlCheck.trim().length()>0){
							LinkedHashMap<String, String> mapRow = altRuncp.get(0);
							if(mapRow!=null && mapRow.size()>0 
									&& mapRow.containsKey("cp_ids")
									&& mapRow.containsKey("cpcls")
									&& mapRow.containsKey("customer")
									&& mapRow.containsKey("ansible_ids")
									&& mapRow.containsKey("ansible_info")
									&& mapRow.containsKey("cmd_tpye")
									&& mapRow.containsKey("cmd_subtype")
									&& mapRow.containsKey("cmd_request")
									&& mapRow.containsKey("cmd_inputdt")
									&& mapRow.containsKey("cpuuid")
									&& mapRow.containsKey("cmdrundt")){
								/*
			+ " INSERT INTO tYYYYXX_cmdCCCCCC_cpoutput ( "
			+ " cp_ids,		cpcls,			customer,		ansible_ids,	ansible_info, "
			+ " cmd_tpye,	cmd_subtype,	cmd_request,	cmd_inputdt,	cpuuid  "
			+ " ceratedt,cmdrundt "
			+ " ) VALUES ( "
			+ " '^cp_ids^',   '^cpcls^',       '^customer^',    '^ansible_ids^', '^ansible_info^', "
			+ " '^cmd_tpye^', '^cmd_subtype^', '^cmd_request^', '^cmd_inputdt^', '^cpuuid^',       "
			+ " '^ceratedt^', '^cmdrundt^' "
			+ " ) ";
								 
{cp_ids=20170731173200912, 
cpcls=orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00, 
customer=1, 
ansible_ids=1, 
ansible_info=openstack, 
cmd_tpye=CREATE, 
cmd_subtype=CREATE00, 
cmd_request={^pname^=admin, ^ansid^=1, msgId=2017073117053158555261, ^intImaId^=12, ^strSshKey^=1, ^uname^=admin, ^strVmUser^=1, ^shell_allpath^=, ^intNwId^=1, ^netwids^=aedbece2-0b64-4879-94e5-461439cd6930, usr_name=username, ^authurl^=http://test-controller:5000/v3, ^flvids^=0, ^imgapi^=2, ^devname^=dev-1e6d62c9c6c7442391f0dcc8ae08dcee, ^uksids^=1, sysflg=cms, ^vmids^=null, ^ideapi^=3, ^intTemId^=0, ^pdom^=Default, ^pass^=admin, ^customerids^=1, ^imgids^=20022a68-bc87-462d-ba6c-af6570ba839e, serverTarget=test, login_name=wode, ^udom^=Default, ^anscmmd^=openstack, ^vmname^=vm7, ^req_type^=CREATE, ^devids^=null, ^req_subtype^=CREATE00, ^strVmPassword^=1}, 
cmd_inputdt=20170731173200912, 
cpuuid=1e6d62c9c6c7442391f0dcc8ae08dcee, 
cmdrundt=20170731173200912, 
info=orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00 disRuncmdPro :  Start!, 
type=point, 
flg=runFlg, 
subflg=start}

							 */
							strSqlCheck = strSqlCheck.replace("YYYY", strYear);
							strSqlCheck = strSqlCheck.replace("XX", strWeeknum);
							strSqlCheck = strSqlCheck.replace("CCCCCC", strBusname);
							strSqlCheck = strSqlCheck.replaceAll("\\^cp_ids\\^", mapRow.get("cp_ids"));
							strSqlCheck = strSqlCheck.replaceAll("\\^cpcls\\^", mapRow.get("cpcls"));
							strSqlCheck = strSqlCheck.replaceAll("\\^customer\\^", mapRow.get("customer"));
							strSqlCheck = strSqlCheck.replaceAll("\\^ansible_ids\\^", mapRow.get("ansible_ids"));
							strSqlCheck = strSqlCheck.replaceAll("\\^ansible_info\\^", mapRow.get("ansible_info"));
							strSqlCheck = strSqlCheck.replaceAll("\\^cmd_tpye\\^", mapRow.get("cmd_tpye"));
							strSqlCheck = strSqlCheck.replaceAll("\\^cmd_subtype\\^", mapRow.get("cmd_subtype"));
							strSqlCheck = strSqlCheck.replaceAll("\\^cpuuid\\^", mapRow.get("cpuuid"));
							strSqlCheck = strSqlCheck.replaceAll("\\^cmd_request\\^", mapRow.get("cmd_request"));
							strSqlCheck = strSqlCheck.replaceAll("\\^cmd_inputdt\\^", mapRow.get("cmd_inputdt"));
							strSqlCheck = strSqlCheck.replaceAll("\\^ceratedt\\^", DatePro.disGetStrdate4NowObjSdf001());
							strSqlCheck = strSqlCheck.replaceAll("\\^cmdrundt\\^", mapRow.get(ProcessAttrs.strInfoKey_Rundt));
							logger.info(strCname + strFname + " SQL ----" + strSqlCheck);
						}
						if(objStmt!=null 
								&& strSqlCheck!=null && strSqlCheck.trim().length()>0){
							intNum = objStmt.executeUpdate(strSqlCheck);
						}
						
					}

				}
			}
		} catch(Exception ex) {
			intNum = 0;
			disOutputLog(strFname, ex);
		}
		return intNum;
	}
	
	public int disSaveRuninfo(ArrayList<LinkedHashMap<String, String>> altRuncp){
		String strFname = " disSaveinfo : ";
		int intNum = 0;
		try {
			if(altRuncp!=null && altRuncp.size()>0){
				strYear = DatePro.disGetYear4now()+"";
				strWeeknum = DatePro.disGetWeek4now()+"";
				if(strYear!=null && strYear.trim().length()>0
						&& strWeeknum!=null && strWeeknum.trim().length()>0
						&& strBusname!=null && strBusname.trim().length()>0){
					for(LinkedHashMap<String, String> mapRow : altRuncp){
						String strSqlCheck = DbInfoSaveAttrs.strSqltemp_Se_SaveRuninfo;
						if(strSqlCheck!=null && strSqlCheck.trim().length()>0
								&& mapRow!=null && mapRow.size()>0){
							if(mapRow.containsKey("cp_ids")
									&& mapRow.containsKey("type")
									&& mapRow.containsKey("flg")
									&& mapRow.containsKey("subflg")
									&& mapRow.containsKey("info")
									&& mapRow.containsKey("cpuuid")
									&& mapRow.containsKey("cmd_inputdt")){
								/*
			+ " INSERT INTO tYYYYXX_cmdCCCCCC_runoutput ( "
			+ " run_ids,	cp_ids,	infotype,	infoflg,	infosubflg, "
			+ " info,		cpuuid,	createdt,	cmdrundt "
			+ " ) VALUES ( "
			+ " '^run_ids^',	'^cp_ids^',	'^infotype^',	'^infoflg^',	'^infosubflg^', "
			+ " '^info^',		'^cpuuid^',	'^createdt^',	'^cmdrundt^' "
			+ " ) ";
											 
{cp_ids=20170731164244889, 
cpcls=orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00, 
customer=1, 
ansible_ids=1, 
ansible_info=openstack, 
cmd_tpye=CREATE, 
cmd_subtype=CREATE00, 
cmd_request=null, 
cmd_inputdt=20170731164244889, 
cpuuid=25a83ac504bc4601a359fb123504261e, 
cmdrundt=20170731164244889, 
info=orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00 disRuncmdPro :  Start!, 
type=point, 
flg=runFlg, 
subflg=start}
								 */
								strSqlCheck = strSqlCheck.replace("YYYY", strYear);
								strSqlCheck = strSqlCheck.replace("XX", strWeeknum);
								strSqlCheck = strSqlCheck.replace("CCCCCC", strBusname);
								strSqlCheck = strSqlCheck.replaceAll("\\^run_ids\\^", DatePro.disGetStrdate4NowObjSdf001());
								strSqlCheck = strSqlCheck.replaceAll("\\^cp_ids\\^", mapRow.get("cp_ids"));
								strSqlCheck = strSqlCheck.replaceAll("\\^infotype\\^", mapRow.get("type"));
								strSqlCheck = strSqlCheck.replaceAll("\\^infoflg\\^", mapRow.get("flg"));
								strSqlCheck = strSqlCheck.replaceAll("\\^infosubflg\\^", mapRow.get("subflg"));
								strSqlCheck = strSqlCheck.replaceAll("\\^info\\^", mapRow.get("info"));
								strSqlCheck = strSqlCheck.replaceAll("\\^cpuuid\\^", mapRow.get("cpuuid"));
								strSqlCheck = strSqlCheck.replaceAll("\\^createdt\\^", DatePro.disGetStrdate4NowObjSdf001());
								strSqlCheck = strSqlCheck.replaceAll("\\^cmdrundt\\^", mapRow.get(ProcessAttrs.strInfoKey_Rundt));
								logger.info(strCname + strFname + " SQL ----" + strSqlCheck);
							}
							if(objStmt!=null 
									&& strSqlCheck!=null && strSqlCheck.trim().length()>0){
								intNum = intNum + objStmt.executeUpdate(strSqlCheck);
							}
						}
						
					}

				}
			}
		} catch(Exception ex) {
			intNum = 0;
			disOutputLog(strFname, ex);
		}
		return intNum;
	}
	
	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp
			, String strInfoTypepFlgp){
		String strTypef = "";
		String strFlgf = "";
		String strSubflgf = "";
		if(strInfoTypepFlgp!=null && strInfoTypepFlgp.trim().length()>0){
			String[] subTypeFlg = strInfoTypepFlgp.split("}}}", -1);
			if(subTypeFlg!=null && subTypeFlg.length>=2){
				strTypef = subTypeFlg[0];
				strFlgf = subTypeFlg[1];
				strSubflgf = subTypeFlg[2];
			}
		}
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
		lhpInfof.put(ProcessAttrs.strInfoType_Info, strTypef);
		lhpInfof.put(ProcessAttrs.strInfoFlg_Info, strFlgf);
		lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, strSubflgf);
		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
		altRuncp.add(lhpInfof);
		return altRuncp;
	}
	
	private void disOutputLog(String strFnamep, Exception exp){
		String strInfo = "";
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		strInfo = strCname + strFnamep + exp + "||" + lonFlg ;
		altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_EEx + " P00000");
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			strInfo = subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_ETx + " --" + i);
		}
	}
}
