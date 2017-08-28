package orgs.cm.pMqp.pRuncmd.pStop00;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.CmdStreamGobbler;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.RunCmd_Getimg;

public class RunCmd_ST00_1 extends AbsRunCmd {

	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_ST00_1.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ClsBaseAttrs objBa = null; 
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	

	public void setRuncres(String strflgp, ArrayList<LinkedHashMap<String, String>> altRunc){
		if(strflgp!=null && strflgp.trim().length()>0){
			if("ERR".equals(strflgp)){
				hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, altRunc);
			}
			if("STD".equals(strflgp)){
				hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, altRunc);
			}
		}
	}
	
	public void setStrThrflg(String strThrflgp){
		this.strThrflg = strThrflgp;
	}
	public HashMap<String, Object> disRunCmd() {
		disRuncmd();
		return hmpAll;
	}
//	public void disSetAll(HashMap<String, Object> hmpAllp){
//		this.hmpAll = hmpAllp;
//	}
	public void disSetHmpall(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	public void disSetClsBaseAttrs(ClsBaseAttrs objBap){
		objBa = objBap;
//		objBa.disClear_lhpInfobase();
		objBa.disClear_altRunc();
	}
	private void disRuncmd(){
		String strFname = " disRuncmd : ";
		long lonBasrDt = new Date().getTime();
		Process process = null;
		CmdStreamGobbler errorGobbler = null;
		CmdStreamGobbler outputGobbler = null;
		String strInfo = "";
		String strstrCpuuid = null;
		SaveInfoPro objSaveInfoPro = null;
		
//		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
//		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();		
		try {
			if(objBa==null || hmpAll==null){
				return ;
			}
			objSaveInfoPro = new SaveInfoPro(strCname, objBa);
			
			logger.info(strCname + strFname + "  Start!");
			hmpAll.put(ProcessAttrs.strParmapKey_Runlst, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, null);
			objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
			objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Runc);
			strInfo = strCname + strFname + " 停止VM Run01 Start----" + DatePro.disGetStrdate4NowObjSdf001();
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS );

			if(hmpAll!=null && hmpAll.containsKey(ProcessAttrs.strInfoKey_Cpuuid)){
				strstrCpuuid = hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid)==null?
						null:hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid).toString();
			}
			if(strstrCpuuid==null || (strstrCpuuid!=null && strstrCpuuid.trim().length()==0)){
				strInfo = strCname + strFname + " CpUuid 异常!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Ebx+" CpUuid 异常");
				return;
			}
			if(hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)!=null
					&& !("t".equals(hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg).toString()))
					){
				strInfo = strCname + strFname + " Shell File create 失败!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Ebx+" Shell File create 失败");
				return;
			}
			String strFileroot = null;
			String strFilename = null;
			String strPostfix = null;
			String strNowRunflg = null;
			String StrCommand = null;
			String strAnsCmmd = null;
			if(hmpAll.containsKey("^anscmmd^")
					&& hmpAll.get("^anscmmd^")!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFileroot)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilename)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowPostfix)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowPostfix)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
					){
				strNowRunflg = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
				strPostfix = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowPostfix).toString();
				strFileroot = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot).toString();
				strFilename = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename).toString(); 
				StrCommand = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString(); 
				StrCommand = StrCommand.split(",")[Integer.parseInt(strNowRunflg)-1];
				strAnsCmmd = hmpAll.get("^anscmmd^").toString(); 
			}
			if(StrCommand!=null && StrCommand.trim().length()>0 && StrCommand.indexOf(",")==-1
					&& strFileroot!=null && strFileroot.trim().length()>0
					&& strFilename!=null && strFilename.trim().length()>0){
				StrCommand = StrCommand.replaceAll("\\^anscmmd\\^", strAnsCmmd);
				StrCommand = StrCommand.replaceAll("\\^shell_allpath\\^", strFileroot+strFilename+strPostfix);
			} else {
				strInfo = strCname + strFname + " Shell cmmd 构建失败!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Ebx + " Shell cmmd 构建失败");
				return;
			}
			/* ------------------------------------------------------------------------------- */
