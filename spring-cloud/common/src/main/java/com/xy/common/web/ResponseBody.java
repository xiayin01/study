package com.xy.common.web;

/**
 * 返回体
 *
 * @author xy
 */
public class ResponseBody<T> {

    /**
     * 唯一序列号
     */
    private static final long serialVersionUID = -9219350599142537307L;
    /**
     * 成功
     */
    public static final String SUCCESS_MSG = "成功";
    /**
     * 失败
     */
    public static final String FAIL_MSG = "失败";
    /**
     * 错误
     */
    public static final String ERROR_MSG = "错误";
    /**
     * 异常
     */
    public static final String EXCEPTION_MSG = "异常";
    /**
     * 回滚
     */
    public static final String FALLBACK_MSG = "回滚";

    /**
     * 业务状态码
     */
    private int status;
    /**
     * 简短信息
     */
    private String msg;
    /**
     * 内容主体
     */
    private T data;
    /**
     * 错误类
     */
    private Error error;

    public ResponseBody() {

    }

    public ResponseBody(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBody(int status, Error error) {
        this.status = status;
        this.error = error;
    }

    /**
     * 获取回滚响应主体
     *
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> isFallback(T data) {
        return isFallback(FALLBACK_MSG, data);
    }

    public static <T> ResponseBody<T> isFallback(Error error) {
        return new ResponseBody<>(Status.FALLBACK.getCode(), error);
    }

    /**
     * 获取回滚响应主体
     *
     * @param msg  回滚信息
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> isFallback(String msg, T data) {
        return new ResponseBody<>(Status.FALLBACK.getCode(), msg, data);
    }

    /**
     * 获取成功响应主体
     *
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> success(T data) {
        return success(SUCCESS_MSG, data);
    }

    /**
     * 获取成功响应主体
     *
     * @param msg  成功消息
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> success(String msg, T data) {
        return new ResponseBody<>(Status.SUCCESS.getCode(), msg, data);
    }

    /**
     * 获取失败响应主体
     *
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> fail(T data) {
        return fail(FAIL_MSG, data);
    }

    /**
     * 获取失败响应主体
     *
     * @param msg  失败消息
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> fail(String msg, T data) {
        return new ResponseBody<>(Status.FAIL.getCode(), msg, data);
    }

    /**
     * 获取错误响应主体
     *
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> error(T data) {
        return error(ERROR_MSG, data);
    }

    /**
     * 获取失败响应主体
     *
     * @param msg  错误消息
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> error(String msg, T data) {
        return new ResponseBody<>(Status.ERROR.getCode(), msg, data);
    }

    /**
     * 获取异常响应主体
     *
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> exception(T data) {
        return exception(EXCEPTION_MSG, data);
    }

    /**
     * 获取失败响应主体
     *
     * @param msg  异常消息
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T> ResponseBody<T> exception(String msg, T data) {
        return new ResponseBody<>(Status.EXCEPTION.getCode(), msg, data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * http响应业务状态码
     *
     * @author xy
     */
    public enum Status {
        SUCCESS(100), FAIL(101), ERROR(102), EXCEPTION(103), FALLBACK(104);
        private final int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
