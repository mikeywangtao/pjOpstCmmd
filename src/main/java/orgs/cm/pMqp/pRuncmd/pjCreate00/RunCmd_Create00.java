package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.HashMap;

import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;

public class RunCmd_Create00 extends AbsRunCmd {

	private HashMap<String, Object> hmpAll;
	
	public void setStrThrflg(String strThrflgp){
		this.strThrflg = strThrflgp;
	}
	public HashMap<String, Object> disRunCmd() {
		// TODO Auto-generated method stub
		return hmpAll;
	}
	public void disSetAll(HashMap<String, Object> hmpAllp){
		this.hmpAll = hmpAllp;
	}

}
