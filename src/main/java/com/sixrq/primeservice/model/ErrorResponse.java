package com.sixrq.primeservice.model;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.context.request.RequestAttributes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {
    private final Integer status;
    private final String message;
    private final String stackTrace;

    public ErrorResponse(RequestAttributes attributes) {
        this.status = (Integer) attributes.getAttribute("javax.servlet.error.status_code", 0);

        Exception exception = (Exception) attributes.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION", 0);
        this.message = exception != null ? ExceptionUtils.getRootCauseMessage(exception) : "";
        this.stackTrace = exception != null ? ExceptionUtils.getStackTrace(exception) : "";
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
