package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.util.HashMap;

import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;

public class RunAfter_Getnetw extends AbsRunAfter {

	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		this.hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunAfter() {
		// TODO Auto-generated method stub
		
		objDbpro = new BaseDbpro(null);
		
		return hmpAll;
	}

}
