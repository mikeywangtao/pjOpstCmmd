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
//		String command = "/home/heaven/shtst000.sh";
		
		/* ------------------------------------------------------------------------------- */
//		String command = "ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u root "; //查看镜像
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
		
		/* ------------------------------------------------------------------------------- */
//		String command = "ansible openstack -m script -a  '/home/heaven/shtst002.sh' -u root "; //查看网络
		/* 11:19:37.970 [http-bio-8080-exec-3] INFO  orgs.cm.tst.model.RunCmmd002 - Run Cmmd ----> ansible openstack -m script -a  '/home/heaven/shtst002.sh' -u root 
STD line: 10.167.212.1 | SUCCESS => {
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
		
		/* ------------------------------------------------------------------------------- */
		String command = "ansible openstack -m script -a  '/home/heaven/shtst003.sh' -u root "; //查看模板
		/* 11:21:10.875 [http-bio-8080-exec-4] INFO  orgs.cm.tst.model.RunCmmd002 - Run Cmmd ----> ansible openstack -m script -a  '/home/heaven/shtst003.sh' -u root 
STD line: 10.167.212.1 | SUCCESS => {
STD line:     "changed": true, 
STD line:     "rc": 0, 
STD line:     "stderr": "", 
STD line:     "stdout": "+------------------------------------+-----------------------------------+-------+------+-----------+-------+-----------+\r\n| ID                                 | Name                              |   RAM | Disk | Ephemeral | VCPUs | Is Public |\r\n+------------------------------------+-----------------------------------+-------+------+-----------+-------+-----------+\r\n| 0                                  | m1.nano                           |    64 |    1 |         0 |     1 | True      |\r\n| 0579be21527653cb8ce6b63050578da22a | 0579be21527653cb8ce6b63050578da2a |  4096 |   20 |         0 |     2 | True      |\r\n| 0579be21527653cb8ce6b63050578daa   | 0579be21527653cb8ce6b63050578daa  | 12288 |   20 |         0 |     2 | True      |\r\n| 1                                  | default.flavor01                  |  1024 |    1 |         0 |     1 | True      |\r\n| 2                                  | default.flavor02                  |  2048 |    1 |         0 |     2 | True      |\r\n+------------------------------------+-----------------------------------+-------+------+-----------+-------+-----------+\r\n", 
STD line:     "stdout_lines": [
STD line:         "+------------------------------------+-----------------------------------+-------+------+-----------+-------+-----------+", 
STD line:         "| ID                                 | Name                              |   RAM | Disk | Ephemeral | VCPUs | Is Public |", 
STD line:         "+------------------------------------+-----------------------------------+-------+------+-----------+-------+-----------+", 
STD line:         "| 0                                  | m1.nano                           |    64 |    1 |         0 |     1 | True      |", 
STD line:         "| 0579be21527653cb8ce6b63050578da22a | 0579be21527653cb8ce6b63050578da2a |  4096 |   20 |         0 |     2 | True      |", 
STD line:         "| 0579be21527653cb8ce6b63050578daa   | 0579be21527653cb8ce6b63050578daa  | 12288 |   20 |         0 |     2 | True      |", 
STD line:         "| 1                                  | default.flavor01                  |  1024 |    1 |         0 |     1 | True      |", 
STD line:         "| 2                                  | default.flavor02                  |  2048 |    1 |         0 |     2 | True      |", 
STD line:         "+------------------------------------+-----------------------------------+-------+------+-----------+-------+-----------+"
STD line:     ]
STD line: }*/
		
		long timeout = 10 * 1000;
		try {
			process = Runtime.getRuntime().exec(command);

			CommandStreamGobbler01 errorGobbler = new CommandStreamGobbler01(process.getErrorStream(), command, "ERR");
			CommandStreamGobbler01 outputGobbler = new CommandStreamGobbler01(process.getInputStream(), command, "STD");

			infoLogger.info("Run Cmmd ----> " + command);
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
