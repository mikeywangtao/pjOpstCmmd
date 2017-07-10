package orgs.cm.tst.model;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("runCmmd001")
public class RunCmmd001 {

	private final String strCname = RunCmmd001.class.getName();
	private final Logger infoLogger = LogManager.getLogger(strCname);

	public void disPro000() {
		String strFname = " disPro000 : ";
		InputStreamReader stdISR = null;
		InputStreamReader errISR = null;
		Process process = null;
//		String command = "/home/Lance/workspace/someTest/testbash.sh";
		String command = "/home/heaven/shtst000.sh";
		try {
			process = Runtime.getRuntime().exec(command);

			CommandStreamGobbler00 errorGobbler = new CommandStreamGobbler00(process.getErrorStream(), command, "ERR");
			CommandStreamGobbler00 outputGobbler = new CommandStreamGobbler00(process.getInputStream(), command, "STD");

			// 必须先等待错误输出ready再建立标准输出
			while (!errorGobbler.isReady()) {
				Thread.sleep(10);
			}
			outputGobbler.start();
			while (!outputGobbler.isReady()) {
				Thread.sleep(10);
			}
			
			int exitValue = process.waitFor();
		} catch (Exception ex) {
			infoLogger.error(strCname + strFname + ex);
//			e.printStackTrace();
		} finally {
			try {
				if (stdISR != null) {
					stdISR.close();
					stdISR = null;
				}
				if (errISR != null) {
					errISR.close();
					errISR = null;
				}
				if (process != null) {
					process.destroy();
					process = null;
				}
			} catch (IOException exx) {
				infoLogger.error(strCname + strFname + exx);
//				System.out.println("正式执行命令：" + command + "有IO异常");
			}
		}
	}
}
