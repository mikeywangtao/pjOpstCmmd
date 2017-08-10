package orgs.cm.pMqp.pComms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import orgs.cm.pMqp.pDbpro.SaveInfoPro;

/**
 * 基础处理对象
 * */
public class ClsBaseAttrs {
//	private OutputLogPro objOutputLogPro = new OutputLogPro(strCname);
//	private SetInfoPro objSetInfoPro = new SetInfoPro(strCname);
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
//	public SaveInfoPro objSaveInfoPro = null;
	public OutputLogPro objOutputLogPro = null;
	public SetInfoPro objSetInfoPro = null;
	public LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<>();
	public ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();
	
	private ClsBaseAttrs(){}
	public ClsBaseAttrs(String strCnamep){
//		objSaveInfoPro = new SaveInfoPro(strCnamep);
		objOutputLogPro = new OutputLogPro(strCnamep);
		objSetInfoPro = new SetInfoPro(strCnamep);
		disClear_lhpInfobase();
		disClear_altRunc();
	}
	
	public void disClear_altRunc(){
		if(altRunc!=null){
			altRunc.clear();
		} else {
			altRunc = new ArrayList<LinkedHashMap<String, String>>(); 
		}
	}
	public void disClear_lhpInfobase(){
		if(lhpInfobase!=null){
			lhpInfobase.clear();
		} else {
			lhpInfobase = new LinkedHashMap<>();
		}
	}
}
