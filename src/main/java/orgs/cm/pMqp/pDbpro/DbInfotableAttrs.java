package orgs.cm.pMqp.pDbpro;

/**
 * 信息存储表 处理 属性集合
 * */
public class DbInfotableAttrs {

	/** 验证info信息表的存在 */
	public static final String strSqltemp_Se_CheckinfoCptab = ""
			+ " SELECT COUNT(1) AS CNTS  "
			+ " FROM INFORMATION_SCHEMA.TABLES "
			+ " WHERE TABLE_SCHEMA='opstcmmd' "
			+ " AND TABLE_NAME='tYYYYXX_cmdCCCCCC_cpoutput'  "; 
			
	/** 创建tYYYYXX_cmdCCCCCC_cpoutput表的sql模板 */
	private static final String  strSqlTemp_Cr_Cptab00 = ""
			+ " /*==============================================================*/ "
			+ " /* Table: tYYYYXX_cmdCCCCCC_cpoutput                            */ "
			+ " /*==============================================================*/ "
			+ " create table tYYYYXX_cmdCCCCCC_cpoutput "
			+ " ( "
			+ "    cp_ids               varchar(32) not null, "
			+ "    cpcls                varchar(256) comment 'contrl/process 控制类名称', "
			+ "    customer             varchar(32) comment '命令发起请求客户（或Qz）', "
			+ "    ansible_ids          varchar(32) comment '客户命令指向ansible，来源于auth。', "
			+ "    ansible_info         text comment '客户命令指向ansible，详细信息json格式，来源于auth。', "
			+ "    cmd_tpye             varchar(32) comment '命令类型，来源于cms。', "
			+ "    cmd_subtype          varchar(32) comment '命令子类型，来源于cms。', "
			+ "    cmd_request          text comment '命令请求全文，json格式。来源于cms或auth。', "
			+ "    cmd_inputdt          varchar(32) comment 'cmd系统接收到命令时间。', "
			+ "    cpuuid               varchar(32) comment '单次uuid', "
			+ "    ceratedt             varchar(32) comment '数据库内行记录插入时间', "
			+ "    cmdrundt             varchar(32) comment '程序中输出信息创建时间。', "
			+ "    primary key (cp_ids) "
			+ " ); ";
	private static final String  strSqlTemp_Cr_Cptab01 = ""
			+ " alter table tYYYYXX_cmdCCCCCC_cpoutput comment 'YYYY 年 XX 周 CCCCCC 类型命令执行信息}}}CCCCCC类型命令采用命令所在package名称16个字符'; ";
	private static final String  strSqlTemp_Cr_Cptab02 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_cpcls                           */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_cpcls on tYYYYXX_cmdCCCCCC_cpoutput (  cpcls ); ";
	private static final String  strSqlTemp_Cr_Cptab03 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_customer                        */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_customer on tYYYYXX_cmdCCCCCC_cpoutput ( customer  ); ";
	private static final String  strSqlTemp_Cr_Cptab04 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_ansibleids                      */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_ansibleids on tYYYYXX_cmdCCCCCC_cpoutput ( ansible_ids ); ";
	private static final String  strSqlTemp_Cr_Cptab05 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_cmdtpye                         */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_cmdtpye on tYYYYXX_cmdCCCCCC_cpoutput ( cmd_tpye ); ";
	private static final String  strSqlTemp_Cr_Cptab06 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_cmdsubtype                      */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_cmdsubtype on tYYYYXX_cmdCCCCCC_cpoutput ( cmd_subtype ); ";
	private static final String  strSqlTemp_Cr_Cptab07 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_cmdinputdt                      */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_cmdinputdt on tYYYYXX_cmdCCCCCC_cpoutput ( cmd_inputdt ); ";
	private static final String  strSqlTemp_Cr_Cptab08 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_cpuuid                          */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_cpuuid on tYYYYXX_cmdCCCCCC_cpoutput ( cpuuid  ); ";
	private static final String  strSqlTemp_Cr_Cptab09 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_ceratedt                        */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_ceratedt on tYYYYXX_cmdCCCCCC_cpoutput ( ceratedt ); ";
	private static final String  strSqlTemp_Cr_Cptab10 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_cp_cmdrundt                        */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_cp_cmdrundt on tYYYYXX_cmdCCCCCC_cpoutput ( cmdrundt ); ";
	public static final String[] subStrSqlTemp_Cr_Cptab = {
			strSqlTemp_Cr_Cptab00
			, strSqlTemp_Cr_Cptab01
			, strSqlTemp_Cr_Cptab02
			, strSqlTemp_Cr_Cptab03
			, strSqlTemp_Cr_Cptab04
			, strSqlTemp_Cr_Cptab05
			, strSqlTemp_Cr_Cptab06
			, strSqlTemp_Cr_Cptab07
			, strSqlTemp_Cr_Cptab08
			, strSqlTemp_Cr_Cptab09
			, strSqlTemp_Cr_Cptab10
			};
	
