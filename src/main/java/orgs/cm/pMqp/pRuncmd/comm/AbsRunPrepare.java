package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
/**
 * cmd运行前准备
 * */
public abstract class AbsRunPrepare {
	public abstract void disSetHmpall(HashMap<String, Object> hmpAllp);
	public abstract void disSetClsBaseAttrs(ClsBaseAttrs objBap);
	public abstract HashMap<String, Object> disRunPrepare();

}
