package org.liufeng.course.service;                                                            
                                                                                               
import java.util.Date;                                                                         
import java.util.HashMap;
import java.util.Map;                                                                          

import javax.servlet.http.HttpServletRequest;                                                  

import org.liufeng.course.message.resp.TextMessage;                                            
import org.liufeng.course.message.resp.Weather;
import org.liufeng.course.util.HttpRequestor;
import org.liufeng.course.util.MessageUtil;                                                    

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
                                                                                               
/**                                                                                            
	 * 核心服务类                                                                                
	 *                                                                                           
	 * @author liufeng                                                                           
	 * @date 2013-05-20                                                                          
	 */                                                                                          
	public class CoreService {                                                          
	    /**                                                                                      
	     * 处理微信发来的请求                                                                    
	     *                                                                                       
	     * @param request                                                                        
	     * @return                                                                               
	     */                                                                                      
	    public static String processRequest(HttpServletRequest request) {                        
	        String respMessage = null;                                                           
	        try {                                                                                
	            // 默认返回的文本消息内容                                                        
	            String respContent = "请求处理异常，请稍候尝试！";                               
	                                                                                             
	            // xml请求解析                                                                   
	            Map<String, String> requestMap = MessageUtil.parseXml(request);                  
	                                                                                             
	            // 发送方帐号（open_id）                                                         
	            String fromUserName = requestMap.get("FromUserName");                            
	            // 公众帐号                                                                      
	            String toUserName = requestMap.get("ToUserName");                                
	            // 消息类型                                                                      
	            String msgType = requestMap.get("MsgType");                                      
	                                                                                             
	            // 回复文本消息                                                                  
	            TextMessage textMessage = new TextMessage();                                     
	            textMessage.setToUserName(fromUserName);                                         
	            textMessage.setFromUserName(toUserName);                                         
	            textMessage.setCreateTime(new Date().getTime());                                 
	            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);                      
	            textMessage.setFuncFlag(0);                                                      
	                                                                                             
	            // 文本消息                                                                      
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { 
	            	String content = requestMap.get("Content");
	            	String city = "";
	            	StringBuilder builder  =  new StringBuilder();
	            	if(content.contains("天气")){
	            		/* Post Request */
	                    Map dataMap = new HashMap();
	                    //dataMap.put("username", "Nick Huang");
	                    //dataMap.put("blog", "IT");
	                    city = content.replaceAll("天气", "");
	                    String url = "http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=inCL9vgx62A6mumKu6dn2dgR";
	                    content = new HttpRequestor().doPost(url, dataMap);
	                    Gson gson = new Gson();
	                    Weather weather = gson.fromJson(content,
	            				new TypeToken<Weather>() {
	            				}.getType());
	                    builder.append("城市 ： " + city)
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(0).getDate())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(0).getTemperature())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(0).getWeather())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(0).getWind())
	                    
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(1).getDate())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(1).getTemperature())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(1).getWeather())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(1).getWind())
	                    
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(2).getDate())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(2).getTemperature())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(2).getWeather())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(2).getWind())
	                    
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(3).getDate())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(3).getTemperature())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(3).getWeather())
	                    .append("\n").append(weather.getResults().get(0).getWeather_data().get(3).getWind());
	                    
//	                    StringBuffer buffer = new StringBuffer();
//	                    buffer.append("您好,我是小q,请回复数字选择服务:").append("\n\n");
//	                    buffer.append("1 天气预报");
//	                    buffer.append("2 公交查询").append("\n");
//	                    buffer.append("3 周边搜索").append("\n");
//	                    buffer.append("4 歌曲点播").append("\n");
//	                    buffer.append("5 经典游戏").append("\n");
//	                    buffer.append("6 美女电台").append("\n");
//	                    buffer.append("7 人脸识别").append("\n");
//	                    buffer.append("8 聊天唠嗑").append("\n\n");
//	                    buffer.append("回复\"?\"显示此帮助菜单");
//	                    return buffer.toString();
	            	}
	            	
	            	if(true){
	            		//Content
	            		//content = requestMap.get("Content");
	            	}
	               //respContent = "您发送的是文本消息！" +add;                                        
	            	respContent =  builder.toString();
	            }                                                                                
	            // 图片消息                                                                      
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
	            	String content = "";
	            	if(true){
	            		//Content
	            		content = requestMap.get("Content");
	            	}
	                respContent = "您发送的是图片消息！";                                        
	            }                                                                                
	            // 地理位置消息                                                                  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {                
	                respContent = "您发送的是地理位置消息！";                                    
	            }                                                                                
	            // 链接消息                                                                      
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {                    
	                respContent = "您发送的是链接消息！";                                        
	            }                                                                                
	            // 音频消息                                                                      
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {                   
	                respContent = "您发送的是音频消息！";                                        
	            }                                                                                
	            // 事件推送                                                                      
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {                   
	                // 事件类型                                                                  
	                String eventType = requestMap.get("Event");                                  
	                // 订阅                                                                      
	                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {                    
	                    respContent = "谢谢您的关注！";                                          
	                }                                                                            
	                // 取消订阅                                                                  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {             
	                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息       
	                }                                                                            
	                // 自定义菜单点击事件                                                        
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {                   
	                    // TODO 自定义菜单权没有开放，暂不处理该类消息                           
	                }                                                                            
	            }                                                                                
	                                                                                             
	            textMessage.setContent(respContent);                                             
	            respMessage = MessageUtil.textMessageToXml(textMessage);                         
	        } catch (Exception e) {                                                              
	            e.printStackTrace();                                                             
	        }                                                                                    
	                                                                                             
	        return respMessage;                                                                  
	    }                                                                                        
	}                                                                                            
