package orgs.cm.tst.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orgs.cm.tst.dao.TstDao;
import orgs.cm.tst.model.RunCmmd000;

@Service("tstServ")
public class TstServ {
	
	private String strCname = TstServ.class.getName();
	
	@Autowired
	private TstDao tstDao;
	@Autowired
	private RunCmmd000 runCmmd000;
	
	public String disTstServ_Pro(LinkedHashMap<String, Object> lhpParp){
		String strFname = " disTstServ_Pro : ";
		String strRe = "";
		
		System.out.println(strCname + strFname);
		tstDao.disTstDao_Pro(lhpParp);
		
		//
		runCmmd000.disPro000();
		return strRe;
	}
}
