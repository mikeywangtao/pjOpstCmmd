package orgs.cm.pMqp.pProlst;


import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程执行队列，单线程执行
 * 可以并行多个
 * */
public class ThrdRunOne {
	
	private int intVltBaseNum ;
	private Vector<ExecutorService> vlt;
	private int intIdx = 0;
	
	private ThrdRunOne(){}
	/**
	 * @param num 并行队列数
	 * */
	public ThrdRunOne(int num){
		String strFname = " ThreadManage01 ; ";
		try{
			if(num>=1){
				intVltBaseNum = num;
				vlt = new Vector<ExecutorService>(intVltBaseNum);
				disInitVlt();
			} else {
				new Exception("队列数必须大于等于1！");
			}
		} catch(Exception ex) {
			System.out.println(strFname + ex);
		}
	}
	
	private synchronized void disInitVlt() {
		String strFname = " disInitVlt : ";
		try {
			for(int i=0; i<intVltBaseNum; i++){
				vlt.add(Executors.newSingleThreadExecutor());
			}
		} catch(Exception ex) {
			System.out.println(strFname + ex);
		}
	}
	public synchronized void putThread2Mlt(Runnable rnbThd){
		String strFname = " putThread2Mlt : ";
		try{
			if(vlt!=null){
				synchronized (vlt) {
					if(intIdx>-1 && intIdx<intVltBaseNum){
						vlt.get(intIdx).submit(rnbThd);
						intIdx = intIdx + 1;
//						System.out.println(strFname + " <100 " + intIdx);
					} else if(intIdx>=intVltBaseNum) {
						intIdx=0;
						vlt.get(intIdx).submit(rnbThd);
						intIdx = intIdx + 1;
//						System.out.println(strFname + " <0 " + intIdx);
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(strFname + ex);
		}
	}
	public synchronized void shutdownVltThd(){
		String strFname = " shutdownVltThd : ";
		try{
			if(vlt!=null){
				synchronized (vlt) {
					for(int i=0; i<vlt.size(); i++){
						vlt.get(i).shutdown();
					}
				}
			}
		} catch(Exception ex) {
			System.out.println(strFname + ex);
		}
	}
}
