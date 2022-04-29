package com.demo.eureka;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-26 23:20
 */


import com.demo.eureka.service.SeckillLimitService;
import com.demo.eureka.util.ZuulParameterUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

//省略import
@Component
public class SeckillLimitFilter extends ZuulFilter {
    private static final Logger LOGGER = Logger.getLogger(SeckillLimitFilter.class.getName());

    /**
     * Redis限流服务实例
     */
    @Resource
    SeckillLimitService seckillLimitService;

    @Override
    public String filterType() {
        return "pre"; //路由之前
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        /**
         *如果请求已经被其他的过滤器终止，本过滤器就不做处理
         **/
        if (!ctx.sendZuulResponse()) {
            return false;
        }
        /**
         *对
         */
        LOGGER.info("*******request: " + request.getRequestURI());
        if (request.getRequestURI().startsWith
                ("/seckill-provider/api/seckill/do")) {
            return true;
        }
        return false;
    }

    /**
     * 过滤器
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Map<String, Object> param= ZuulParameterUtil.getRequestParams(ctx);
        LOGGER.info("*******run: "+param);
        String goodId = String.valueOf(param.get("goodId"));

        if (goodId != null) {
          String cacheKey = "seckill:" + goodId;
          Boolean limited = seckillLimitService.trySeckill(cacheKey,100L);
            if (!limited) {
                 /**
                 *降级处理
                 */
                String msg = "参与抢购的人太多，请稍后再试";
                fallback(ctx, msg);
                return null;
            }
            return null;
        } else {
            /**
             *参数输入错误
             */
            String msg = "参数错误";
            fallback(ctx, msg);
            return null;
        }
    }

    /**
     * 限流后的降级处理
     *
     * @param ctx
     * @param msg
     */
    private void fallback(RequestContext ctx, String msg) {
        ctx.setSendZuulResponse(false);
        try {
            ctx.getResponse().setContentType("text/html;charset=utf-8");
            ctx.getResponse().getWriter().write(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
