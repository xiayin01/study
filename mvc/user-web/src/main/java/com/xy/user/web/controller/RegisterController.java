package com.xy.user.web.controller;

import com.xy.common.mvc.ResponseResult;
import com.xy.common.mvc.controller.RestController;
import com.xy.common.mvc.enums.ResponseStatus;
import com.xy.user.web.context.ComponentContext;
import com.xy.user.web.domain.User;
import com.xy.user.web.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("user")
public class RegisterController implements RestController {

    private final UserServiceImpl userService;

    public RegisterController() {
        this.userService = ComponentContext.getInstance().getComponent("bean/UserService");
    }

    @Override
    @POST
    @Path("register")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        User u = new User();
        u.setPhoneNumber(phoneNumber);
        u.setEmail(email);
        u.setName(userName);
        u.setPassword(password);

        ResponseResult result = userService.register(u);

        if (result.getStatus() == ResponseStatus.SUCCESS.getStatus()) {
            request.setAttribute("name", userName);
            return "hello.jsp";
        } else {
            request.setAttribute("error", result.getMsg());
            return "error.jsp";
        }
    }

    @Override
    @GET
    @Path("query")
    public String query(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new UserServiceImpl().queryUserByEmailAndPassword(email, password);
        request.setAttribute("name", user.getName());
        return "hello.jsp";
    }

    @Override
    @POST
    @Path("login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new UserServiceImpl().queryUserByEmailAndPassword(email, password);
        if (StringUtils.isNotBlank(user.getName())) {
            request.setAttribute("name", user.getName());
            return "hello.jsp";
        } else {
            request.setAttribute("error", "无对应注册用户");
            return "error.jsp";
        }
    }
}
