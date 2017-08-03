package net.codinginaction.mp.soapwsbu.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.soapwsbu.helper.RequestWrapper;
import net.codinginaction.mp.soapwsbu.helper.ResponseWrapper;

@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
        if (response.getCharacterEncoding() == null) {
            response.setCharacterEncoding("UTF-8");
        }

        if (log.isDebugEnabled()) {
            logRequest(requestWrapper);
        }

        ResponseWrapper responsetWrapper = new ResponseWrapper((HttpServletResponse) response);
        try {
            chain.doFilter(requestWrapper, responsetWrapper);
            responsetWrapper.flushBuffer();
        } finally {
            if (log.isDebugEnabled()) {
                logResponse(responsetWrapper);
            }
        }
    }

    private void logResponse(ResponseWrapper response) throws UnsupportedEncodingException {
        if (response == null) {
            log.debug("logRequest. request is null");
            return;
        }
        String body = new String(response.getCopy(), response.getCharacterEncoding());
        Collection<String> headerNames = response.getHeaderNames();
        StringBuilder responsePayload = new StringBuilder();
        for (String headerName : headerNames) {
            responsePayload.append(headerName).append(" : ").append(response.getHeader(headerName)).append("\n");
        }
        responsePayload.append("\n").append(body);
        log.debug("logResponse. ResponseCode: {}, Payload: \n{}", response.getStatus(), responsePayload);
    }

    private void logRequest(RequestWrapper request) {
        if (request == null) {
            log.debug("logRequest. request is null");
            return;
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder requestPayload = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            requestPayload.append(headerName).append(" : ").append(request.getHeader(headerName)).append("\n");
        }
        requestPayload.append("\n").append(request.getBody());
        log.debug("logRequest. RemoteAddress: {}, Method: {}, Request URI: {}. Body: \n{}", request.getRemoteAddr(), request.getMethod(), request.getRequestURI(), requestPayload);
    }

    @Override
    public void destroy() {
        log.debug("destroy");
    }

}
