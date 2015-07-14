package org.liufeng.course.util;

import java.util.HashMap;
import java.util.Map;

public class Call {

    public static void main(String[] args) throws Exception {
        
        /* Post Request */
        Map dataMap = new HashMap();
        //dataMap.put("username", "Nick Huang");
        //dataMap.put("blog", "IT");
        System.out.println(new HttpRequestor().doPost("http://api.map.baidu.com/telematics/v3/weather?location=北京&output=xml&ak=inCL9vgx62A6mumKu6dn2dgR", dataMap));
        
        /* Get Request */
        System.out.println(new HttpRequestor().doGet("http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=inCL9vgx62A6mumKu6dn2dgR"));
    }

}
