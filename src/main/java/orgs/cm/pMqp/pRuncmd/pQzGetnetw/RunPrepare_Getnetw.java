package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.util.HashMap;

import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

public class RunPrepare_Getnetw extends AbsRunPrepare {

	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
	@Override
	public HashMap<String, Object> disRunPrepare() {
		// TODO Auto-generated method stub
		
		objDbpro = new BaseDbpro();
		
		return hmpAll;
	}
	
}