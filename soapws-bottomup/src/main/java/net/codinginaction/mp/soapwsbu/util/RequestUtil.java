package net.codinginaction.mp.soapwsbu.util;

import java.util.UUID;

import org.slf4j.MDC;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.soapwsbu.data.ExceptionData;
import net.codinginaction.mp.soapwsbu.enumerations.ErrorCode;
import net.codinginaction.mp.soapwsbu.helper.BuException;

@Slf4j
public class RequestUtil {
	public static final String REQUEST_ID_TITLE_PARAMETER_NAME = "requestId";
	public static final String REQUEST_ID_TITLE_PARAMETER_VALUE = "RequestId:";
	public static final String REQUEST_ID_PARAMETER_NAME = "id";
	public static final String DEFAULT_SOAP_FAULT_STRING = "Exception";

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

	public static BuException formBuException(Exception e) {
		BuException ex = null;
		if (e instanceof BuException) {
			ex = (BuException) e;
			ErrorCode code = ex.getFaultInfo().getCode();
			if (code == ErrorCode.NORMAL_FAULT) {
				log.debug("formBuException", e);
			} else {
				log.error("formBuException.", e);
			}
		} else {
			log.error("formBuException.", e);
			ex = new BuException(new ExceptionData(ErrorCode.UNKNOWN, e.getMessage()));
		}
		return ex;
	}
}
