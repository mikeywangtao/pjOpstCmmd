package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.CmdStreamGobbler;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.tst.model.CommandWaitForThread01;

public class RunCmd_Getnetw extends AbsRunCmd {
	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_Getnetw.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
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
	public void disSetAll(HashMap<String, Object> hmpAllp){
		this.hmpAll = hmpAllp;
	}
	
//	public void setBooThrflg(boolean booThrflgp){
//		this.booThrflg = booThrflgp;
//	}
//	@Override
//	public HashMap<String, Object> disRunCmd() {
//		disRuncmd();
//		return hmpAll;
//	}
	
	private void disRuncmd(){
		String strFname = " disRuncmd : ";
		long lonBasrDt = new Date().getTime();
		Process process = null;
		CmdStreamGobbler errorGobbler = null;
		CmdStreamGobbler outputGobbler = null;
		String strInfo = "";
		String strstrCpuuid = null;
		
		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();		
		try {
			logger.info(strCname + strFname + "  Start!");
			hmpAll.put(ProcessAttrs.strParmapKey_Runlst, null);
			lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Runc);
			if(hmpAll!=null 
					&& hmpAll.containsKey(ProcessAttrs.strInfoKey_Cpuuid)){
				strstrCpuuid = hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid)==null?
						null:hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid).toString();
			}
			if(strstrCpuuid==null || (strstrCpuuid!=null && strstrCpuuid.trim().length()==0)){
				strInfo = strCname + strFname + " CpUuid 异常!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			
			/* ------------------------------------------------------------------------------- */
			String command = "ansible openstack -m script -a  '/home/heaven/shtst002.sh' -u root "; //查看网络
/* TD line: 10.167.212.1 | SUCCESS => {
STD line:     "changed": true, 
STD line:     "rc": 0, 
STD line:     "stderr": "", 
STD line:     "stdout": "+--------------------------------------+-------------+------------------------------------------------------+\r\n| id                                   | name        | subnets                                              |\r\n+--------------------------------------+-------------+------------------------------------------------------+\r\n| 371a25f3-112d-4b42-b382-9bd25f9545c9 | selfservice | 2f14c050-c774-4fdf-a28a-f836381f53df 172.16.1.0/24   |\r\n| aedbece2-0b64-4879-94e5-461439cd6930 | provider    | d5b7d965-7ecf-49d6-897a-ece75e7471ab 10.167.211.0/24 |\r\n+--------------------------------------+-------------+------------------------------------------------------+\r\n", 
STD line:     "stdout_lines": [
STD line:         "+--------------------------------------+-------------+------------------------------------------------------+", 
STD line:         "| id                                   | name        | subnets                                              |", 
STD line:         "+--------------------------------------+-------------+------------------------------------------------------+", 
STD line:         "| 371a25f3-112d-4b42-b382-9bd25f9545c9 | selfservice | 2f14c050-c774-4fdf-a28a-f836381f53df 172.16.1.0/24   |", 
STD line:         "| aedbece2-0b64-4879-94e5-461439cd6930 | provider    | d5b7d965-7ecf-49d6-897a-ece75e7471ab 10.167.211.0/24 |", 
STD line:         "+--------------------------------------+-------------+------------------------------------------------------+"
STD line:     ]
STD line: } */
			
			SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");
			strInfo = strCname + strFname + " 查看网络 Start----" + DatePro.disGetStrdate4NowObjSdf001();
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
			strInfo = strCname + strFname + " 查看网络 Cmmd----" + command;
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
			logger.info(strInfo);
			logger.info(strInfo);
			
			process = Runtime.getRuntime().exec(command);

			errorGobbler = new CmdStreamGobbler(process.getErrorStream(), command, "查看网络 ERR", strstrCpuuid, this);
			outputGobbler = new CmdStreamGobbler(process.getInputStream(), command, "查看网络 STD", strstrCpuuid, this);

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
					if((new Date().getTime())-lonBasrDt<=15000){
						Thread.sleep(1010);
						if(super.strThrflg.equals("ERR")){
							strInfo = strCname + strFname + " ERR 正常完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							errorGobbler = null;
							super.strThrflg = "";
						} 
						if(super.strThrflg.equals("STD")){
							strInfo = strCname + strFname + " SDT 正常完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							outputGobbler = null;
							super.strThrflg = "";
						}
						if(outputGobbler==null && errorGobbler==null){
							strInfo = strCname + strFname + " ER SDT 监听完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							super.strThrflg = null;
						}
					} else {
						if(outputGobbler!=null){
							strInfo = strCname + strFname + " SDT 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
//							outputGobbler.setStop();
							outputGobbler = null;
						}
						if(errorGobbler!=null){
							strInfo = strCname + strFname + " ERR 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
//							errorGobbler.setStop();
							errorGobbler = null;
						}
						break;
					}
				}
				super.strThrflg = null;
				strInfo = strCname + strFname + " 查看网络 End----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Runlst, altRunc);
				for(int i=0; i<altRunc.size(); i++){
					System.out.println(altRunc.get(i));
				}
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		} finally{
			process = null;
		}
