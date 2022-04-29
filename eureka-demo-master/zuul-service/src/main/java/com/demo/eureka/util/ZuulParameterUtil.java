package com.demo.eureka.util;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 16:38
 */
import com.alibaba.fastjson.JSON;
import com.demo.eureka.Constant;
import com.google.common.collect.Maps;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ZuulParameterUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ZuulParameterUtil.class);

    /**
     * 获取zuul拦击请求参数
     * @param ctx
     */
    public static Map<String, Object> getRequestParams(RequestContext ctx) {
        String method = ctx.getRequest().getMethod();
        //判断是POST请求还是GET请求（不同请求获取参数方式不同）
        LOGGER.info("method :"+method);
        LinkedHashMap<String,Object> param = new LinkedHashMap<>();
        try {
            if ("GET".equals(method)) {
                Map<String,String[]> map = ctx.getRequest().getParameterMap();
                if (!(Objects.isNull(map) || map.isEmpty())) {
                    param = Maps.newLinkedHashMap();
                    for (Map.Entry<String, String[]> entry : map.entrySet()) {
                        param.put(entry.getKey(), entry.getValue()[0]);
                    }
                }
            } else if ("POST".equals(method)) {
                String paramsType = ctx.getRequest().getHeader(Constant.ContentType);
                LOGGER.info("paramsType :"+paramsType);
                if(paramsType.equals(Constant.APPLICATION_JSON)){
                    InputStream inputStream = ctx.getRequest().getInputStream();
                    String body = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
                    Map map = JSON.parseObject(body);
                    for(Object key : map.keySet()){
                        param.put(String.valueOf(key),map.get(key));
                    }
                }else{
                    StandardMultipartHttpServletRequest mul = new StandardMultipartHttpServletRequest(ctx.getRequest());
                    HttpServletRequestWrapper sere  = (HttpServletRequestWrapper)mul.getRequest();
                    StandardMultipartHttpServletRequest fill = (StandardMultipartHttpServletRequest) sere.getRequest();;
                    RequestFacade facsde = (RequestFacade)fill.getRequest();
                    Map<String,String[]> parameterMap = facsde.getParameterMap();
                    for(String key : parameterMap.keySet()){
                        param.put(key,parameterMap.get(key)[0]);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("***************ZuulParameterUtil->getRequestParams throw Exception：{}***************", e);
        }
        return param;
    }
}
