package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.util.HashMap;

import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;

public class RunAfter_Getimg extends AbsRunAfter {

	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
	@Override
	public HashMap<String, Object> disRunAfter() {
		// TODO Auto-generated method stub
		
		objDbpro = new BaseDbpro();
		
		return hmpAll;
	}

}