package orgs.cm.tst.dao;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Repository;

@Repository("tstDao")
public class TstDao {

	private String strCname = TstDao.class.getName();
	
	public String disTstDao_Pro(LinkedHashMap<String, Object> lhpParp){
		String strFname = " disTstDao_Pro : ";
		String strRe = "";
		
		System.out.println(strCname + strFname);
		
		return strRe;
	}
}
