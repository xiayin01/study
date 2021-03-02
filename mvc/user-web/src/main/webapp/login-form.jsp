<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf"/>
    <title>登录</title>
</head>
<body>
<div class="container">
    <form class="form-signin" action="<%=request.getContextPath()%>/user/login" method="post">
        <h1 class="h3 mb-3 font-weight-normal">登录</h1>
        <div>
            <label for="inputEmail" class="sr-only">请输出电子邮件</label>
            <input type="email" id="inputEmail" name="email" class="form-control"
                   placeholder="请输入电子邮件" required autofocus>
        </div>
        <div>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password" class="form-control"
                   placeholder="请输入密码" required>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in
        </button>
    </form>
    <a id="register" href="register-form.jsp">register</a>
</div>
</body>