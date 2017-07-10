package orgs.cm.tst.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CommandStreamGobbler00 extends Thread {

	private InputStream is;

	private String command;

	private String prefix = "";

	private boolean readFinish = false;

	private boolean ready = false;

	private List<String> infoList = new LinkedList<String>();

	CommandStreamGobbler00(InputStream is, String command, String prefix) {
		this.is = is;
		this.command = command;
		this.prefix = prefix;
	}

	public void run() {
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			ready = true;
			while ((line = br.readLine()) != null) {
				infoList.add(line);
				System.out.println(prefix + " line: " + line);
			}
		} catch (IOException ioe) {
			System.out.println("正式执行命令：" + command + "有IO异常");
		} finally {
			try {
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
}