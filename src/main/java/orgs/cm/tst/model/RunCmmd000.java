package orgs.cm.tst.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("runCmmd000")
public class RunCmmd000 {

	private final String strCname = RunCmmd000.class.getName();
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
			int exitValue = process.waitFor();

			String line = null;
			if(exitValue==0){
				stdISR = new InputStreamReader(process.getInputStream());
				BufferedReader stdBR = new BufferedReader(stdISR);
				while ((line = stdBR.readLine()) != null) {
					System.out.println("STD line:" + line);
				}

				errISR = new InputStreamReader(process.getErrorStream());
				BufferedReader errBR = new BufferedReader(errISR);
				while ((line = errBR.readLine()) != null) {
					System.out.println("ERR line:" + line);
				}
			} else {
				throw new Exception("Run Command Error!!");
			}
		} catch (Exception ex) {
			infoLogger.error(strCname + strFname + ex);
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
			} catch (Exception exx) {
				// System.out.println("正式执行命令：" + command + "有IO异常");
				infoLogger.error(strCname + strFname + exx);
			}
		}
	}
}
