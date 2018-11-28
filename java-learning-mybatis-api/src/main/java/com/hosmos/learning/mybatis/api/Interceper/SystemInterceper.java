package com.hosmos.learning.mybatis.api.Interceper;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SystemInterceper extends HandlerInterceptorAdapter {
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        Map map = request.getParameterMap();
        logger.info("Interceper URL:"+url + "  Parameters:"+ JSONObject.toJSONString(map));
        return super.preHandle(request, response, handler);
    }

}
