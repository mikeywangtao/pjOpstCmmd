package orgs.cm.pMqp.pRuncmd.comm;

import java.util.HashMap;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
/**
 * cmd运行后进行的数据推送等
 * */
public abstract class AbsRunAfter {

	public abstract HashMap<String, Object> disRunAfter();
	public abstract void disSetHmpall(HashMap<String, Object> hmpAllp);
	public abstract void disSetClsBaseAttrs(ClsBaseAttrs objBap);
//	public abstract void disSetAll(HashMap<String, Object> hmpAllp);
}
