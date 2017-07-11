package orgs.cm.pMqp.pMqlst;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;

/**
 * MQ内cmd获取
 * cmd获取后采用Thraed快速抛出
 * */
public class ThrdReceiveMsg extends AbsThrdReceiveMsg{

	private final String strCname = ThrdReceiveMsg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private String strFlg = null;
	private String strServerTarget = null;
	
	public void setFlg(String strFlg) {
		this.strFlg = strFlg;
	}

	private String USERNAME = null; // ActiveMQConnection.DEFAULT_USER;// 默认连接用户名
	private String PASSWORD = null; //ActiveMQConnection.DEFAULT_PASSWORD;// 默认连接密码
	private String BROKEURL = null; //ActiveMQConnection.DEFAULT_BROKER_URL;// 默认连接地址

	private ConnectionFactory connectionFactory = null;// 连接工厂
	private Connection connection = null;// 连接
	private Session session = null;// 会话 接受或者发送消息的线程
	private Destination destination = null;// 消息的目的地
	private MessageConsumer messageConsumer = null;// 消息的消费者
	
	public void run() {
		String strFname = " run ; ";
		
		try {
//			if(disCreate()){
				disGetProperties();
				if(strServerTarget!=null && strServerTarget.trim().length()>0
						&& strFlg!=null && strFlg.trim().length()>0){
					// 实例化连接工厂
					connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
					// 通过连接工厂获取连接
					connection = connectionFactory.createConnection();
					// 启动连接
					connection.start();
					// 创建session
					session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					// 创建一个连接HelloWorld的消息队列
					destination = session.createQueue(strFlg+","+strServerTarget);
					// 创建消息消费者
					messageConsumer = session.createConsumer(destination);
					
					while (true) {
						disReveivePro(messageConsumer);
					}
				}
//			}
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
	}
	
	/**
	 * 监听有返回后的处理
	 * */
	private void disReveivePro(MessageConsumer messageConsumer){
		String strFname = " disReveivePro : ";
		
		try{
			TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
			if (textMessage != null) {
				String strMsg = textMessage.getText();
				System.out.println(strFname + "收到的消息:" + strMsg);
				logger.info(strFname + "收到的消息:" + strMsg);
				if(strServerTarget!=null && strServerTarget.trim().length()>0
						&& strFlg!=null && strMsg!=null ){
					ThrdReceiveDispense objThrdReceiveDispense = new ThrdReceiveDispense();
					objThrdReceiveDispense.setStrFlg(strFlg);
					objThrdReceiveDispense.setStrServerTarget(strServerTarget);
					objThrdReceiveDispense.setStrMsg(strMsg);
					objThrdReceiveDispense.start();
				}
			} 
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() 
						+ subSte[i].getMethodName() 
						+ ":" + subSte[i].getLineNumber() 
						+ "||" + lonFlg );
			}
		}
	}

	/**
	 * 获取Mq的参数
	 * */
	private void disGetProperties () throws Exception{
		String strFname = " disGetProperties ; ";
		
		try {
			Object objUname = null;
			Object objPass = null;
			Object objUrl = null;
			objUname = Propertiesmap.getPptAttr("activeMq.properties", "USERNAME");
			objPass = Propertiesmap.getPptAttr("activeMq.properties", "PASSWORD");
			objUrl = Propertiesmap.getPptAttr("activeMq.properties", "BROKEURL");
			this.USERNAME = objUname==null? null:objUname.toString();
			this.PASSWORD = objPass==null? null:objPass.toString();
			this.BROKEURL = objUrl==null? null:"failover:"+objUrl.toString();
//			if(PropetiesManage.pptMq!=null){
//				this.USERNAME = null; //PropetiesManage.pptMq.getProperty("USERNAME");// 默认连接用户名 //admin
//				this.PASSWORD = null; //PropetiesManage.pptMq.getProperty("PASSWORD");// 默认连接密码 //admin123456
//				this.BROKEURL = "failover:"+PropetiesManage.pptMq.getProperty("BROKEURL"); //"failover://tcp://"+PropetiesManage.pptMq.getProperty("BROKEURL");// 默认连接地址 //"http://10.167.210.212:8161/admin/"
//			}
//			this.USERNAME = ActiveMQConnection.DEFAULT_USER;// 默认连接用户名 //admin
//			this.PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// 默认连接密码 //admin123456
//			this.BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// 默认连接地址 //"http://10.167.210.212:8161/admin/"
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() 
						+ subSte[i].getMethodName() 
						+ ":" + subSte[i].getLineNumber() 
						+ "||" + lonFlg );
			}
			throw ex;
		}
	}
//	
//	private boolean disCreate(){
//		boolean booRe= false; 
//		if(strServerTarget==null || strServerTarget.trim().length()==0){
//			strServerTarget = PropetiesManage.getPptSysFlg();
//			booRe = true;
//		}
//		if(booRe && PropetiesManage.pptMq==null){
//			booRe = false;
//			String strPath = this.getClass().getResource("").getPath();
//			if(strPath!=null && strPath.trim().length()>0
//					&& strPath.indexOf("WEB-INF")>0){
//				int intOdx = strPath.indexOf("WEB-INF");
//				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/activeMq.properties";
//				GetProperties objPro = new GetProperties();
//				PropetiesManage.pptMq = objPro.disGet(strPath);
//				booRe = true;
//			}
//		}
//		if(booRe && PropetiesManage.pptMDB==null){
//			booRe = false;
//			String strPath = this.getClass().getResource("").getPath();
//			if(strPath!=null && strPath.trim().length()>0
//					&& strPath.indexOf("WEB-INF")>0){
//				int intOdx = strPath.indexOf("WEB-INF");
//				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/Mngdb.properties";
//				GetProperties objPro = new GetProperties();
//				PropetiesManage.pptMDB = objPro.disGet(strPath);
//				booRe = true;
//			}
//		}
//		if(booRe && PropetiesManage.pptMDB!=null){
//			booRe = false;
//			int intPool = Integer.parseInt(
//					PropetiesManage.pptMDB.getProperty("mng.objPoolnum"));
//			if(intPool>0){
//				intPool = new Double(intPool*0.8).intValue();
//				if(strFlg!=null && strFlg.trim().length()>0
//						&& strServerTarget!=null && strServerTarget.trim().length()>0){
//					ThreadPoolManage.chmThdPool_Msg.put(strFlg+strServerTarget, new ThreadPool01(500, ThrdSaveMsg.class));
//					ThreadRunManage.chmthdrMang_DataSave.put(strFlg+strServerTarget, new ThreadRun01(intPool));
//					booRe = true;
//				}
//			}
//		}
//		return booRe;
//	}
}