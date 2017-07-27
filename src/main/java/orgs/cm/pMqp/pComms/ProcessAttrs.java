package orgs.cm.pMqp.pComms;

public class ProcessAttrs {

	/*
	 信息大类标记。大类：
	 cp-控制执行；
	 runc-命令运行；
	 shell-脚本内信息；
	 reserr-运行脚本返回异常；
	 resstd-运行脚本标准返回；
	 aft-运行脚本后；
	 bef-运行脚本前；
	 prep-命令准备；
	 */
	public static final String strInfoFlgKey_Cp ="cp";
	public static final String strInfoFlgKey_Runc ="runc";
	public static final String strInfoFlgKey_Shell ="shell";
	public static final String strInfoFlgKey_Res ="res";
	public static final String strInfoFlgKey_Reserr ="reserr";
	public static final String strInfoFlgKey_Resstd ="resstd";
	public static final String strInfoFlgKey_Resstdf = "resstdf";
	public static final String strInfoFlgKey_Aft ="aft";
	public static final String strInfoFlgKey_Bef ="bef";
	public static final String strInfoFlgKey_Prep ="prep";
	
	public static final String strInfoFlg_PRS = "point}}}runFlg}}}start";
	public static final String strInfoFlg_PRE = "point}}}runFlg}}}end";
	public static final String strInfoFlg_PRx = "point}}}runFlg}}}";
	public static final String strInfoFlg_PAx = "point}}}attrInfo}}}";
	public static final String strInfoFlg_PLx = "point}}}loopPoint}}}";
	public static final String strInfoFlg_ETx = "error}}}eTrace}}}";
	public static final String strInfoFlg_EEx = "error}}}exception}}}";
	
	
	public static final String strInfoKey_Info ="info"; 
	public static final String strInfoType_Info ="type"; //error? point? 
	public static final String strInfoFlg_Info ="flg"; //attrInfo? runFlg? loopPoint? eTrace? exception?
	public static final String strInfoSubflg_Info ="subflg"; //
	public static final String strInfoKey_Rundt = "cmdrundt";
	public static final String strInfoKey_Cpuuid = "cpuuid";
	
	public static final String strParmapKey_Infobase = "infobase";
	public static final String strParmapKey_Param = "param";
	public static final String strParmapKey_Cplst = "cplst";
	public static final String strParmapKey_Ppalst = "ppalst";
	public static final String strParmapKey_Runlst = "runlst";
//	public static final String strParmapKey_Reslst = "reslst";
	public static final String strParmapKey_Beflst = "beflst";
	public static final String strParmapKey_Aftlst = "aftlst";
	public static final String strParmapKey_Inpars = "inpars";
	
	public static final String strParmapKey_Ppa_Cmdi = "cmdi";
	public static final String strParmapKey_Ppa_Cmdids = "cmdids";
	public static final String strParmapKey_Ppa_Cmdsh = "cmdsh";
	public static final String strParmapKey_Ppa_Cmdpar = "cmdpar";
	public static final String strParmapKey_Ppa_Cmdshr = "cmdshr";
	
	public static final String strParmapKey_Ppa_ShFileroot = "strShFileroot";
	public static final String strParmapKey_Ppa_ShFilename = "strShFilename";
	public static final String strParmapKey_Ppa_ShFilecflg = "strShFilecflg";
	public static final String strParmapKey_Ppa_RunShCmmd = "strRunShCmmd";
	public static final String strParmapKey_Ppa_NowRunCmdids = "strNowRunCmdids";
	public static final String strParmapKey_Ppa_NowRunflg = "strNowRunflg";
	public static final String strParmapKey_Ppa_NowPostfix = "strNowPostfix";
	public static final String strParmapKey_Ppa_RunLoopFlg = "strRunLoopFlg";
}
