package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.CmdStreamGobbler;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.RunCmd_Getimg;

public class RunCmd_C00_1 extends AbsRunCmd {

	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_C00_1.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void setRuncres(String strflgp, ArrayList<LinkedHashMap<String, String>> altRunc){
		if(strflgp!=null && strflgp.trim().length()>0){
			if("ERR".equals(strflgp)){
				hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, altRunc);
			}
			if("STD".equals(strflgp)){
				hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, altRunc);
			}
		}
	}
	
	public void setStrThrflg(String strThrflgp){
		this.strThrflg = strThrflgp;
	}
	public HashMap<String, Object> disRunCmd() {
		disRuncmd();
		return hmpAll;
	}
	public void disSetAll(HashMap<String, Object> hmpAllp){
		this.hmpAll = hmpAllp;
	}
	private void disRuncmd(){
		String strFname = " disRuncmd : ";
		long lonBasrDt = new Date().getTime();
		Process process = null;
		CmdStreamGobbler errorGobbler = null;
		CmdStreamGobbler outputGobbler = null;
		String strInfo = "";
		String strstrCpuuid = null;
		
		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();		
		try {
			logger.info(strCname + strFname + "  Start!");
			hmpAll.put(ProcessAttrs.strParmapKey_Runlst, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, null);
			lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Runc);

			if(hmpAll!=null && hmpAll.containsKey(ProcessAttrs.strInfoKey_Cpuuid)){
				strstrCpuuid = hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid)==null?
						null:hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid).toString();
			}
			if(strstrCpuuid==null || (strstrCpuuid!=null && strstrCpuuid.trim().length()==0)){
				strInfo = strCname + strFname + " CpUuid 异常!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			if(hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)!=null
					&& !("t".equals(hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg).toString()))
					){
				strInfo = strCname + strFname + " Shell File create 失败!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			String strFileroot = null;
			String strFilename = null;
			String strPostfix = null;
			String strNowRunflg = null;
			String StrCommand = null;
			String strAnsCmmd = null;
			if(hmpAll.containsKey("^anscmmd^")
					&& hmpAll.get("^anscmmd^")!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFileroot)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilename)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowPostfix)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowPostfix)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
					){
				strNowRunflg = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
				strPostfix = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowPostfix).toString();
				strFileroot = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot).toString();
				strFilename = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename).toString(); 
				StrCommand = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString(); 
				StrCommand = StrCommand.split(",")[Integer.parseInt(strNowRunflg)-1];
				strAnsCmmd = hmpAll.get("^anscmmd^").toString(); 
			}
			if(StrCommand!=null && StrCommand.trim().length()>0 && StrCommand.indexOf(",")==-1
					&& strFileroot!=null && strFileroot.trim().length()>0
					&& strFilename!=null && strFilename.trim().length()>0){
				StrCommand = StrCommand.replaceAll("\\^anscmmd\\^", strAnsCmmd);
				StrCommand = StrCommand.replaceAll("\\^shell_allpath\\^", strFileroot+strFilename+strPostfix);
			} else {
				strInfo = strCname + strFname + " Shell cmmd 构建失败!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			/* ------------------------------------------------------------------------------- */
//			String StrCommand = "ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u root "; //查看镜像
/* 
{cmdids=201707240000,201707240001,201707240002, strNowPostfix=_1.sh, ^ansid^=1, reserr=null, strNowRunflg=1, runlst=[{type=runc, info=orgs.cm.pMqp.pRuncmd.pjCreate00.RunCmd_C00_1 disRuncmd :  创建VM Start----20170725153130407, subtype=info, rundt=20170725153131145}, {type=runc, info=orgs.cm.pMqp.pRuncmd.pjCreate00.RunCmd_C00_1 disRuncmd :  创建VM Cmmd----ansible openstack -m script -a  '/home/anshells/201730/20170725153051296_CREATECREATE00_1.sh' -u root , subtype=info, rundt=20170725153132150}, {type=runc, info=orgs.cm.pMqp.pRuncmd.pjCreate00.RunCmd_C00_1 disRuncmd :  SDT 超时！, subtype=info, rundt=2017072515321255}, {type=runc, info=orgs.cm.pMqp.pRuncmd.pjCreate00.RunCmd_C00_1 disRuncmd :  ERR 超时！, subtype=info, rundt=2017072515321256}, {type=runc, info=orgs.cm.pMqp.pRuncmd.pjCreate00.RunCmd_C00_1 disRuncmd :  查看镜像 End----2017072515321256, subtype=info, rundt=2017072515321256}], resstd=null, strShFilename=/20170725153051296_CREATECREATE00, strRunShCmmd=ansible ^anscmmd^ -m script -a  '^shell_allpath^' -u root ,ansible ^anscmmd^ -m script -a  '^shell_allpath^' -u root ,ansible ^anscmmd^ -m script -a  '^shell_allpath^' -u root ,, ^customerids^=20170725000, ^anscmmd^=openstack, inpars={^pname^=admin, ^ansid^=1, ^pdom^=Default, ^ideapi^=3, ^uname^=admin, ^pass^=admin, ^customerids^=20170725000, ^imgids^=20022a68-bc87-462d-ba6c-af6570ba839e, ^shell_allpath^=null, ^udom^=Default, ^anscmmd^=openstack, ^vmname^=vm-d35274b78826461aafe73a25f471a5df, ^netwids^=aedbece2-0b64-4879-94e5-461439cd6930, ^req_type^=CREATE, ^req_subtype^=CREATE00, ^devids^=null, ^authurl^=http://test-controller:5000/v3, ^flvids^=0, ^imgapi^=2, ^devname^=dev-d35274b78826461aafe73a25f471a5df}, cpuuid=d35274b78826461aafe73a25f471a5df, strShFilecflg=t, ^req_type^=CREATE, ^req_subtype^=CREATE00, ppalst={cmdi=[{cmdi_ids=201707240000, req_type=CREATE, req_subtype=CREATE00, cmmd=ansible ^anscmmd^ -m script -a  '^shell_allpath^' -u root , states=创建标准VM}, {cmdi_ids=201707240001, req_type=CREATE, req_subtype=CREATE00, cmmd=ansible ^anscmmd^ -m script -a  '^shell_allpath^' -u root , states=创建标准VM}, {cmdi_ids=201707240002, req_type=CREATE, req_subtype=CREATE00, cmmd=ansible ^anscmmd^ -m script -a  '^shell_allpath^' -u root , states=创建标准VM}], cmdsh=[{smdsh_ids=201707240100, cmdi_ids=201707240000, shell_line=#!/bin/bash, states=1s, orders=1, runorder=1}, {smdsh_ids=201707240101, cmdi_ids=201707240000, shell_line=export OS_PROJECT_DOMAIN_NAME=^pdom^, states=2s, orders=2, runorder=1}, {smdsh_ids=201707240102, cmdi_ids=201707240000, shell_line=export OS_USER_DOMAIN_NAME=^udom^, states=3s, orders=3, runorder=1}, {smdsh_ids=201707240103, cmdi_ids=201707240000, shell_line=export OS_PROJECT_NAME=^pname^, states=4s, orders=4, runorder=1}, {smdsh_ids=201707240104, cmdi_ids=201707240000, shell_line=export OS_USERNAME=^uname^, states=5s, orders=5, runorder=1}, {smdsh_ids=201707240105, cmdi_ids=201707240000, shell_line=export OS_PASSWORD=^pass^, states=6s, orders=6, runorder=1}, {smdsh_ids=201707240106, cmdi_ids=201707240000, shell_line=export OS_AUTH_URL=^authurl^, states=7s, orders=7, runorder=1}, {smdsh_ids=201707240107, cmdi_ids=201707240000, shell_line=export OS_IDENTITY_API_VERSION=^ideapi^, states=8s, orders=8, runorder=1}, {smdsh_ids=201707240108, cmdi_ids=201707240000, shell_line=export OS_IMAGE_API_VERSION=^imgapi^, states=9s, orders=9, runorder=1}, {smdsh_ids=201707240109, cmdi_ids=201707240000, shell_line=cinder create --image-id ^imgids^  --name ^devname^ 1 , states=10s, orders=10, runorder=1}, {smdsh_ids=201707240200, cmdi_ids=201707240001, shell_line=#!/bin/bash, states=1s, orders=1, runorder=2}, {smdsh_ids=201707240201, cmdi_ids=201707240001, shell_line=export OS_PROJECT_DOMAIN_NAME=^pdom^, states=2s, orders=2, runorder=2}, {smdsh_ids=201707240202, cmdi_ids=201707240001, shell_line=export OS_USER_DOMAIN_NAME=^udom^, states=3s, orders=3, runorder=2}, {smdsh_ids=201707240203, cmdi_ids=201707240001, shell_line=export OS_PROJECT_NAME=^pname^, states=4s, orders=4, runorder=2}, {smdsh_ids=201707240204, cmdi_ids=201707240001, shell_line=export OS_USERNAME=^uname^, states=5s, orders=5, runorder=2}, {smdsh_ids=201707240205, cmdi_ids=201707240001, shell_line=export OS_PASSWORD=^pass^, states=6s, orders=6, runorder=2}, {smdsh_ids=201707240206, cmdi_ids=201707240001, shell_line=export OS_AUTH_URL=^authurl^, states=7s, orders=7, runorder=2}, {smdsh_ids=201707240207, cmdi_ids=201707240001, shell_line=export OS_IDENTITY_API_VERSION=^ideapi^, states=8s, orders=8, runorder=2}, {smdsh_ids=201707240208, cmdi_ids=201707240001, shell_line=export OS_IMAGE_API_VERSION=^imgapi^, states=9s, orders=9, runorder=2}, {smdsh_ids=201707240209, cmdi_ids=201707240001, shell_line=cinder list , states=10s, orders=10, runorder=2}, {smdsh_ids=201707240300, cmdi_ids=201707240002, shell_line=#!/bin/bash, states=1s, orders=1, runorder=3}, {smdsh_ids=201707240301, cmdi_ids=201707240002, shell_line=export OS_PROJECT_DOMAIN_NAME=^pdom^, states=2s, orders=2, runorder=3}, {smdsh_ids=201707240302, cmdi_ids=201707240002, shell_line=export OS_USER_DOMAIN_NAME=^udom^, states=3s, orders=3, runorder=3}, {smdsh_ids=201707240303, cmdi_ids=201707240002, shell_line=export OS_PROJECT_NAME=^pname^, states=4s, orders=4, runorder=3}, {smdsh_ids=201707240304, cmdi_ids=201707240002, shell_line=export OS_USERNAME=^uname^, states=5s, orders=5, runorder=3}, {smdsh_ids=201707240305, cmdi_ids=201707240002, shell_line=export OS_PASSWORD=^pass^, states=6s, orders=6, runorder=3}, {smdsh_ids=201707240306, cmdi_ids=201707240002, shell_line=export OS_AUTH_URL=^authurl^, states=7s, orders=7, runorder=3}, {smdsh_ids=201707240307, cmdi_ids=201707240002, shell_line=export OS_IDENTITY_API_VERSION=^ideapi^, states=8s, orders=8, runorder=3}, {smdsh_ids=201707240308, cmdi_ids=201707240002, shell_line=export OS_IMAGE_API_VERSION=^imgapi^, states=9s, orders=9, runorder=3}, {smdsh_ids=201707240309, cmdi_ids=201707240002, shell_line=nova boot  --flavor ^flvids^ --image ^imgids^  --nic net-id=^netwids^  --block-device-mapping vda=^devids^:::1 ^vmname^ , states=10s, orders=10, runorder=3}], cmdpar=[{cmdpa_ids=201707250100, cmdi_ids=201707240000, par_flg=^pdom^, states=null}, {cmdpa_ids=201707250101, cmdi_ids=201707240000, par_flg=^udom^, states=null}, {cmdpa_ids=201707250102, cmdi_ids=201707240000, par_flg=^pname^, states=null}, {cmdpa_ids=201707250103, cmdi_ids=201707240000, par_flg=^uname^, states=null}, {cmdpa_ids=201707250104, cmdi_ids=201707240000, par_flg=^pass^, states=null}, {cmdpa_ids=201707250105, cmdi_ids=201707240000, par_flg=^authurl^, states=null}, {cmdpa_ids=201707250106, cmdi_ids=201707240000, par_flg=^ideapi^, states=null}, {cmdpa_ids=201707250107, cmdi_ids=201707240000, par_flg=^imgapi^, states=null}, {cmdpa_ids=201707250108, cmdi_ids=201707240000, par_flg=^anscmmd^, states=null}, {cmdpa_ids=201707250109, cmdi_ids=201707240000, par_flg=^shell_allpath^, states=null}, {cmdpa_ids=201707250110, cmdi_ids=201707240000, par_flg=^imgids^, states=null}, {cmdpa_ids=201707250111, cmdi_ids=201707240000, par_flg=^devname^, states=null}, {cmdpa_ids=201707250112, cmdi_ids=201707240000, par_flg=^flvids^, states=null}, {cmdpa_ids=201707250113, cmdi_ids=201707240000, par_flg=^netwids^, states=null}, {cmdpa_ids=201707250114, cmdi_ids=201707240000, par_flg=^devids^, states=null}, {cmdpa_ids=201707250115, cmdi_ids=201707240000, par_flg=^vmname^, states=null}, {cmdpa_ids=201707250200, cmdi_ids=201707240001, par_flg=^pdom^, states=null}, {cmdpa_ids=201707250201, cmdi_ids=201707240001, par_flg=^udom^, states=null}, {cmdpa_ids=201707250202, cmdi_ids=201707240001, par_flg=^pname^, states=null}, {cmdpa_ids=201707250203, cmdi_ids=201707240001, par_flg=^uname^, states=null}, {cmdpa_ids=201707250204, cmdi_ids=201707240001, par_flg=^pass^, states=null}, {cmdpa_ids=201707250205, cmdi_ids=201707240001, par_flg=^authurl^, states=null}, {cmdpa_ids=201707250206, cmdi_ids=201707240001, par_flg=^ideapi^, states=null}, {cmdpa_ids=201707250207, cmdi_ids=201707240001, par_flg=^imgapi^, states=null}, {cmdpa_ids=201707250208, cmdi_ids=201707240001, par_flg=^anscmmd^, states=null}, {cmdpa_ids=201707250209, cmdi_ids=201707240001, par_flg=^shell_allpath^, states=null}, {cmdpa_ids=201707250210, cmdi_ids=201707240001, par_flg=^imgids^, states=null}, {cmdpa_ids=201707250211, cmdi_ids=201707240001, par_flg=^devname^, states=null}, {cmdpa_ids=201707250212, cmdi_ids=201707240001, par_flg=^flvids^, states=null}, {cmdpa_ids=201707250213, cmdi_ids=201707240001, par_flg=^netwids^, states=null}, {cmdpa_ids=201707250214, cmdi_ids=201707240001, par_flg=^devids^, states=null}, {cmdpa_ids=201707250215, cmdi_ids=201707240001, par_flg=^vmname^, states=null}, {cmdpa_ids=201707250300, cmdi_ids=201707240002, par_flg=^pdom^, states=null}, {cmdpa_ids=201707250301, cmdi_ids=201707240002, par_flg=^udom^, states=null}, {cmdpa_ids=201707250302, cmdi_ids=201707240002, par_flg=^pname^, states=null}, {cmdpa_ids=201707250303, cmdi_ids=201707240002, par_flg=^uname^, states=null}, {cmdpa_ids=201707250304, cmdi_ids=201707240002, par_flg=^pass^, states=null}, {cmdpa_ids=201707250305, cmdi_ids=201707240002, par_flg=^authurl^, states=null}, {cmdpa_ids=201707250306, cmdi_ids=201707240002, par_flg=^ideapi^, states=null}, {cmdpa_ids=201707250307, cmdi_ids=201707240002, par_flg=^imgapi^, states=null}, {cmdpa_ids=201707250308, cmdi_ids=201707240002, par_flg=^anscmmd^, states=null}, {cmdpa_ids=201707250309, cmdi_ids=201707240002, par_flg=^shell_allpath^, states=null}, {cmdpa_ids=201707250310, cmdi_ids=201707240002, par_flg=^imgids^, states=null}, {cmdpa_ids=201707250311, cmdi_ids=201707240002, par_flg=^devname^, states=null}, {cmdpa_ids=201707250312, cmdi_ids=201707240002, par_flg=^flvids^, states=null}, {cmdpa_ids=201707250313, cmdi_ids=201707240002, par_flg=^netwids^, states=null}, {cmdpa_ids=201707250314, cmdi_ids=201707240002, par_flg=^devids^, states=null}, {cmdpa_ids=201707250315, cmdi_ids=201707240002, par_flg=^vmname^, states=null}]}, strShFileroot=/home/anshells/201730, cmdshr=[201707240000}}}#!/bin/bash, 201707240000}}}#!/bin/bash, 201707240000}}}export OS_PROJECT_DOMAIN_NAME=Default, 201707240000}}}export OS_PROJECT_DOMAIN_NAME=Default, 201707240000}}}export OS_USER_DOMAIN_NAME=Default, 201707240000}}}export OS_USER_DOMAIN_NAME=Default, 201707240000}}}export OS_PROJECT_NAME=admin, 201707240000}}}export OS_PROJECT_NAME=admin, 201707240000}}}export OS_USERNAME=admin, 201707240000}}}export OS_USERNAME=admin, 201707240000}}}export OS_PASSWORD=admin, 201707240000}}}export OS_PASSWORD=admin, 201707240000}}}export OS_AUTH_URL=http://test-controller:5000/v3, 201707240000}}}export OS_AUTH_URL=http://test-controller:5000/v3, 201707240000}}}export OS_IDENTITY_API_VERSION=3, 201707240000}}}export OS_IDENTITY_API_VERSION=3, 201707240000}}}export OS_IMAGE_API_VERSION=2, 201707240000}}}export OS_IMAGE_API_VERSION=2, 201707240000}}}cinder create --image-id 20022a68-bc87-462d-ba6c-af6570ba839e  --name dev-d35274b78826461aafe73a25f471a5df 1 , 201707240000}}}cinder create --image-id 20022a68-bc87-462d-ba6c-af6570ba839e  --name dev-d35274b78826461aafe73a25f471a5df 1 ]}
创建VM STD line: 10.167.212.1 | SUCCESS => {
创建VM STD line:     "changed": true, 
创建VM STD line:     "rc": 0, 
创建VM STD line:     "stderr": "", 
创建VM STD line:     "stdout": "+--------------------------------+--------------------------------------+\r\n| Property                       | Value                                |\r\n+--------------------------------+--------------------------------------+\r\n| attachments                    | []                                   |\r\n| availability_zone              | nova                                 |\r\n| bootable                       | false                                |\r\n| consistencygroup_id            | None                                 |\r\n| created_at                     | 2017-07-25T07:32:12.000000           |\r\n| description                    | None                                 |\r\n| encrypted                      | False                                |\r\n| id                             | aacf0e60-d280-4726-b1d0-e4f117b36abd |\r\n| metadata                       | {}                                   |\r\n| migration_status               | None                                 |\r\n| multiattach                    | False                                |\r\n| name                           | dev-d35274b78826461aafe73a25f471a5df |\r\n| os-vol-host-attr:host          | None                                 |\r\n| os-vol-mig-status-attr:migstat | None                                 |\r\n| os-vol-mig-status-attr:name_id | None                                 |\r\n| os-vol-tenant-attr:tenant_id   | 0f50d3fbc4f64fb6b0e892d56ff47ced     |\r\n| replication_status             | disabled                             |\r\n| size                           | 1                                    |\r\n| snapshot_id                    | None                                 |\r\n| source_volid                   | None                                 |\r\n| status                         | creating                             |\r\n| updated_at                     | None                                 |\r\n| user_id                        | fdb93da2369e494ea091004e9253013f     |\r\n| volume_type                    | None                                 |\r\n+--------------------------------+--------------------------------------+\r\n+--------------------------------+--------------------------------------+\r\n| Property                       | Value                                |\r\n+--------------------------------+--------------------------------------+\r\n| attachments                    | []                                   |\r\n| availability_zone              | nova                                 |\r\n| bootable                       | false                                |\r\n| consistencygroup_id            | None                                 |\r\n| created_at                     | 2017-07-25T07:32:14.000000           |\r\n| description                    | None                                 |\r\n| encrypted                      | False                                |\r\n| id                             | d5e45fda-3552-449b-8fce-d8aa23179ba5 |\r\n| metadata                       | {}                                   |\r\n| migration_status               | None                                 |\r\n| multiattach                    | False                                |\r\n| name                           | dev-d35274b78826461aafe73a25f471a5df |\r\n| os-vol-host-attr:host          | None                                 |\r\n| os-vol-mig-status-attr:migstat | None                                 |\r\n| os-vol-mig-status-attr:name_id | None                                 |\r\n| os-vol-tenant-attr:tenant_id   | 0f50d3fbc4f64fb6b0e892d56ff47ced     |\r\n| replication_status             | disabled                             |\r\n| size                           | 1                                    |\r\n| snapshot_id                    | None                                 |\r\n| source_volid                   | None                                 |\r\n| status                         | creating                             |\r\n| updated_at                     | None                                 |\r\n| user_id                        | fdb93da2369e494ea091004e9253013f     |\r\n| volume_type                    | None                                 |\r\n+--------------------------------+--------------------------------------+\r\n", 
创建VM STD line:     "stdout_lines": [
创建VM STD line:         "+--------------------------------+--------------------------------------+", 
创建VM STD line:         "| Property                       | Value                                |", 
创建VM STD line:         "+--------------------------------+--------------------------------------+", 
创建VM STD line:         "| attachments                    | []                                   |", 
创建VM STD line:         "| availability_zone              | nova                                 |", 
创建VM STD line:         "| bootable                       | false                                |", 
创建VM STD line:         "| consistencygroup_id            | None                                 |", 
创建VM STD line:         "| created_at                     | 2017-07-25T07:32:12.000000           |", 
创建VM STD line:         "| description                    | None                                 |", 
创建VM STD line:         "| encrypted                      | False                                |", 
创建VM STD line:         "| id                             | aacf0e60-d280-4726-b1d0-e4f117b36abd |", 
创建VM STD line:         "| metadata                       | {}                                   |", 
创建VM STD line:         "| migration_status               | None                                 |", 
创建VM STD line:         "| multiattach                    | False                                |", 
创建VM STD line:         "| name                           | dev-d35274b78826461aafe73a25f471a5df |", 
创建VM STD line:         "| os-vol-host-attr:host          | None                                 |", 
创建VM STD line:         "| os-vol-mig-status-attr:migstat | None                                 |", 
创建VM STD line:         "| os-vol-mig-status-attr:name_id | None                                 |", 
创建VM STD line:         "| os-vol-tenant-attr:tenant_id   | 0f50d3fbc4f64fb6b0e892d56ff47ced     |", 
创建VM STD line:         "| replication_status             | disabled                             |", 
创建VM STD line:         "| size                           | 1                                    |", 
创建VM STD line:         "| snapshot_id                    | None                                 |", 
创建VM STD line:         "| source_volid                   | None                                 |", 
创建VM STD line:         "| status                         | creating                             |", 
创建VM STD line:         "| updated_at                     | None                                 |", 
创建VM STD line:         "| user_id                        | fdb93da2369e494ea091004e9253013f     |", 
创建VM STD line:         "| volume_type                    | None                                 |", 
创建VM STD line:         "+--------------------------------+--------------------------------------+", 
创建VM STD line:         "+--------------------------------+--------------------------------------+", 
创建VM STD line:         "| Property                       | Value                                |", 
创建VM STD line:         "+--------------------------------+--------------------------------------+", 
创建VM STD line:         "| attachments                    | []                                   |", 
创建VM STD line:         "| availability_zone              | nova                                 |", 
创建VM STD line:         "| bootable                       | false                                |", 
创建VM STD line:         "| consistencygroup_id            | None                                 |", 
创建VM STD line:         "| created_at                     | 2017-07-25T07:32:14.000000           |", 
创建VM STD line:         "| description                    | None                                 |", 
创建VM STD line:         "| encrypted                      | False                                |", 
创建VM STD line:         "| id                             | d5e45fda-3552-449b-8fce-d8aa23179ba5 |", 
创建VM STD line:         "| metadata                       | {}                                   |", 
创建VM STD line:         "| migration_status               | None                                 |", 
创建VM STD line:         "| multiattach                    | False                                |", 
创建VM STD line:         "| name                           | dev-d35274b78826461aafe73a25f471a5df |", 
创建VM STD line:         "| os-vol-host-attr:host          | None                                 |", 
创建VM STD line:         "| os-vol-mig-status-attr:migstat | None                                 |", 
创建VM STD line:         "| os-vol-mig-status-attr:name_id | None                                 |", 
创建VM STD line:         "| os-vol-tenant-attr:tenant_id   | 0f50d3fbc4f64fb6b0e892d56ff47ced     |", 
创建VM STD line:         "| replication_status             | disabled                             |", 
创建VM STD line:         "| size                           | 1                                    |", 
创建VM STD line:         "| snapshot_id                    | None                                 |", 
创建VM STD line:         "| source_volid                   | None                                 |", 
创建VM STD line:         "| status                         | creating                             |", 
创建VM STD line:         "| updated_at                     | None                                 |", 
创建VM STD line:         "| user_id                        | fdb93da2369e494ea091004e9253013f     |", 
创建VM STD line:         "| volume_type                    | None                                 |", 
创建VM STD line:         "+--------------------------------+--------------------------------------+"
创建VM STD line:     ]
创建VM STD line: }
*/
			
//			StrCommand = StrCommand.substring(0, StrCommand.length()-1);
			strInfo = strCname + strFname + " 创建VM Start----" + DatePro.disGetStrdate4NowObjSdf001();
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
			strInfo = strCname + strFname + " 创建VM Cmmd----" + StrCommand;
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
			logger.info(strInfo);
			logger.info(strInfo);
			
			process = Runtime.getRuntime().exec(StrCommand);
			
			errorGobbler = new CmdStreamGobbler(process.getErrorStream(), StrCommand, "创建VM ERR", strstrCpuuid, this);
			outputGobbler = new CmdStreamGobbler(process.getInputStream(), StrCommand, "创建VM STD", strstrCpuuid, this);

			
			if(errorGobbler!=null && outputGobbler!=null){
				errorGobbler.start();
				while (!errorGobbler.isReady()) {
					Thread.sleep(100);
				}
				outputGobbler.start();
				while (!outputGobbler.isReady()) {
					Thread.sleep(50);
				}

				while(super.strThrflg!=null){
					if((new Date().getTime())-lonBasrDt<=10000){
						Thread.sleep(1010);
						if(super.strThrflg.equals("ERR")){
							strInfo = strCname + strFname + " ERR 正常完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							errorGobbler = null;
							super.strThrflg = "";
						} 
						if(super.strThrflg.equals("STD")){
							strInfo = strCname + strFname + " SDT 正常完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							outputGobbler = null;
							super.strThrflg = "";
						}
						if(outputGobbler==null && errorGobbler==null){
							strInfo = strCname + strFname + " ER SDT 监听完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							super.strThrflg = null;
						}
					} else {
						if(outputGobbler!=null){
							strInfo = strCname + strFname + " SDT 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							outputGobbler = null;
						}
						if(errorGobbler!=null){
							strInfo = strCname + strFname + " ERR 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							errorGobbler = null;
						}
						break;
					}
				}
				super.strThrflg = null;
				strInfo = strCname + strFname + " 查看镜像 End----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Runlst, altRunc);
				for(int i=0; i<altRunc.size(); i++){
					System.out.println(altRunc.get(i));
				}
				System.out.println(hmpAll);
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}  finally{
			process = null;
		}

	}
	
	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp){
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
		lhpInfof.put(ProcessAttrs.strInfoSubtype_Info, "info");
		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
		altRuncp.add(lhpInfof);
		return altRuncp;
	}
	
	private void disOutputLog(String strFnamep, Exception exp){
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}

}
