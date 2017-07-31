package orgs.cm.pMqp.pDbpro;

public class DbInfoSaveAttrs {
	public static final String strSaveFlg_Cp = "cp";
	public static final String strSaveFlg_Run = "run";
	
	/*
  `cp_ids` varchar(32) NOT NULL,
  `cpcls` varchar(32) DEFAULT NULL COMMENT 'contrl/process 控制类名称',
  `customer` varchar(32) DEFAULT NULL COMMENT '命令发起请求客户（或Qz）',
  `ansible_ids` varchar(32) DEFAULT NULL COMMENT '客户命令指向ansible，来源于auth。',
  `ansible_info` text COMMENT '客户命令指向ansible，详细信息json格式，来源于auth。',
  `cmd_tpye` varchar(32) DEFAULT NULL COMMENT '命令类型，来源于cms。',
  `cmd_subtype` varchar(32) DEFAULT NULL COMMENT '命令子类型，来源于cms。',
  `cmd_request` text COMMENT '命令请求全文，json格式。来源于cms或auth。',
  `cmd_inputdt` varchar(32) DEFAULT NULL COMMENT 'cmd系统接收到命令时间。',
  `cpuuid` varchar(32) DEFAULT NULL COMMENT '单次uuid',
  `ceratedt` varchar(32) DEFAULT NULL COMMENT '数据库内行记录插入时间',
  `cmdrundt` varchar(32) DEFAULT NULL COMMENT '程序中输出信息创建时间。',	
	 */
	/** 验证info信息表的存在 */
	public static final String strSqltemp_Se_SaveCpinfo = ""
			+ " INSERT INTO tYYYYXX_cmdCCCCCC_cpoutput ( "
			+ " cp_ids,		cpcls,			customer,		ansible_ids,	ansible_info, "
			+ " cmd_tpye,	cmd_subtype,	cmd_request,	cmd_inputdt,	cpuuid,  "
			+ " ceratedt,   cmdrundt "
			+ " ) VALUES ( "
			+ " '^cp_ids^',   '^cpcls^',       '^customer^',    '^ansible_ids^', '^ansible_info^', "
			+ " '^cmd_tpye^', '^cmd_subtype^', '^cmd_request^', '^cmd_inputdt^', '^cpuuid^',       "
			+ " '^ceratedt^', '^cmdrundt^' "
			+ " ) ";
	
	/*
  `run_ids` varchar(32) NOT NULL,
  `cp_ids` varchar(32) DEFAULT NULL,
  `infotype` varchar(32) DEFAULT NULL COMMENT '输出信息类型：error / info',
  `infoflg` varchar(32) DEFAULT NULL COMMENT '信息大类标记。大类：cp-控制执行；runc-命令运行；shell-脚本内信息；reserr-运行脚本返回异常；resstd-运行脚本标准返回；af-运行脚本后；bef-运行脚本前；prep-命令准备；',
  `infosubflg` varchar(32) DEFAULT NULL COMMENT '信息小类标记。',
  `info` text COMMENT '输出信息，json格式。',
  `cpuuid` varchar(32) DEFAULT NULL COMMENT '单次uuid',
  `createdt` varchar(32) DEFAULT NULL COMMENT '数据库内行记录插入时间',
  `cmdrundt` varchar(32) DEFAULT NULL COMMENT '当前信息创建时间。shell类信息多行相同。reserr/resstd类信息多行相同。',
	 */
	/** 验证info信息表的存在 */
	public static final String strSqltemp_Se_SaveRuninfo = ""
			+ " INSERT INTO tYYYYXX_cmdCCCCCC_runoutput ( "
			+ " run_ids,	cp_ids,	infotype,	infoflg,	infosubflg, "
			+ " info,		cpuuid,	createdt,	cmdrundt "
			+ " ) VALUES ( "
			+ " '^run_ids^',	'^cp_ids^',	'^infotype^',	'^infoflg^',	'^infosubflg^', "
			+ " '^info^',		'^cpuuid^',	'^createdt^',	'^cmdrundt^' "
			+ " ) ";
}
