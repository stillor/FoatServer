package custom.util;
import java.lang.String;
import java.util.Calendar;
import java.util.Vector;
import java.text.*;

public class Utility extends java.lang.Object{
	public static String getNowWeekSunday(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK,1);
		java.text.Format f = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return f.format(c.getTime());
	}  
	public static int DayOfWeek(String CID){
		String date = CID.substring(8,12) + "-" + CID.substring(12,14) + "-" + CID.substring(14,16);
		Calendar c = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c.get(Calendar.DAY_OF_WEEK);		
	}
	public static String generateCID(String ID,String DayOfWeek,String index){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK,Integer.parseInt(DayOfWeek));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = sdf.format(c.getTime());
		return ID+temp.substring(0,4)+temp.substring(5,7)+temp.substring(8,10)+index;
	}
	public static String getDayOfWeek(String DayOfWeek){
		switch(DayOfWeek){
			case "1": return "星期日";
			case "2": return "星期一";
			case "3": return "星期二";
			case "4": return "星期三";
			case "5": return "星期四";
			case "6": return "星期五";
			case "7": return "星期六";
		}
		return null;
	}
	//返回现在的节次信息
	public static int getNowClassIndexNumber(){
		Calendar nowTime = Calendar.getInstance();
		Calendar tempTime = Calendar.getInstance();
		tempTime.set(Calendar.HOUR_OF_DAY,8);
		if(nowTime.getTimeInMillis()<tempTime.getTimeInMillis())
		return -1;
		for(int i = 1;i<=4;i++){
			tempTime.set(Calendar.HOUR_OF_DAY,8+i);
			if(nowTime.getTimeInMillis()<tempTime.getTimeInMillis())
			return i;
		}
		tempTime.set(Calendar.HOUR_OF_DAY,14);
		if(nowTime.getTimeInMillis()<tempTime.getTimeInMillis())
		return -1;
		for(int i = 5;i<=8;i++){
			tempTime.set(Calendar.HOUR_OF_DAY,10+i);
			if(nowTime.getTimeInMillis()<tempTime.getTimeInMillis())
			return i;
		}
		tempTime.set(Calendar.HOUR_OF_DAY,19);
		if(nowTime.getTimeInMillis()<tempTime.getTimeInMillis())
		return -1;
		for(int i = 9;i<=11;i++){
			tempTime.set(Calendar.HOUR_OF_DAY,11+i);
			if(nowTime.getTimeInMillis()<tempTime.getTimeInMillis())
			return i;
		}
		return -1;
	}
	//判断是否课程正在进行中
	public static boolean isClassInTeaching(String fullTime){
	
		String Time = fullTime.substring(1,3);
		Calendar nowTime = Calendar.getInstance();
		String DOW = Integer.toString(nowTime.get(Calendar.DAY_OF_WEEK));
		
		
		if(!fullTime.substring(0,1).equals(DOW)) return false;
	
	
		int startTime = -1;
		if(Time.substring(0,1).equals("A"))startTime=10;
		else
		if(Time.substring(0,1).equals("B"))startTime=11;
		else
		startTime = Integer.parseInt(Time.substring(0,1));
		
		int endTime = -1;
		if(Time.substring(1,2).equals("A"))endTime=10;
		else
		if(Time.substring(1,2).equals("B"))endTime=11;
		else
		endTime = Integer.parseInt(Time.substring(1,2));
		
		if(startTime<=getNowClassIndexNumber() && endTime>=getNowClassIndexNumber())
		return true;
		
		return false;

	}
	//获得现在的时间
	public static long getNowTimeInMillis(){
		Calendar nowTime = Calendar.getInstance();
		return nowTime.getTimeInMillis();

	} 
	public static String vectorToString(Vector validVector){
		StringBuffer tempStr = new StringBuffer();
		for(int i = 0;i<validVector.size();i++){
			String temp = (String)validVector.elementAt(i);
			tempStr.append(temp);
			if(i != validVector.size()-1)
			tempStr.append(",");
		}
		return tempStr.toString();
	}
	public static Vector arrayToVector(Object[] array){
		Vector tempVector = new Vector();
		for(int i = 0;i<array.length;i++){
			tempVector.addElement(array[i]);
		}
		return tempVector;
	}
}