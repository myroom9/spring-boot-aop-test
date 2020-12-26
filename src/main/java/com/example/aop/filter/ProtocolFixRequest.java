package com.example.aop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ProtocolFixRequest extends HttpServletRequestWrapper {
    private String fixedProtocol;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public ProtocolFixRequest(HttpServletRequest request, String protocol) {
        super(request);
        fixedProtocol = protocol;
    }

    @Override
    public String getProtocol() {
        return "https";
    }

    @Override
    public String getScheme() {
        return "https";
    }
}
