package com.xy.common.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 页面控制，页面渲染
 */
public interface RestController extends Controller {

    /**
     * 执行
     *
     * @param request  请求
     * @param response 响应
     * @return 结果
     * @throws Throwable 异常
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable;

    String query(HttpServletRequest request, HttpServletResponse response) throws Throwable;

    String login(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
