package orgs.cm.pMqp.pRuncmd.pQzGetflv;

import java.util.HashMap;

import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;

public class RunBefore_Getflv extends AbsRunBefore {

	private HashMap<String, Object> hmpAll;
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunBefore() {
		// TODO Auto-generated method stub
		return hmpAll;
	}

}