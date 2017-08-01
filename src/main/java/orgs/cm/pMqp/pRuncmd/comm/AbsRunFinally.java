package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;
/**
 * cmd运行前准备
 * */
public abstract class AbsRunFinally {
	public abstract void disSetAll(HashMap<String, Object> hmpAllp);
	public abstract HashMap<String, Object> disRunFinally();

}
