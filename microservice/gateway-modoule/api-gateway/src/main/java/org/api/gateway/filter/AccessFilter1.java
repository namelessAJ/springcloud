package org.api.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//@Component
public class AccessFilter1 extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AccessFilter1.class);

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

		Object accessToken = request.getParameter("accessToken");
		if (accessToken == null) {
			log.warn("accessToken为空!");
			// zuul直接过滤请求
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.getResponse().setContentType("text/html;charset=UTF-8");
			// 返回内容给客户端
			ctx.setResponseBody("非法请求!");
			return null;
		}
		log.info("合法请求!");

		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		// pre：请求路由前执行
		return FilterConstants.PRE_TYPE;
	}

}
