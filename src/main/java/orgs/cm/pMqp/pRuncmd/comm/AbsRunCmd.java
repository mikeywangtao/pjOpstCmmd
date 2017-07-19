package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;
/**
 * 执行cmd命令
 * */
public abstract class AbsRunCmd {
	public abstract HashMap<String, Object> disRunCmd();
	
	protected String strThrflg = "";
	public abstract void setStrThrflg(String strThrflgp);
	public abstract void disSetAll(HashMap<String, Object> hmpAllp);
}
