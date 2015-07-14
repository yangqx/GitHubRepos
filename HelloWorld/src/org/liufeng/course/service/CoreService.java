package org.liufeng.course.service;                                                            
                                                                                               
import java.util.Date;                                                                         
import java.util.HashMap;
import java.util.Map;                                                                          

import javax.servlet.http.HttpServletRequest;                                                  

import org.liufeng.course.message.resp.TextMessage;                                            
import org.liufeng.course.util.HttpRequestor;
import org.liufeng.course.util.MessageUtil;                                                    
                                                                                               
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
	            	String content = "";
	            	String city = "";
	            	if(msgType.contains("天气")){
	            		/* Post Request */
	                    Map dataMap = new HashMap();
	                    //dataMap.put("username", "Nick Huang");
	                    //dataMap.put("blog", "IT");
	                    if(msgType.contains("北京")){
	                    	city = "北京";
	                    }
	                    if(msgType.contains("郑州")){
	                    	city = "郑州";
	                    }
	                    if(msgType.contains("桐柏")){
	                    	city = "桐柏";
	                    }
	                    if(msgType.contains("天津")){
	                    	city = "天津";
	                    }
	                    String url = "http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=xml&ak=inCL9vgx62A6mumKu6dn2dgR";
	                    content = new HttpRequestor().doPost(url, dataMap);
	                    
	                    
	            	}
	            	
	            	if(true){
	            		//Content
	            		content = requestMap.get("Content");
	            		
	            	}
	                respContent = "您发送的是文本消息！/n" +content;                                        
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
