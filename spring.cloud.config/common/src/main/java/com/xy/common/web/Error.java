package com.xy.common.web;

/**
 * 错误信息
 *
 * @author xy
 */
public class Error extends RuntimeException {

    private static final long serialVersionUID = 32143414134514211L;

    public Error(String message, Throwable throwable) {
        super(message, throwable);
    }
}