//		String strFname = " disRuncmd : ";
//		long lonBasrDt = new Date().getTime();
//		Process process = null;
//		CmdStreamGobbler errorGobbler = null;
//		CmdStreamGobbler outputGobbler = null;
//		try {
//			logger.info(strCname + strFname + "  Start!");
//			/* ------------------------------------------------------------------------------- */
//			String command = "ansible openstack -m script -a  '/home/heaven/shtst002.sh' -u root "; //查看网络
///* 11:17:09.345 [http-bio-8080-exec-10] INFO  orgs.cm.tst.model.RunCmmd002 - Run Cmmd ----> ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u rootSTD line: 10.167.212.1 | SUCCESS => {
//STD line:     "changed": true, 
//STD line:     "rc": 0, 
//STD line:     "stderr": "", 
//STD line:     "stdout": "+--------------------------------------+------------+\r\n| ID                                   | Name       |\r\n+--------------------------------------+------------+\r\n| 4c367d93-fbe5-4c58-ac85-f7aab0310740 | centos6    |\r\n| 4c3b9963-bc32-4c38-a592-309e2da5722e | centos6.5  |\r\n| 20022a68-bc87-462d-ba6c-af6570ba839e | cirros     |\r\n| a7ff7cec-f187-4439-969e-a696020fb6a6 | fedora25   |\r\n| a1f55a3b-fd1c-4512-bbcf-15f4a231371c | ubuntu1404 |\r\n+--------------------------------------+------------+\r\n", 
//STD line:     "stdout_lines": [
//STD line:         "+--------------------------------------+------------+", 
//STD line:         "| ID                                   | Name       |", 
//STD line:         "+--------------------------------------+------------+", 
//STD line:         "| 4c367d93-fbe5-4c58-ac85-f7aab0310740 | centos6    |", 
//STD line:         "| 4c3b9963-bc32-4c38-a592-309e2da5722e | centos6.5  |", 
//STD line:         "| 20022a68-bc87-462d-ba6c-af6570ba839e | cirros     |", 
//STD line:         "| a7ff7cec-f187-4439-969e-a696020fb6a6 | fedora25   |", 
//STD line:         "| a1f55a3b-fd1c-4512-bbcf-15f4a231371c | ubuntu1404 |", 
//STD line:         "+--------------------------------------+------------+"
//STD line:     ]
//STD line: } */
//			
//			SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");
//			logger.info(strCname + strFname + " 查看网络----" + objSdf.format(new Date()));
//			logger.info("Run Cmmd 查看网络----> " + command);
//			
//			process = Runtime.getRuntime().exec(command);
//
//			errorGobbler = new CmdStreamGobbler(process.getErrorStream(), command, "查看网络 ERR", this);
//			outputGobbler = new CmdStreamGobbler(process.getInputStream(), command, "查看网络 STD", this);
//			
//			if(errorGobbler!=null && outputGobbler!=null){
//				errorGobbler.start();
//				// 必须先等待错误输出ready再建立标准输出
//				while (!errorGobbler.isReady()) {
//					Thread.sleep(10);
//				}
//				outputGobbler.start();
//				while (!outputGobbler.isReady()) {
//					Thread.sleep(10);
//				}
//
//				while(!super.booThrflg){
//					if((new Date().getTime())-lonBasrDt<=8000){
//						Thread.sleep(1000);
//					} else {
//						try {
//							errorGobbler.destroy();
//						} catch(Exception exx) {
//							disOutputLog(strFname, exx);
//						} finally{
//							errorGobbler = null;
//						}
//						try {
//							outputGobbler.destroy();
//						} catch(Exception exx) {
//							disOutputLog(strFname, exx);
//						} finally{
//							outputGobbler = null;
//						}
//						break;
//					}
//				}
//			}
//		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
//		} 
//		errorGobbler.start();
//		// 必须先等待错误输出ready再建立标准输出
//		while (!errorGobbler.isReady()) {
//			Thread.sleep(10);
//		}
//		outputGobbler.start();
//		while (!outputGobbler.isReady()) {
//			Thread.sleep(10);
//		}
//
//		while(!super.booThrflg){
//			if((new Date().getTime())-lonBasrDt<=8000){
//				Thread.sleep(1000);
//			} else {
//				try {
//					errorGobbler.destroy();
//				} catch(Exception exx) {
//					disOutputLog(strFname, exx);
//				} finally{
//					errorGobbler = null;
//				}
//				try {
//					outputGobbler.destroy();
//				} catch(Exception exx) {
//					disOutputLog(strFname, exx);
//				} finally{
//					outputGobbler = null;
//				}
//				break;
//			}
//		}
////		CommandWaitForThread01 commandThread = new CommandWaitForThread01(process);
////		commandThread.start();
////
////		long commandTime = new Date().getTime();
////		long nowTime = new Date().getTime();
////		boolean timeoutFlag = false;
////		while (!commandThread.isFinish()) {
////			if (nowTime - commandTime > timeout) {
////				timeoutFlag = true;
////				break;
////			} else {
////				Thread.sleep(10000);
////				nowTime = new Date().getTime();
////			}
////		}
////		if (timeoutFlag) {
////			// 命令超时
////			errorGobbler.setTimeout(1);
////			outputGobbler.setTimeout(1);
////			System.out.println("正式执行命令：" + command + "超时");
////		}
//
////		while (true) {
////			if (errorGobbler.isReadFinish() && outputGobbler.isReadFinish()) {
////				break;
////			}
////			Thread.sleep(10);
////		}
//		} catch(Exception ex) {
//		disOutputLog(strFname, ex);
//	}
	}

	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp){
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
		lhpInfof.put(ProcessAttrs.strInfoSubtype_Info, "info");
		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
		altRuncp.add(lhpInfof);
		return altRuncp;
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
