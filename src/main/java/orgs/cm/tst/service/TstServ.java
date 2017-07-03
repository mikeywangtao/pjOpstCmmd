package orgs.cm.tst.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orgs.cm.tst.dao.TstDao;

@Service("tstServ")
public class TstServ {
	
	private String strCname = TstServ.class.getName();
	
	@Autowired
	private TstDao tstDao;
	
	public String disTstServ_Pro(LinkedHashMap<String, Object> lhpParp){
		String strFname = " disTstServ_Pro : ";
		String strRe = "";
		
		System.out.println(strCname + strFname);
		tstDao.disTstDao_Pro(lhpParp);
		
		return strRe;
	}
}
