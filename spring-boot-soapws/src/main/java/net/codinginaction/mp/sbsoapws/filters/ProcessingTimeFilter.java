package net.codinginaction.mp.sbsoapws.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessingTimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("info");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		if (log.isDebugEnabled()) {
			String queryString = httprequest.getQueryString();
			if (queryString != null) {
				queryString = httprequest.getRequestURI() + "?" + queryString;
			} else {
				queryString = httprequest.getRequestURI();
			}
			log.debug("doFilter. Request: {} took {}ms", queryString, System.currentTimeMillis() - start);
		}
	}

	@Override
	public void destroy() {
		log.info("destroy");
	}

}
