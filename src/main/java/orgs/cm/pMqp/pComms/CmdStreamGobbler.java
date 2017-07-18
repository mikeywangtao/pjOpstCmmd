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

import orgs.cm.pMqp.pRuncmd.pQzGetimg.RunCmd_Getimg;

public class CmdStreamGobbler extends Thread {

	private InputStream is;
	private String command;
	private String prefix = "";
	private boolean readFinish = false;
	private boolean ready = false;

	// 命令执行结果,0:执行中 1:超时
	private int commandResult = 0;

	private List<String> infoList = new LinkedList<String>();
	
	private final String strCname = CmdStreamGobbler.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");

	public CmdStreamGobbler(InputStream is, String command, String prefix) {
		this.is = is;
		this.command = command;
		this.prefix = prefix;
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
//			System.out.println(prefix + " ---->" + (new Date()).getTime());
			while (commandResult != 1) {
				if (br.ready()) {
					if ((line = br.readLine()) != null) {
						infoList.add(line);
						System.out.println(prefix + " line: " + line);
					} else {
						logger.info(strCname + strFname + " ----" + prefix + " End:" + objSdf.format(new Date()));
						break;
					}
				} else {
					Thread.sleep(1000);
				}
			}
			
		} catch (IOException | InterruptedException ioe) {
			System.out.println("正式执行命令：" + command + "有IO异常");
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
			readFinish = true;
		}
	}

	public InputStream getIs() {
		return is;
	}

	public String getCommand() {
		return command;
	}

	public boolean isReadFinish() {
		return readFinish;
	}

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
