package orgs.cm.pMqp.pDbpro;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface IBaseDbpro {
	public ArrayList<LinkedHashMap<String, Object>> disSearch(String strSqlp);
}
