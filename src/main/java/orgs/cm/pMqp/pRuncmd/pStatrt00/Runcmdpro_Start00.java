package orgs.cm.pMqp.pRuncmd.pStatrt00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.IdsPro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunFinally;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;

/**
 * 停止 Vm 虚拟机。
 * 创建一个。
 * */
public class Runcmdpro_Start00 extends AbsRuncmdPro implements Runnable {

	private final String strCname = Runcmdpro_Start00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpPar = new HashMap<>();
//	private OutputLogPro objOutputLogPro = new OutputLogPro(strCname);
//	private SetInfoPro objSetInfoPro = new SetInfoPro(strCname);
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	private ClsBaseAttrs objBa = null;
	
	public void run(){
		disRuncmdPro();
	}
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		
		String strInfo = "";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		SaveInfoPro objSaveInfoPro = null;
		
		try {
			objBa = new ClsBaseAttrs(strCname);
			disSetParinfos();
			disSetInfobase();
			
			
			/* 过程信息存储 */
			objSaveInfoPro = new SaveInfoPro(strCname, objBa);
			
			strInfo = strCname + strFname + " Start!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
			strInfo = strCname + strFname + "000 Input----" + hmpPar.toString();
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PAx+" input map par ");
			objSaveInfoPro.disSaveInfo_Cp(DbInfoSaveAttrs.strSaveFlg_Cp);
			List<HashMap> altDataAnsible = new ArrayList<>();
//			AnsiblePro objAnsiblePro = new AnsiblePro(this.strCname, hmpPar);
//			List<HashMap> altDataAnsible = objAnsiblePro.disGetAnsible();
//			List<HashMap> altDataAnsible = disGetAnsible();
			strInfo = strCname + strFname + "000 altDataAnsible----" + altDataAnsible;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PAx+" altDataAnsible ");
			if(hmpPar!=null 
					&& objBa!=null){
//				HashMap map = altDataAnsible.get(0);
				
				objPrepare = new RunPrepare_Start00();
				objPrepare.disSetHmpall(hmpPar);
				objPrepare.disSetClsBaseAttrs(objBa);
				super.disRunPrepare(objPrepare);
				
				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "1".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + 
							ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
							" run cmmd 1 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
					objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRx+" run cmmd 1 ");
					
					objBefore = new RunBefore_Sa00_1();
					objBefore.disSetHmpall(hmpPar);
					objBefore.disSetClsBaseAttrs(objBa);
					super.disRunBefre(objBefore);
					objBefore = null;
					
					objCmd = new RunCmd_Sa00_1();
					objCmd.disSetHmpall(hmpPar);
					objCmd.disSetClsBaseAttrs(objBa);
					super.disRunCmd(objCmd);
					objCmd = null;
					
					objAfter = new RunAfter_Sa00_1();
					objAfter.disSetHmpall(hmpPar);
					objAfter.disSetClsBaseAttrs(objBa);
					super.dusRunAfter(objAfter);
					objAfter = null;
				}
				
				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "2".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + 
							ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
							" run cmmd 2 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
					objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRx+" run cmmd 2 ");
					
					int intLoop = 0;
					String strRunlopp = null;
					do {
						objBefore = new RunBefore_Sa00_2();
						objBefore.disSetHmpall(hmpPar);
						objBefore.disSetClsBaseAttrs(objBa);
						super.disRunBefre(objBefore);
						objBefore = null;
						
						objCmd = new RunCmd_Sa00_2();
						objCmd.disSetHmpall(hmpPar);
						objCmd.disSetClsBaseAttrs(objBa);
						super.disRunCmd(objCmd);
						objCmd = null;
						
						objAfter = new RunAfter_Sa00_2();
						objAfter.disSetHmpall(hmpPar);
						objAfter.disSetClsBaseAttrs(objBa);
						super.dusRunAfter(objAfter);
						objAfter = null;
						strRunlopp = hmpPar.get(ProcessAttrs.strParmapKey_Ppa_RunLoopFlg).toString();
						
						intLoop = intLoop + 1;
						
						strInfo = strCname + strFname + 
								ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
								" loop (do while) : strRunlopp ----" + strRunlopp + 
								 " Loop num : " + intLoop;
						objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PLx+" loop (do while) ");
					} while("t".equals(strRunlopp) || intLoop==10);
				}
			} else {
				throw new Exception("hmpPar Error ! is null or is empty!");
			}
			strInfo = strCname + strFname + "999 End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
			
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally {
			if(objBa!=null && objSaveInfoPro!=null){
				AbsRunFinally objRunFinally = new RunFinally_Start00();
				objRunFinally.disSetHmpall(hmpPar);
				objRunFinally.disSetClsBaseAttrs(objBa);
				objRunFinally.disRunFinally();
				objSaveInfoPro.disSaveInfo_Cp(DbInfoSaveAttrs.strSaveFlg_Run);
			}
//			AbsRunFinally objRunFinally = new RunFinally_Create00();
//			objRunFinally.disSetHmpall(hmpPar);
//			objRunFinally.disSetClsBaseAttrs(objBa);
//			objRunFinally.disRunFinally();
//			objSaveInfoPro.disSaveInfo_Cp(DbInfoSaveAttrs.strSaveFlg_Run);
		}
	}

	

	/**
	 * 运行日志基本信息构建
	 * */
	private void disSetInfobase(){
		String strFname = " disSetInfobase : ";
		
		try {
//			objBa.lhpInfobase = new LinkedHashMap<>();
			objBa.lhpInfobase.put("cp_ids",IdsPro.disGetIds()); //DatePro.disGetStrdate4NowObjSdf001());
			objBa.lhpInfobase.put("cpcls", strCname);
			objBa.lhpInfobase.put("customer", hmpPar.get("^customerids^").toString());
			objBa.lhpInfobase.put("ansible_ids", hmpPar.get("^ansid^").toString());
			objBa.lhpInfobase.put("ansible_info", hmpPar.get("^anscmmd^").toString());
			objBa.lhpInfobase.put("cmd_tpye", hmpPar.get("^req_type^").toString());
			objBa.lhpInfobase.put("cmd_subtype", hmpPar.get("^req_subtype^").toString());
			objBa.lhpInfobase.put("cmd_request", hmpPar.get(ProcessAttrs.strParmapKey_Inpars).toString());
			objBa.lhpInfobase.put("cmd_inputdt", DatePro.disGetStrdate4NowObjSdf001());
			objBa.lhpInfobase.put("cpuuid", hmpPar.get(ProcessAttrs.strInfoKey_Cpuuid).toString());
			objBa.lhpInfobase.put("cmdrundt", DatePro.disGetStrdate4NowObjSdf001());
			objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Pro);
			
			hmpPar.put(ProcessAttrs.strParmapKey_Infobase, objBa.lhpInfobase);
			hmpPar.put("cp_ids",objBa.lhpInfobase.get("cp_ids"));
		} catch(Exception ex) {
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
		}
	}
	
	/**
	 * 传入参数处理
	 * */
	private void disSetParinfos(){
		String strFname = " disSetPars : ";
		List<HashMap> altDataKeystone = new ArrayList<>();
		try {
//			List<HashMap> altDataKeystone = disGetKeustone();	
//			KeystonePro objKeystonePro = new KeystonePro(this.strCname, hmpPar);
//			altDataKeystone = objKeystonePro.disGetKeustone();
			
			if(altDataKeystone!=null){
/*
{^pname^=admin, 
^ansid^=1, 
msgId=2017073116040150856914, 
^intImaId^=12, 
^strSshKey^=1, 
^uname^=admin, 
^strVmUser^=1, 
^shell_allpath^=, 
^intNwId^=1, 
^netwids^=aedbece2-0b64-4879-94e5-461439cd6930, 
usr_name=username, 
^authurl^=http://test-controller:5000/v3, 
^flvids^=0, 
^imgapi^=2, 
^uksids^=1, 
sysflg=cms, 
^vmids^=, 
^ideapi^=3, 
^intTemId^=0, 
^pdom^=Default, 
^pass^=admin, 
^customerids^=1, 
^imgids^=20022a68-bc87-462d-ba6c-af6570ba839e, 
serverTarget=test, 
login_name=wode, 
^udom^=Default, 
^anscmmd^=openstack, 
^vmname^=vv, 
^req_type^=CREATE, 
^devids^=, 
^req_subtype^=CREATE00, 
^strVmPassword^=1}
 */
//				UUID objUuid = UUID.randomUUID();
//				HashMap<String, String> hmpAllInp = new HashMap<>();
//				hmpAllInp.put("^ansid^", "1");
				
				UUID objUuid = UUID.randomUUID();
				HashMap<String, Object> hmpAllInp = null;
				hmpAllInp = hmpPar;
				hmpAllInp.put("^devname^", "dev-"+objUuid.toString().replaceAll("-", ""));
				hmpAllInp.put("^devids^", null);
				hmpAllInp.put("^vmids^", null);
				hmpAllInp.put("^shell_allpath^", "");
				if(!hmpAllInp.containsKey("^vmname^")
						|| hmpAllInp.get("^vmname^")==null
						|| (hmpAllInp.get("^vmname^")!=null 
							&& hmpAllInp.get("^vmname^").toString().trim().length()==0)
						|| (hmpAllInp.get("^vmname^")!=null 
							&& hmpAllInp.get("^vmname^").toString().trim().length()>0) 
							&& "None".equals(hmpAllInp.get("^vmname^").toString())){
					hmpAllInp.put("^vmname^", "vm-"+objUuid.toString().replaceAll("-", ""));
				}
				hmpPar = new HashMap<>();
				
				hmpPar.put(ProcessAttrs.strParmapKey_Inpars, hmpAllInp);
				hmpPar.put("^ansid^", hmpAllInp.get("^ansid^"));
				hmpPar.put("^anscmmd^", hmpAllInp.get("^anscmmd^"));
				hmpPar.put("^req_type^", hmpAllInp.get("^req_type^"));
				hmpPar.put("^req_subtype^", hmpAllInp.get("^req_subtype^"));
				hmpPar.put("^customerids^", hmpAllInp.get("^customerids^"));
				hmpPar.put("^uksids^", hmpAllInp.get("^uksids^"));
				hmpPar.put("^devname^", "dev-"+objUuid.toString().replaceAll("-", ""));
				hmpPar.put("^devids^", null);
				hmpPar.put("^vmids^", null);
				
				hmpPar.put(ProcessAttrs.strInfoKey_Cpuuid, objUuid.toString().replaceAll("-", ""));
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "f");
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunShCmmd, null);
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFileroot, 
						"/home/anshells/" + DatePro.disGetYear4now()+DatePro.disGetWeek4now());
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilename, 
						"/" 
						+ DatePro.disGetStrdate4NowObjSdf001() 
						+ "_" + hmpAllInp.get("^req_type^").toString()
						+ hmpAllInp.get("^req_subtype^").toString() );
//							+ ".sh");
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunResLst, "");
				
			} else {
				hmpPar = null;
			}
		} catch(Exception ex) {
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
		}
	}
	
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}
