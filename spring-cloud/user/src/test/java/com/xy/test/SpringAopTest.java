package com.xy.test;

import com.xy.user.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

@ComponentScans(value = {@ComponentScan("com.xy.user.aop"), @ComponentScan("com.xy.user.service")})
@EnableAspectJAutoProxy
public class SpringAopTest {

    public static void main(String[] args) throws IOException {
        int i = 2, a;
        a = ++i;
        System.out.println(a);
        System.out.println(i);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAopTest.class);
        UserService userService = context.getBean(UserService.class);
        //UserServiceImpl userService=(UserServiceImpl)context.getBean("userServiceImpl");
        System.out.println(userService.getUser(1L));

        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "$Proxy", new Class[]{UserService.class});
        FileOutputStream outputStream = new FileOutputStream("D://Proxy.class");
        outputStream.write(proxyClassFile);
        outputStream.flush();


    }
}
