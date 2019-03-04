//package juc;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Future;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.ImmutableList;
//import com.jd.uav.service.FlightCoordinateHistoryService;
//import com.jd.uav.web.socket.thread.FlightCoordinateTask;
//
//
//
//public class FlightCoordinateWebSocketHandler implements WebSocketHandler {
//
//	public static final Logger log = LoggerFactory.getLogger(FlightCoordinateWebSocketHandler.class);
//
//	@Resource
//	private FlightCoordinateHistoryService flightCoordinateHistoryService;
//
//	//存放允许快进的倍数,不在这个范围内,不修改任务的频率
//	private static List<String> allowSpeedList= ImmutableList.of("1","2", "4", "5", "8");
//
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//
//
//
//		// 如果不是1s的默认频率,直接放到线程池中按照固定频率执行线程. 连接断开以后,要cancel线程
//		if (!speed.equals("1.0")) {
//			ScheduledExecutorService pool=WebSocketSessionInfo.getPool();
//			Future future = pool.scheduleAtFixedRate(new FlightCoordinateTask(session,speed,flightCoordinateHistoryService), 0, (int)Double.valueOf(speed).doubleValue(),TimeUnit.MILLISECONDS);
//			WebSocketSessionInfo.findScheduleFuturesMap().put(session.getId(), future);
//		} else {// 如果是默认的1s,放到map中,用task任务来执行,放的是默认1s一次的session,如果发生变化,要从这个map中删除
//			WebSocketSessionInfo.findDefaultSessionMap().put(session.getId(), session);
//		}
//	}
//
//	@Override
//	// 接到页面上的消息,调整定时任务的发送频率
//	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//
//		//会传入speed和时间点两个参数
//		String speed=null;
//		String startDate=null;
//
//		Map map=JSON.parseObject(message.getPayload().toString(), Map.class);
//
//		if(map.containsKey("speed")){
//			speed=map.get("speed").toString();
//		}
//		if(map.containsKey("startDate")){
//			startDate=map.get("startDate").toString();
//		}
//		if(StringUtils.isNotBlank(startDate)){
//			handleThreadByStartDate(session, startDate);
//		}
//		if(StringUtils.isNotBlank(speed)){
//			handleThreadBySpeed(session, speed);
//		}
//
//	}
//
//	//页面传入了speed,要调整任务的频率
//	public void handleThreadBySpeed(WebSocketSession session,String speed){
//		String sessionId = session.getId();
//		//只有允许的倍数,才允许调整频率
//		if(allowSpeedList.contains(speed)){
//			//=================先删除原来的任务===========================
//			//删除原来1s的任务
//			WebSocketSessionInfo.findDefaultSessionMap().remove(sessionId);
//
//			//删除原来不是1s的任务
//			if (WebSocketSessionInfo.findScheduleFuturesMap().containsKey(sessionId)) {
//				//停止线程,从futureMap里面删除
//				Future future = WebSocketSessionInfo.getFuture(sessionId);
//				if (future != null){
//					future.cancel(true);
//					WebSocketSessionInfo.findScheduleFuturesMap().remove(sessionId);
//				}
//			}
//			//=================调整任务的频率是新增任务===========================
//			// 如果是默认的1s,放到map中,用task任务来执行,放的是默认1s一次的session
//			if (speed.equals("1")) {
//				WebSocketSessionInfo.findDefaultSessionMap().put(sessionId, session);
//			} else {// 如果不是1s的默认频率,直接放到线程池中按照固定频率执行线程.
//					double f = 1 / Double.valueOf(speed);
//					BigDecimal b = new BigDecimal(f);
//					double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()*1000;
//					speed=String.valueOf(f1);
//					Future future = WebSocketSessionInfo.getPool().scheduleAtFixedRate(new FlightCoordinateTask(session,speed,flightCoordinateHistoryService), 0, (int)Double.valueOf(speed).doubleValue(),TimeUnit.MILLISECONDS);
//					WebSocketSessionInfo.findScheduleFuturesMap().put(session.getId(), future);
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		double f = 1 / Double.valueOf("4");
//		BigDecimal b = new BigDecimal(f);
//		double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()*1000;
//		String speed=String.valueOf(f1);
//
//		System.out.println((int)Double.valueOf(speed).doubleValue());
//
//	}
//
//	//页面传入了positin,要从某个时间点开始
//	public void handleThreadByStartDate(WebSocketSession session,String startDate){
//		//设置开始的的时间点
//		session.getAttributes().put("startDate", startDate);
//		//从时间点开始,接着从0开始累积
//		session.getAttributes().put("position", 0);
//	}
//
//	// 发生异常
//	@Override
//	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//		// 这里发生异常,就直接就关闭连接了....
//		if (session.isOpen()) {
//			session.close();
//		}
//		log.debug("websocket chat connection closed......");
//
//		WebSocketSessionInfo.findDefaultSessionMap().remove(session.getId());
//
//		//停止线程,从futureMap里面删除
//		Future future = WebSocketSessionInfo.getFuture(session.getId());
//		if (future != null){
//			future.cancel(true);
//			WebSocketSessionInfo.findScheduleFuturesMap().remove(session.getId());
//		}
//	}
//
//	// 关闭连接
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//		try {
//			//停止线程,从futureMap里面删除
//			WebSocketSessionInfo.findDefaultSessionMap().remove(session.getId());
//			Future future = WebSocketSessionInfo.getFuture(session.getId());
//			if (future != null){
//				future.cancel(true);
//				WebSocketSessionInfo.findScheduleFuturesMap().remove(session.getId());
//			}
//			log.info("afterConnectionClosed" + closeStatus.getReason());
//		} catch (Exception e) {
//			log.error("afterConnectionClosed error:", e);
//		}
//	}
//
//	@Override
//	public boolean supportsPartialMessages() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
////	// 发送给单个socket客户端
////	public void sendMessageToUser(String uid, String message) throws Exception {
////		WebSocketSession session = sessionMap.get(uid);
////		if (session != null && session.isOpen()) {
////			JSONObject json = JSONObject.parseObject(message);
////			List<String> list = flightCoordinateHistoryService.getFlightCoordinateHistory(json.getString("sysId"),
////					json.getString("flyLogId"), json.getDoubleValue("seconds"), json.getLongValue("num"),
////					TimeUnit.SECONDS);
////			session.sendMessage(new TextMessage(JSONObject.toJSONString(list)));
////		}
////	}
//
//}
