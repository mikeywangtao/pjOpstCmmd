package orgs.cm.tst.model;

public class CommandWaitForThread01 extends Thread {

	private Process process;
	private boolean finish = false;
	private int exitValue = -1;

	public CommandWaitForThread01(Process process) {
		this.process = process;
	}

	public void run() {
		try {
			this.exitValue = process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			finish = true;
		}
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public int getExitValue() {
		return exitValue;
	}

}
