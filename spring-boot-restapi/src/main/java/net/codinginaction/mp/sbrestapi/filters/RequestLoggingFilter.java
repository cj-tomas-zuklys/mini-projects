package net.codinginaction.mp.sbrestapi.filters;

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

import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.sbrestapi.helper.HttpServletResponseCopier;

@Slf4j
public class RequestLoggingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			logRequest(request);
		}
		if (log.isDebugEnabled()) {
			if (response.getCharacterEncoding() == null) {
				response.setCharacterEncoding("UTF-8");
			}
			HttpServletResponseCopier responseCopier = new HttpServletResponseCopier((HttpServletResponse) response);
			try {
				chain.doFilter(request, responseCopier);
				responseCopier.flushBuffer();
			} finally {
				logResponse(responseCopier, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private void logRequest(ServletRequest request) {
		HttpServletRequest r = (HttpServletRequest) request;
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append("Address: ").append(r.getRequestURI());
		String queryString = r.getQueryString();
		if (queryString != null) {
			sb.append('?').append(queryString);
		}
		sb.append(System.lineSeparator());
		if (StringUtils.hasLength(r.getCharacterEncoding())) {
			sb.append("Encoding: ").append(r.getCharacterEncoding()).append(System.lineSeparator());
		}
		if (StringUtils.hasLength(r.getContentType())) {
			sb.append("Content-type: ").append(r.getContentType()).append(System.lineSeparator());
		}
		printRequestHeaders(sb, request);
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				// int length = Math.min(buf.length, getMaxPayloadLength());
				String payload;
				try {
					// payload = new String(buf, 0, length,
					// wrapper.getCharacterEncoding());
					payload = new String(buf, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
				sb.append("Payload: ").append(payload);
			}
		}
		log.debug(sb.toString());
	}

	protected void printRequestHeaders(StringBuilder sb, ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> headerNames = req.getHeaderNames();
		boolean first = true;
		sb.append("Headers: ");
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append("{").append(headerName).append(": [");

			Enumeration<String> headers = req.getHeaders(headerName);
			first = true;
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				sb.append(headerValue);
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
			}

			sb.append("]}");
		}
		sb.append(System.lineSeparator());
	}

	private void logResponse(HttpServletResponseCopier responseCopier, ServletResponse response) throws UnsupportedEncodingException {
		HttpServletResponse r = (HttpServletResponse) response;
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		if (StringUtils.hasLength(r.getCharacterEncoding())) {
			sb.append("Encoding: ").append(r.getCharacterEncoding()).append(System.lineSeparator());
		}
		if (StringUtils.hasLength(r.getContentType())) {
			sb.append("Content-type: ").append(r.getContentType()).append(System.lineSeparator());
		}
		printResponseHeaders(sb, responseCopier);
		sb.append("Payload: ");
		byte[] copy = responseCopier.getCopy();
		sb.append(new String(copy, response.getCharacterEncoding()));
		log.debug(sb.toString());
	}

	protected void printResponseHeaders(StringBuilder sb, ServletResponse response) {
		HttpServletResponse resp = (HttpServletResponse) response;
		Collection<String> headerNames = resp.getHeaderNames();
		boolean first = true;
		sb.append("Headers: ");
		for (String headerName : headerNames) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append("{").append(headerName).append(": [");
			Collection<String> headers = resp.getHeaders(headerName);
			first = true;
			for (String headerValue : headers) {
				sb.append(headerValue);
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
			}
			sb.append("]}");
		}
		sb.append(System.lineSeparator());
	}

}
