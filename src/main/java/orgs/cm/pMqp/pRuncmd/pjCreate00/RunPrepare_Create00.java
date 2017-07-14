package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.HashMap;

import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

public class RunPrepare_Create00 extends AbsRunPrepare {

	private AbsDbpro objDbpro = null;
	@Override
	public HashMap<String, Object> disRunPrepare() {
		// TODO Auto-generated method stub
		
		objDbpro = new BaseDbpro();
		
		return null;
	}

}
