package com.xy.common.mvc;

import com.xy.common.mvc.controller.Controller;
import com.xy.common.mvc.controller.RestController;
import com.xy.common.mvc.header.HandlerMethodInfo;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.substringAfter;

public class ControllerServlet extends HttpServlet {

    /**
     * 请求路径和 Controller 的映射关系缓存
     */
    private Map<String, Controller> controllersMapping = new HashMap<>();
    /**
     * 请求路径和 {@link HandlerMethodInfo} 映射关系缓存
     */
    private Map<String, HandlerMethodInfo> handleMethodInfoMapping = new HashMap<>();

    public void init(ServletConfig config) {
        initHandleMethods();
    }

    private void initHandleMethods() {
        for (Controller controller : ServiceLoader.load(Controller.class)) {
            Class<? extends Controller> controllerClass = controller.getClass();
            //找到类上的路径
            Path pathFromClass = controllerClass.getAnnotation(Path.class);
            String requestMainPath = pathFromClass.value();
            if (!requestMainPath.startsWith("/")) {
                requestMainPath = "/" + requestMainPath;
            }
            Method[] publicMethods = controllerClass.getMethods();
            for (Method publicMethod : publicMethods) {
                Set<String> supportedHttpMethods = findSupportedHttpMethods(publicMethod);
                //找到方法上的路径
                Path pathFromMethod = publicMethod.getAnnotation(Path.class);
                if (null != pathFromMethod) {
                    String requestBodyPath = "";
                    if (!pathFromMethod.value().startsWith("/")) {
                        requestBodyPath += "/" + pathFromMethod.value();
                    } else {
                        requestBodyPath += pathFromMethod.value();
                    }
                    handleMethodInfoMapping.put(requestMainPath + requestBodyPath,
                            new HandlerMethodInfo(requestMainPath + requestBodyPath, publicMethod, supportedHttpMethods));
                    controllersMapping.put(requestMainPath + requestBodyPath, controller);
                }
            }
        }
    }

    /**
     * 获取处理方法中标注的 HTTP方法集合
     *
     * @param method 处理方法
     * @return 集合
     */
    private Set<String> findSupportedHttpMethods(Method method) {
        Set<String> supportedHttpMethods = new LinkedHashSet<>();
        for (Annotation annotation : method.getAnnotations()) {
            HttpMethod httpMethod = annotation.annotationType().getAnnotation(HttpMethod.class);
            if (null != httpMethod) {
                supportedHttpMethods.add(httpMethod.value());
            }
        }
        if (supportedHttpMethods.isEmpty()) {
            supportedHttpMethods.addAll(asList(HttpMethod.GET, HttpMethod.POST,
                    HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.HEAD, HttpMethod.OPTIONS));
        }
        return supportedHttpMethods;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String servletContextPath = req.getContextPath();
        // 映射路径（子路径）
        String requestMappingPath = substringAfter(requestURI,
                StringUtils.replace(servletContextPath, "//", "/"));
        // 映射到 Controller
        Controller controller = controllersMapping.get(requestMappingPath);
        if (controller != null) {
            HandlerMethodInfo handlerMethodInfo = handleMethodInfoMapping.get(requestMappingPath);
            try {
                if (null != handlerMethodInfo) {
                    String httpMethod = req.getMethod();
                    if (!handlerMethodInfo.getSupportedHttpMethods().contains(httpMethod)) {
                        // HTTP 方法不支持
                        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        return;
                    }
                    if (controller instanceof RestController) {
                        String viewPath = (String) handlerMethodInfo.getHandlerMethod().invoke(controller, req, resp);
                        // 页面请求 forward
                        // request -> RequestDispatcher forward
                        // RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
                        // ServletContext -> RequestDispatcher forward
                        // ServletContext -> RequestDispatcher 必须以 "/" 开头
                        ServletContext servletContext = req.getServletContext();
                        if (!viewPath.startsWith("/")) {
                            viewPath = "/" + viewPath;
                        }
                        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(viewPath);
                        requestDispatcher.forward(req, resp);
                    }
                }
            } catch (Throwable throwable) {
                if (throwable.getCause() instanceof IOException) {
                    throw (IOException) throwable.getCause();
                } else {
                    throw new ServletException(throwable.getCause());
                }
            }
        }
    }
}
