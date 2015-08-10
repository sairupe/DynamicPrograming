package TimeUtil;

public class TimeUtil {

	public static long lastTimeStamp = 0;
	
	public static void logStartTime(){
		lastTimeStamp = System.currentTimeMillis();
	}
	
	public static void logEndTime(){
		System.out.println("=======ºÄÊ± " + (System.currentTimeMillis() - lastTimeStamp) + "");
	}
}
