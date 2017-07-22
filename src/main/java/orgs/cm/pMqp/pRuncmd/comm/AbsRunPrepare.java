package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;
/**
 * cmd运行前准备
 * */
public abstract class AbsRunPrepare {
	public abstract void disSetAll(HashMap<String, Object> hmpAllp);
	public abstract HashMap<String, Object> disRunPrepare();

}
