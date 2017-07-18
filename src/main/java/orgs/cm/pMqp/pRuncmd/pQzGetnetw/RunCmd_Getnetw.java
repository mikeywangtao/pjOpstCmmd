package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.CmdStreamGobbler;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.tst.model.CommandWaitForThread01;

public class RunCmd_Getnetw extends AbsRunCmd {
	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_Getnetw.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	@Override
	public HashMap<String, Object> disRunCmd() {
		disRuncmd();
		return hmpAll;
	}
	
	private void disRuncmd(){
		String strFname = " disRuncmd : ";
		long timeout = 10 * 1000;
		Process process = null;
		
		try {
			logger.info(strCname + strFname + "  Start!");
			/* ------------------------------------------------------------------------------- */
			String command = "ansible openstack -m script -a  '/home/heaven/shtst002.sh' -u root "; //查看网络
/* 11:17:09.345 [http-bio-8080-exec-10] INFO  orgs.cm.tst.model.RunCmmd002 - Run Cmmd ----> ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u rootSTD line: 10.167.212.1 | SUCCESS => {
STD line:     "changed": true, 
STD line:     "rc": 0, 
STD line:     "stderr": "", 
STD line:     "stdout": "+--------------------------------------+------------+\r\n| ID                                   | Name       |\r\n+--------------------------------------+------------+\r\n| 4c367d93-fbe5-4c58-ac85-f7aab0310740 | centos6    |\r\n| 4c3b9963-bc32-4c38-a592-309e2da5722e | centos6.5  |\r\n| 20022a68-bc87-462d-ba6c-af6570ba839e | cirros     |\r\n| a7ff7cec-f187-4439-969e-a696020fb6a6 | fedora25   |\r\n| a1f55a3b-fd1c-4512-bbcf-15f4a231371c | ubuntu1404 |\r\n+--------------------------------------+------------+\r\n", 
STD line:     "stdout_lines": [
STD line:         "+--------------------------------------+------------+", 
STD line:         "| ID                                   | Name       |", 
STD line:         "+--------------------------------------+------------+", 
STD line:         "| 4c367d93-fbe5-4c58-ac85-f7aab0310740 | centos6    |", 
STD line:         "| 4c3b9963-bc32-4c38-a592-309e2da5722e | centos6.5  |", 
STD line:         "| 20022a68-bc87-462d-ba6c-af6570ba839e | cirros     |", 
STD line:         "| a7ff7cec-f187-4439-969e-a696020fb6a6 | fedora25   |", 
STD line:         "| a1f55a3b-fd1c-4512-bbcf-15f4a231371c | ubuntu1404 |", 
STD line:         "+--------------------------------------+------------+"
STD line:     ]
STD line: } */
			
			SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");
			logger.info(strCname + strFname + " 查看网络----" + objSdf.format(new Date()));
			logger.info("Run Cmmd 查看网络----> " + command);
			
			process = Runtime.getRuntime().exec(command);

			CmdStreamGobbler errorGobbler = new CmdStreamGobbler(process.getErrorStream(), command, "查看网络 ERR");
			CmdStreamGobbler outputGobbler = new CmdStreamGobbler(process.getInputStream(), command, "查看网络 STD");
			errorGobbler.start();
			// 必须先等待错误输出ready再建立标准输出
			while (!errorGobbler.isReady()) {
				Thread.sleep(10);
			}
			outputGobbler.start();
			while (!outputGobbler.isReady()) {
				Thread.sleep(10);
			}

			CommandWaitForThread01 commandThread = new CommandWaitForThread01(process);
			commandThread.start();

			long commandTime = new Date().getTime();
			long nowTime = new Date().getTime();
			boolean timeoutFlag = false;
			while (!commandThread.isFinish()) {
				if (nowTime - commandTime > timeout) {
					timeoutFlag = true;
					break;
				} else {
					Thread.sleep(10000);
					nowTime = new Date().getTime();
				}
			}
			if (timeoutFlag) {
				// 命令超时
				errorGobbler.setTimeout(1);
				outputGobbler.setTimeout(1);
				System.out.println("正式执行命令：" + command + "超时");
			}

			while (true) {
				if (errorGobbler.isReadFinish() && outputGobbler.isReadFinish()) {
					break;
				}
				Thread.sleep(10);
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
