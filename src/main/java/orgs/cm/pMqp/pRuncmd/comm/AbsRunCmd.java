package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;
/**
 * 执行cmd命令
 * */
public abstract class AbsRunCmd {
	public abstract HashMap<String, Object> disRunCmd();
	
	protected boolean booThrflg = false;
	public abstract void setBooThrflg(boolean booThrflgp);
}
