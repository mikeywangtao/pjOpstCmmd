package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;
/**
 * cmd运行后进行的数据推送等
 * */
public abstract class AbsRunAfter {

	public abstract HashMap<String, Object> disRunAfter();
	
	public abstract void disSetAll(HashMap<String, Object> hmpAllp);
}
