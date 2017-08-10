package orgs.cm.pMqp.pRuncmd.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
/**
 * 执行cmd命令
 * */
public abstract class AbsRunCmd {
	public abstract HashMap<String, Object> disRunCmd();
	
	protected String strThrflg = "";
	public abstract void setStrThrflg(String strThrflgp);
//	public abstract void disSetAll(HashMap<String, Object> hmpAllp);
	public abstract void disSetHmpall(HashMap<String, Object> hmpAllp);
	public abstract void disSetClsBaseAttrs(ClsBaseAttrs objBap);
	public abstract void setRuncres(String strflgp, ArrayList<LinkedHashMap<String, String>> altRunc);
}
