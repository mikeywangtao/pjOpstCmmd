package orgs.cm.pMqp.pRuncmd.pQzGetflv;

import java.util.HashMap;

import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

public class RunPrepare_Getflv extends AbsRunPrepare {

	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunPrepare() {
		// TODO Auto-generated method stub
		
		objDbpro = new BaseDbpro(null);
		
		return hmpAll;
	}
	
}
