<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf"/>
    <title>注册</title>
</head>
<body>
<div class="container">
    <form class="form-register" action="<%=request.getContextPath()%>/user/register" method="post">
        <h1 class="h3 mb-3 font-weight-normal">注册</h1>
       <div>
           <label for="inputName" class="sr-only">name</label>
           <input type="text" id="inputName" name="name" class="form-control"
                  placeholder="请输入用户名" required>
       </div>
        <div>
            <label for="inputPhoneNumber" class="sr-only">phoneNumber</label>
            <input type="number" id="inputPhoneNumber" name="phoneNumber" class="form-control"
                   placeholder="请输入电话号码" required>
        </div>
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
        <button class="btn btn-lg btn-primary btn-block" type="submit">register
        </button>
    </form>
</div>
</body>