package orgs.cm.pMqp.pComms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pShellpro.StandardShellpro_Getimg;

public class ShellFilepro {

	private final String strCname = StandardShellpro_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);

	private String strFileroot;
	private String strFilename;
	private String strPostfix = null;
	private ArrayList<String> altShell;

	private ShellFilepro() {
	}

	public ShellFilepro(String strFilerootp, String strFilenamep, ArrayList<String> altShellp, String strPostfixp) {
		strFileroot = strFilerootp;
		strFilename = strFilenamep;
		strPostfix = strPostfixp;
		altShell = altShellp;
	}

	public boolean disCreateshell() {
		String strFname = " disCreateshell : ";
		boolean booRe = false;

		File file = null;
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			if (strFileroot != null && strFileroot.trim().length() > 0 
					&& altShell != null && altShell.size() > 0) {
//				file = new File(strFileroot +"/sssss/");
//				file.mkdirs();
//				file = null;
//				file=new File(strFileroot +"/sssss/" + "ssss.sh");
//				fw = new FileWriter(file);
				file = new File(strFileroot);
				file.mkdirs();
				file = null;
				if(strPostfix==null){
					file=new File(strFileroot + strFilename);
				} else {
					file=new File(strFileroot + strFilename + strPostfix);
				}
				fw = new FileWriter(file);
				writer = new BufferedWriter(fw);
				for (String str : altShell) {
					if(str.indexOf("}}}")>-1){
						String[] subLine = str.split("}}}");
						if(subLine!=null && subLine.length==2){
							writer.write(subLine[1].trim() + " ");
							writer.newLine();// 换行
						}
					}
				}
				writer.flush();
				booRe = true;
			}
		} catch (Exception ex) {
			 booRe = false;
		} finally {
			try {
				writer.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return booRe;
	}
}
