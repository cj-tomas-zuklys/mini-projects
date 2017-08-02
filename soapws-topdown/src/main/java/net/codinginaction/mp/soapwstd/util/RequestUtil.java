package net.codinginaction.mp.soapwstd.util;

import java.util.UUID;

import org.slf4j.MDC;

public class RequestUtil {
	public static final String REQUEST_ID_TITLE_PARAMETER_NAME = "requestId";
	public static final String REQUEST_ID_TITLE_PARAMETER_VALUE = "RequestId:";
	public static final String REQUEST_ID_PARAMETER_NAME = "id";

	public static void addRequestLogging() {
		addRequestLogging(null);
	}

	public static void addRequestLogging(String reqId) {
		MDC.put(REQUEST_ID_TITLE_PARAMETER_NAME, REQUEST_ID_TITLE_PARAMETER_VALUE);
		if (reqId == null) {
			MDC.put(REQUEST_ID_PARAMETER_NAME, getUniqueRequestId());
		} else {
			MDC.put(REQUEST_ID_PARAMETER_NAME, reqId);
		}
	}

	public static void removeRequestLogging() {
		MDC.remove(REQUEST_ID_TITLE_PARAMETER_NAME);
		MDC.remove(REQUEST_ID_PARAMETER_NAME);
	}

	public static String getUniqueRequestId() {
		return UUID.randomUUID().toString();
	}

	public static String getCurrentRequestId() {
		return MDC.get(REQUEST_ID_PARAMETER_NAME);
	}
}
