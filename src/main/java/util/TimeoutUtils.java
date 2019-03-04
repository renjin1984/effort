package test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson.JSON;


public class TimeoutUtils {
	public static long toSeconds(long timeout, TimeUnit unit) {
		return roundUpIfNecessary(timeout, unit.toSeconds(timeout));
	}

	public static long toMillis(long timeout, TimeUnit unit) {
		return roundUpIfNecessary(timeout, unit.toMillis(timeout));
	}

	private static long roundUpIfNecessary(long timeout, long convertedTimeout) {
		if ((timeout > 0L) && (convertedTimeout == 0L)) {
			return 1L;
		}
		return convertedTimeout;
	}
	
	
	public static void main(String[] args) {
//		final long seconds = TimeoutUtils.toSeconds(1, TimeUnit.DAYS);
//		System.out.println(seconds);
		
			
		long time = 1493815309131l;
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		System.out.println(dateStr);
		
		
		String json="{startDate:1493867421529}";
		Map map=JSON.parseObject(json, Map.class);
		System.out.println(map.containsKey("startDate"));
		
	//	long s=(long)map.get("startDate");
		  DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//��ʽ������  
	 //       System.out.println("--------" + decimalFormat.format(s));
	}
}
