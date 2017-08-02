package net.codinginaction.mp.soapwstd.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.soapwstd.util.RequestUtil;

@Slf4j
public class RequestTrackingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            RequestUtil.addRequestLogging();
            chain.doFilter(request, response);
        } finally {
        	RequestUtil.removeRequestLogging();
        }
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }

}
