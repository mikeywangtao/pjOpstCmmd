package orgs.cm.pMqp.pComms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;

public class CmdStreamGobbler extends Thread {

	private InputStream is;
	private String command;
	private String prefix = "";
	private boolean ready = false;
	private String strCpUuid = null;
	private AbsRunCmd objAbsRunCmd = null; 

	// 命令执行结果,0:执行中 1:超时
	private int commandResult = 0;

	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
	private final String strCname = CmdStreamGobbler.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");

	public CmdStreamGobbler(InputStream is, String command, String prefix, String strCpUuidp, AbsRunCmd objRuncmdp) {
		this.is = is;
		this.command = command;
		this.prefix = prefix;
		this.strCpUuid = strCpUuidp;
		this.objAbsRunCmd = objRuncmdp;
	}

	public void setStop(){
		String strInfo = strCname + " CpUuid:" + strCpUuid+ " setStop : 强制中断: " + prefix + objSdf.format(new Date());
		logger.info(strInfo);
		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
		lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Res+prefix.split(" ")[1].toLowerCase());
		altRunc = disSetInfo(strInfo, lhpInfo, altRunc, "info");
		commandResult = 1;
	}
	public void run() {
		String strFname = " run : ";
		String strInfo = "";
		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
		lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Res+prefix.split(" ")[1].toLowerCase());
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = null;
			ready = true;
//			logger.info(strCname + strFname + " ----" + prefix + " Start:" + objSdf.format(new Date()));
			strInfo = strCname + strFname + " ----" + prefix + " Start:" + objSdf.format(new Date());
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc, "info");
			
			while(commandResult != 1){
				if (br.ready()) {
					while((line = br.readLine()) != null) {
//						infoList.add(line);
						strInfo = prefix.split(" ")[1] + "}}}" + line;
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, "res");
						System.out.println(prefix + " line: " + line);
					}
					commandResult = 1;
				} else {
					Thread.sleep(500);
				}
			}
			
//			logger.info(strCname + strFname + " ----" + prefix + " End:" + objSdf.format(new Date()));
			strInfo = strCname + strFname + " ----" + prefix + " End:" + objSdf.format(new Date());
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc, "info");
			
			this.objAbsRunCmd.setRuncres(prefix.split(" ")[1], altRunc);
			this.objAbsRunCmd.setStrThrflg(prefix.split(" ")[1]);
			
		} catch (Exception ex) {
			disOutputLog(strFname, ex);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException ioe) {
				System.out.println("正式执行命令：" + command + "有IO异常");
			}
		}
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
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}
	public InputStream getIs() {
		return is;
	}

	public String getCommand() {
		return command;
	}

	public boolean isReady() {
		return ready;
	}

	public void setTimeout(int timeout) {
		this.commandResult = timeout;
	}
}