	/** 验证info信息表的存在 */
	public static final String strSqltemp_Se_CheckinfoRuntab = ""
			+ " SELECT COUNT(1) AS CNTS  "
			+ " FROM INFORMATION_SCHEMA.TABLES "
			+ " WHERE TABLE_SCHEMA='opstcmmd' "
			+ " AND TABLE_NAME='tYYYYXX_cmdCCCCCC_runoutput'  "; 
	/** 创建tYYYYXX_cmdCCCCCC_runoutput表的sql模板 */
	private static final String  strSqlTemp_Cr_Runtab00 = ""
			+ " /*==============================================================*/ "
			+ " /* Table: tYYYYXX_cmdCCCCCC_runoutput                           */ "
			+ " /*==============================================================*/ "
			+ " create table tYYYYXX_cmdCCCCCC_runoutput "
			+ " ( "
			+ "    run_ids              varchar(32) not null, "
			+ "    cp_ids               varchar(32), "
			+ "    infotype             varchar(32) comment '输出信息类型：error / info', "
			+ "    infoflg              varchar(32) comment '信息大类标记。大类：cp-控制执行；runc-命令运行；shell-脚本内信息；reserr-运行脚本返回异常；resstd-运行脚本标准返回；af-运行脚本后；bef-运行脚本前；prep-命令准备；', "
			+ "    infosubflg           varchar(64) comment '信息小类标记。', "
			+ "    info                 text comment '输出信息，json格式。', "
			+ "    cpuuid               varchar(32) comment '单次uuid', "
			+ "    createdt             varchar(32) comment '数据库内行记录插入时间', "
			+ "    cmdrundt             varchar(32) comment '当前信息创建时间。shell类信息多行相同。reserr/resstd类信息多行相同。', "
			+ "    primary key (run_ids) "
			+ " ); ";
	private static final String  strSqlTemp_Cr_Runtab01 = ""
			+ " alter table tYYYYXX_cmdCCCCCC_runoutput comment 'YYYY 年 XX 周 CCCCCC 类型命令运行信息}}} CCCCCC 类型命令采用命令所在package名称16个'; ";
	private static final String  strSqlTemp_Cr_Runtab02 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_cpids                          */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_cpids on tYYYYXX_cmdCCCCCC_runoutput ( cp_ids ); ";
	private static final String  strSqlTemp_Cr_Runtab03 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_infotype                       */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_infotype on tYYYYXX_cmdCCCCCC_runoutput ( infotype ); ";
	private static final String  strSqlTemp_Cr_Runtab04 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_infoflg                        */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_infoflg on tYYYYXX_cmdCCCCCC_runoutput ( infoflg ); ";
	private static final String  strSqlTemp_Cr_Runtab05 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_infosubflg                     */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_infosubflg on tYYYYXX_cmdCCCCCC_runoutput ( infosubflg ); ";
	private static final String  strSqlTemp_Cr_Runtab06 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_cpuuid                         */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_cpuuid on tYYYYXX_cmdCCCCCC_runoutput ( cpuuid ); ";
	private static final String  strSqlTemp_Cr_Runtab07 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_createdt                       */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_createdt on tYYYYXX_cmdCCCCCC_runoutput ( createdt ); ";
	private static final String  strSqlTemp_Cr_Runtab08 = ""
			+ " /*==============================================================*/ "
			+ " /* Index: idx_tYYYYXX_CCCCCC_run_cmdrundt                       */ "
			+ " /*==============================================================*/ "
			+ " create index idx_tYYYYXX_CCCCCC_run_cmdrundt on tYYYYXX_cmdCCCCCC_runoutput ( cmdrundt ); ";
	public static final String[] subStrSqlTemp_Cr_Runtab = {
			strSqlTemp_Cr_Runtab00
			, strSqlTemp_Cr_Runtab01
			, strSqlTemp_Cr_Runtab02
			, strSqlTemp_Cr_Runtab03
			, strSqlTemp_Cr_Runtab04
			, strSqlTemp_Cr_Runtab05
			, strSqlTemp_Cr_Runtab06
			, strSqlTemp_Cr_Runtab07
			, strSqlTemp_Cr_Runtab08
			};
} 
