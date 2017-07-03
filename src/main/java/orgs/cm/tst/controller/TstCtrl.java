package orgs.cm.tst.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import orgs.cm.tst.service.TstServ;

@Controller
@RequestMapping(value="/tstCtrl")
public class TstCtrl {
	
	private String strCname = TstCtrl.class.getName();
	
	@Autowired
	private TstServ tstServ;
	
	/**
	 * 注册信息查询
	 */
	@RequestMapping(value = "/disTstCtrlPro")
	public String disTstCtrl_Pro(HttpSession session,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		String strFname = " disTstCtrl_Pro : ";
		String strRe = "";
		try {
			System.out.println(strCname + strFname);
			tstServ.disTstServ_Pro(null);
			strRe = "myTest00";
		} catch(Exception ex) {
			
		}
		return strRe;
	}
}
