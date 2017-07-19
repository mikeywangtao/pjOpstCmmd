package orgs.cm.pMqp.pComms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	private List<String> infoList = new LinkedList<String>();
	
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
		logger.info(strCname + " CpUuid:" + strCpUuid+ " setStop : 强制中断: " + prefix + objSdf.format(new Date()));
		commandResult = 1;
	}
	public void run() {
		String strFname = " run : ";
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = null;
			ready = true;
			logger.info(strCname + strFname + " ----" + prefix + " Start:" + objSdf.format(new Date()));
			while(commandResult != 1){
				if (br.ready()) {
					while((line = br.readLine()) != null) {
						infoList.add(line);
						System.out.println(prefix + " line: " + line);
					}
					commandResult = 1;
				} else {
					Thread.sleep(500);
				}
			}
			
			logger.info(strCname + strFname + " ----" + prefix + " End:" + objSdf.format(new Date()));
			
			this.objAbsRunCmd.setStrThrflg(prefix.split(" ")[1]);
//			while (commandResult != 1) {
//				if (br.ready()) {
//					if ((line = br.readLine()) != null) {
//						infoList.add(line);
//						System.out.println(prefix + " line: " + line);
//					} else {
//						logger.info(strCname + strFname + " ----" + prefix + " End:" + objSdf.format(new Date()));
//						break;
//					}
//				} else {
//					Thread.sleep(1000);
//				}
//			}
			
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
//			readFinish = true;
		}
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

//	public boolean isReadFinish() {
//		return readFinish;
//	}

	public boolean isReady() {
		return ready;
	}

	public List<String> getInfoList() {
		return infoList;
	}

	public void setTimeout(int timeout) {
		this.commandResult = timeout;
	}
}