//			String StrCommand = "ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u root "; //查看镜像
/* 
创建VM STD line: 10.167.212.1 | SUCCESS => {
创建VM STD line:     "changed": true, 
创建VM STD line:     "rc": 0, 
创建VM STD line:     "stderr": "", 
创建VM STD line:     "stdout": "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+\r\n| ID                                   | Status    | Name                                 | Size | Volume Type | Bootable | Attached to |\r\n+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+\r\n| 30ff9ce4-290d-47a5-acd4-5073ca1639f0 | available | -                                    | 1    | -           | true     |             |\r\n| 37c50bb0-2538-4ad7-9c89-fdf33c0424b7 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |\r\n| 4dce04ba-2fab-4af7-bc60-44e9e912fc96 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |\r\n| 50041379-58f6-465f-a4c4-7e76fda0ff87 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |\r\n| 7b216755-b341-45f6-90ea-d46e6d9d8724 | available | -                                    | 1    | -           | true     |             |\r\n| aa90b0c2-f9c1-45e8-8201-48db06a01338 | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |\r\n| b38ed2d2-c21f-4331-8930-0fb74884699a | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |\r\n| b4e47dda-2a3c-43ba-bbc8-c80ecde70628 | available | -                                    | 1    | -           | true     |             |\r\n| ddcc7c2d-0c31-450c-b4c2-c6ee53f50e14 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |\r\n+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+\r\n", 
创建VM STD line:     "stdout_lines": [
创建VM STD line:         "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+", 
创建VM STD line:         "| ID                                   | Status    | Name                                 | Size | Volume Type | Bootable | Attached to |", 
创建VM STD line:         "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+", 
创建VM STD line:         "| 30ff9ce4-290d-47a5-acd4-5073ca1639f0 | available | -                                    | 1    | -           | true     |             |", 
创建VM STD line:         "| 37c50bb0-2538-4ad7-9c89-fdf33c0424b7 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |", 
创建VM STD line:         "| 4dce04ba-2fab-4af7-bc60-44e9e912fc96 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |", 
创建VM STD line:         "| 50041379-58f6-465f-a4c4-7e76fda0ff87 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |", 
创建VM STD line:         "| 7b216755-b341-45f6-90ea-d46e6d9d8724 | available | -                                    | 1    | -           | true     |             |", 
创建VM STD line:         "| aa90b0c2-f9c1-45e8-8201-48db06a01338 | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |", 
创建VM STD line:         "| b38ed2d2-c21f-4331-8930-0fb74884699a | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |", 
创建VM STD line:         "| b4e47dda-2a3c-43ba-bbc8-c80ecde70628 | available | -                                    | 1    | -           | true     |             |", 
创建VM STD line:         "| ddcc7c2d-0c31-450c-b4c2-c6ee53f50e14 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |", 
创建VM STD line:         "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+"
创建VM STD line:     ]
创建VM STD line: }
*/
			
//			StrCommand = StrCommand.substring(0, StrCommand.length()-1);
			StrCommand = StrCommand.replaceAll(",", "");
			strInfo = strCname + strFname + " 停止VM Run01 Cmmd----" + StrCommand;
			strInfo = strInfo.replaceAll("'", "\"");
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
			logger.info(strInfo);
			
			process = Runtime.getRuntime().exec(StrCommand);

			errorGobbler = new CmdStreamGobbler(process.getErrorStream(), StrCommand, "停止VM ERR", strstrCpuuid, this);
			outputGobbler = new CmdStreamGobbler(process.getInputStream(), StrCommand, "停止VM STD", strstrCpuuid, this);

			if(errorGobbler!=null && outputGobbler!=null){
				errorGobbler.start();
				while (!errorGobbler.isReady()) {
					Thread.sleep(100);
				}
				outputGobbler.start();
				while (!outputGobbler.isReady()) {
					Thread.sleep(50);
				}

				while(super.strThrflg!=null){
					if((new Date().getTime())-lonBasrDt<=10000){
						Thread.sleep(1010);
						if(super.strThrflg.equals("ERR")){
							strInfo = strCname + strFname + " ERR 正常完成！";
							objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRx + " ERR 正常完成");
							errorGobbler = null;
							super.strThrflg = "";
						} 
						if(super.strThrflg.equals("STD")){
							strInfo = strCname + strFname + " SDT 正常完成！";
							objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRx + " SDT 正常完成");
							outputGobbler = null;
							super.strThrflg = "";
						}
						if(outputGobbler==null && errorGobbler==null){
							strInfo = strCname + strFname + " ER SDT 监听完成！";
							objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRx + " ERR SDT 监听完成");
							super.strThrflg = null;
						}
					} else {
						if(outputGobbler!=null){
							strInfo = strCname + strFname + " SDT 超时！";
							objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Elx + " SDT 超时");
							outputGobbler = null;
						}
						if(errorGobbler!=null){
							strInfo = strCname + strFname + " ERR 超时！";
							objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Elx + " ERR 超时");
							errorGobbler = null;
						}
						break;
					}
				}
				super.strThrflg = null;
				strInfo = strCname + strFname + " 停止VM Run01 End----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
