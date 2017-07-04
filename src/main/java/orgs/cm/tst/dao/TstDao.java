package orgs.cm.tst.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("tstDao")
public class TstDao {

	private String strCname = TstDao.class.getName();
	
	@Autowired
	private SqlSessionFactory  sqlSessionFactory ;
	
	public String disTstDao_Pro(LinkedHashMap<String, Object> lhpParp){
		String strFname = " disTstDao_Pro : ";
		String strRe = "";
		try {
			SqlSession session = sqlSessionFactory.openSession();
			List lstRe = session.selectList("orgs.cm.tst.dao.TstDao.getTst");
			System.out.println(strCname + strFname);
		} catch(Exception ex) {
			System.out.println(strCname + strFname + ex);
		}

		
		return strRe;
	}
}
