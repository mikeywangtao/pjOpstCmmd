package orgs.cm.tst.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.Runcmdpro_Getimg;
import orgs.cm.pMqp.pShellpro.AbsShellpro;
import orgs.cm.pMqp.pShellpro.StandardShellpro_Getimg;
import orgs.cm.tst.dao.TstDao;
import orgs.cm.tst.model.RunCmmd000;
import orgs.cm.tst.model.RunCmmd001;
import orgs.cm.tst.model.RunCmmd002;

@Service("tstServ")
public class TstServ {
	
	private final String strCname = TstServ.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	@Autowired
	private TstDao tstDao;
	@Autowired
	private RunCmmd000 runCmmd000;
	@Autowired
	private RunCmmd001 runCmmd001;
	@Autowired
	private RunCmmd002 runCmmd002;
	
	public String disTstServ_Pro(LinkedHashMap<String, Object> lhpParp){
		String strFname = " disTstServ_Pro : ";
		String strRe = "";
		
		System.out.println(strCname + strFname);
		tstDao.disTstDao_Pro(lhpParp);
		

//		ArrayList<String> altPar = new ArrayList<>();
//		altPar.add("alt1");
//		altPar.add("alt2");
//		altPar.add("alt3");
//		Map<String, Object> mapPar = new HashMap<>();
//		mapPar.put("key", "val");
//		mapPar.put("keyy", "vall");
//		mapPar.put("keyyy", altPar);
//		
//		String strPar = JSON.toJSONString(mapPar);
//		HttpClientUtil.sendHttpPostJson("http://127.0.0.1:8080/pjOpStCmmd/tstCtrl/dishtpcReq", strPar);
		
		
		Map<String, Object> mapParAnsible = new HashMap<>(); 
//		mapParAnsible.put("id", "1"); 
//		mapParAnsible.put("ip", "1"); 
//		mapParAnsible.put("sshKey", "1"); 
		String strParAnsible = JSON.toJSONString(mapParAnsible);
		String strAnsible = HttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/ansible/getAnsible", strParAnsible);
		
		Map<String, Object> mapResAnsible = JSON.parseObject(strAnsible, HashMap.class);
		if(mapResAnsible!=null && mapResAnsible.size()>0
				&& mapResAnsible.containsKey("msg") && mapResAnsible.get("msg")!=null
				&& mapResAnsible.containsKey("data") && mapResAnsible.get("data")!=null){
			String strMsg = mapResAnsible.get("msg")==null?null:mapResAnsible.get("msg").toString();
			if("ok".equals(strMsg)){
				String strDataAnsible = mapResAnsible.get("data").toString();
				if(strDataAnsible!=null && strDataAnsible.trim().length()>0){
					List<HashMap> altDataAnsible = JSON.parseArray(strDataAnsible, HashMap.class);
					Object[] subKey = altDataAnsible.get(0).keySet().toArray();
					if(subKey!=null && subKey.length>0){
						for(HashMap map : altDataAnsible){
							System.out.print("Ansible ----");
							for(Object objkey : subKey){
								String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
								System.out.print(objkey + ":" + strVal + ", ");
								/*
								 name
								 */
							}
							System.out.println("");
						}
					}
				}
			}
		}
		
		
		Map<String, Object> mapParKeystone = new HashMap<>(); 
//		mapParKeystone.put("ksId", "1"); 
//		mapParKeystone.put("dom", "1"); 
//		mapParKeystone.put("project", "1"); 
//		mapParKeystone.put("ideenity", "1"); 
//		mapParKeystone.put("imgapiv", "1"); 
//		mapParKeystone.put("ansibleId", "1"); 
		String strParKeystone = JSON.toJSONString(mapParKeystone);
		String strKeystone = HttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/keystone/getKeystone", strParKeystone);
		
		Map<String, Object> mapResKeystone = JSON.parseObject(strKeystone, HashMap.class);
		if(mapResKeystone!=null && mapResKeystone.size()>0
				&& mapResKeystone.containsKey("msg") && mapResKeystone.get("msg")!=null
				&& mapResKeystone.containsKey("data") && mapResKeystone.get("data")!=null){
			String strMsg = mapResKeystone.get("msg")==null?null:mapResKeystone.get("msg").toString();
			if("ok".equals(strMsg)){
				String strDataKeystone = mapResKeystone.get("data").toString();
				if(strDataKeystone!=null && strDataKeystone.trim().length()>0){
					List<HashMap> altDataKeystone = JSON.parseArray(strDataKeystone, HashMap.class);
					Object[] subKey = altDataKeystone.get(0).keySet().toArray();
					if(subKey!=null && subKey.length>0){
						for(HashMap map : altDataKeystone){
							System.out.print("Keystone ----");
							for(Object objkey : subKey){
								String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
								System.out.print(objkey + ":" + strVal + ", ");
								/*
								 * dom
								 * dom
								 * project
								 * userName
								 * password
								 * authUrl
								 * ideenity
								 * imgapiv
								 */
							}
							System.out.println("");
						}
					}
				}
			}
		}
		
		HashMap<String, String> mapPar = new HashMap<>();
		AbsShellpro objShellpro = new StandardShellpro_Getimg(mapPar);
		boolean booFlg = objShellpro.disShellpro();
		
		
//		Map<String, Object> mapRes = JSON.parseObject(strAnsible, HashMap.class);
//		if(mapRes!=null && mapRes.size()>0
//				&& mapRes.containsKey("msg") && mapRes.get("msg")!=null
//				&& mapRes.containsKey("data") && mapRes.get("data")!=null){
//			String strMsg = mapRes.get("msg")==null?null:mapRes.get("msg").toString();
//			if("ok".equals(strMsg)){
//				String strData = mapRes.get("data").toString();
//				if(strData!=null && strData.trim().length()>0){
//					ArrayList altData = JSON.parseObject(strData, ArrayList.class);
//					
//					System.out.println(altData);
//				}
//			}
//		}
			
//		runCmmd000.disPro000();
//		runCmmd001.disPro000();
//		runCmmd002.disPro000();
		Runcmdpro_Getimg objRcGetimg = new Runcmdpro_Getimg();
		objRcGetimg.disRuncmdPro();
		
//		try {
//			for(int i=0; i<2000; i++){
//				
//				Runcmdpro_Getimg objRcGetimg = new Runcmdpro_Getimg();
//				objRcGetimg.disRuncmdPro();
//				Thread.sleep(3000);
//				Runcmdpro_Getflv objRuncmdpro_Getflv = new Runcmdpro_Getflv();
//				objRuncmdpro_Getflv.disRuncmdPro();
//				Thread.sleep(3000);
//				Runcmdpro_Getnetw objRuncmdpro_Getnetw = new Runcmdpro_Getnetw();
//				objRuncmdpro_Getnetw.disRuncmdPro();
//				
//				System.out.println(" disRuncmdPro Run ----" + i);
//				Thread.sleep(10000);
//			}
//		} catch(Exception ex) {
//			long lonFlg = System.currentTimeMillis();
//			logger.error(strCname + strFname + ex + "||" + lonFlg);
//			StackTraceElement[] subSte = ex.getStackTrace();
//			for(int i=0; i<subSte.length; i++){
//				logger.error(
//						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
//			}
//		}

		return strRe;
	}
}
