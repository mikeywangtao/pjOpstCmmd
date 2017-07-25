package orgs.cm.pMqp.pComms;

public class ProcessSql_Qz {

	public static final String strQzSql_Search_Cmdi = ""
			+ " SELECT " 
			+ "  t01.cmdi_ids " 
			+ "  ,t01.req_type " 
			+ "  ,t01.req_subtype " 
			+ "  ,t01.cmmd " 
			+ "  ,t01.states " 
			+ " FROM cmd_info t01 " 
			+ " WHERE 1=1 " 
			+ " AND t01.req_type = '^req_type^' " 
			+ " AND t01.req_subtype = '^req_subtype^' " 
			+ " ORDER BY orders ";
	
	public static final String strQzSql_Search_Cmdsh = ""
			+ " SELECT " 
			+ "  t01.smdsh_ids " 
			+ "  ,t01.cmdi_ids " 
			+ "  ,t01.shell_line " 
			+ "  ,t01.states " 
			+ "  ,t01.orders " 
			+ "  ,t02.orders AS runorder " 
			+ " FROM cmd_shell t01 LEFT JOIN cmd_info t02 on t01.cmdi_ids=t02.cmdi_ids " 
			+ " WHERE 1=1 " 
			+ " AND t01.cmdi_ids in( ^cmdi_ids^ )" 
			+ " ORDER BY cmdi_ids, orders " ;
			
	public static final String strQzSql_Search_Cmdpar = ""
			+ " SELECT " 
			+ "  t01.cmdpa_ids " 
			+ "  ,t01.cmdi_ids " 
			+ "  ,t01.par_flg " 
			+ "  ,t01.states " 
			+ " FROM cmd_pars t01 LEFT JOIN cmd_info t02 ON t01.cmdi_ids=t02.cmdi_ids "  
			+ " WHERE 1=1 " 
			+ " AND t01.cmdi_ids in( ^cmdi_ids^ )" ;
}
