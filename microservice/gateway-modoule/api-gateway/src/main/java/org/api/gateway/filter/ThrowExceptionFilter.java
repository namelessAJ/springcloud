package org.api.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ThrowExceptionFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);

	@Override
	public Object run() throws ZuulException {
		log.info("这是一个前置的 runtimeExceptionfilter");
		RequestContext ctx = RequestContext.getCurrentContext();

		try {
			doSomething();
		} catch (Exception e) {
			ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			ctx.set("error.exception", e);
		}

		return null;
	}

	private void doSomething() {
		throw new RuntimeException("exception");
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
