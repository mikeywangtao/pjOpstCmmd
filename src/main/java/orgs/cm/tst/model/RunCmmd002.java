package orgs.cm.tst.model;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("runCmmd002")
public class RunCmmd002 {

	private final String strCname = RunCmmd002.class.getName();
	private final Logger infoLogger = LogManager.getLogger(strCname);

	public void disPro000() {
		String strFname = " disPro000 : ";
//		InputStreamReader stdISR = null;
//		InputStreamReader errISR = null;
		Process process = null;
//		String command = "/home/Lance/workspace/someTest/testbash.sh";
		String command = "/home/heaven/shtst000.sh";
		long timeout = 10 * 1000;
		try {
			process = Runtime.getRuntime().exec(command);

			CommandStreamGobbler01 errorGobbler = new CommandStreamGobbler01(process.getErrorStream(), command, "ERR");
			CommandStreamGobbler01 outputGobbler = new CommandStreamGobbler01(process.getInputStream(), command, "STD");

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
					Thread.sleep(1000);
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
		} catch (Exception ex) {
			infoLogger.error(strCname + strFname + ex);
//			e.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
				process = null;
			}
		}
	}
}
