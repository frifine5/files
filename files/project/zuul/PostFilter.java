package com.gomain.makeseal.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;


@Component
public class PostFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;// 在请求被处理之后，会进入该过滤器
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = String.valueOf(ctx.get("requestURI"));
        // 根据url来匹配排除不需要过滤器拦截的请求
        if (requestURI.contains("test")) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        int responseCode = ctx.getResponseStatusCode();
        //---------------------------^v^---------------------------------
        if ((responseCode >= 300 || responseCode < 200) && responseCode != 401) {
            String message = "访问错误，请检查请求路径是否正确！";
            logger.error("接口访问错误：" + message);
            ctx.setResponseBody(message);
        }
        try {
            // 获取返回值内容，加以处理
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
//            String returnStr = "测试结果：filter拦截后过来的结果";
            logger.info("return body >>>\t" + body);
            //处理逻辑..

            // 内容重新写入

            ctx.setResponseBody(body);
        } catch (Exception e) {
            logger.error("获取返回结果信息异常", e);
        }
        return null;
    }
}