//				hmpAll.put(ProcessAttrs.strParmapKey_Runlst, altRunc);
//				for(int i=0; i<altRunc.size(); i++){
//					System.out.println(altRunc.get(i));
//				}
//				System.out.println(hmpAll);
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}  finally{
			process = null;
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
//			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}

	}
	
//	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
//			, LinkedHashMap<String, String> lhpInfop
//			, ArrayList<LinkedHashMap<String, String>> altRuncp
//			, String strInfoTypepFlgp){
//		String strTypef = "";
//		String strFlgf = "";
//		String strSubflgf = "";
//		if(strInfoTypepFlgp!=null && strInfoTypepFlgp.trim().length()>0){
//			String[] subTypeFlg = strInfoTypepFlgp.split("}}}", -1);
//			if(subTypeFlg!=null && subTypeFlg.length>=2){
//				strTypef = subTypeFlg[0].trim();
//				strFlgf = subTypeFlg[1].trim();
//				strSubflgf = subTypeFlg[2].trim();
//			}
//		}
//		LinkedHashMap<String, String> lhpInfof = null;
//		String strInfo = strInfop;
//		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
//		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo.replaceAll("'", "\""));
//		lhpInfof.put(ProcessAttrs.strInfoType_Info, strTypef);
//		lhpInfof.put(ProcessAttrs.strInfoFlg_Info, strFlgf);
//		lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, strSubflgf);
//		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
//		altRuncp.add(lhpInfof);
//		return altRuncp;
//	}
//	
//	private void disSaveInfo(String strFlgp){
//		String strFname = " disSaveInfo : ";
//		try {
//			if(strFlgp!=null && strFlgp.trim().length()>0
//					&& altRunc!=null && altRunc.size()>0){
////				for(LinkedHashMap<String, String> mapRow : altRunc){
////					System.out.println(mapRow);
////				}
//				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
//				DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
//				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
//					int intNum = objDbInfoSavepro.disSaveRuninfo(altRunc);
//					if(intNum==altRunc.size()){
//						logger.info(strCname + strFname + " Runcmd完整存储!");
//					} else {
//						logger.info(strCname + strFname + " Runcmd存储异常!");
//					}
//				}
//			}
//		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
//		}
//	}
//	private String disGetBusname(){
//		String strFname = " disGetBusname : ";
//		String strRe = "";
//		try {
//			String strPackage = this.getClass().getPackage().getName();
//			String[] subTmp = strPackage.split("\\.");
//			if(subTmp!=null && subTmp.length>1){
//				strPackage = subTmp[subTmp.length-1];
//			}
//			if(strPackage.indexOf(".")==-1){
//				strPackage = strPackage.toLowerCase();
//			}
//			strRe = strPackage;
//		} catch(Exception ex) {
//			strRe = "";
//			disOutputLog(strFname, ex);
//		}
//		return strRe;
//	}
//	private void disOutputLog(String strFnamep, Exception exp){
//		long lonFlg = System.currentTimeMillis();
//		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
//		StackTraceElement[] subSte = exp.getStackTrace();
//		for(int i=0; i<subSte.length; i++){
//			logger.error(
//					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
//		}
//	}

}
