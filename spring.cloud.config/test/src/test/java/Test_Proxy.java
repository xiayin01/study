import com.xy.test.service.UserService;
import com.xy.test.service.impl.UserServiceImpl;
import com.xy.test.proxy.dynamic.DynamicProxyUserServiceInvocationHandler;
import com.xy.test.proxy.statics.StaticProxyUserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 代理（静态/动态）测试
 *
 * @author xy
 */
public class Test_Proxy {

    /**
     * 静态代理测试
     */
    @Test
    public void testStaticProxy() {
        UserService userService = new UserServiceImpl();
        UserService userServiceProxy = new StaticProxyUserServiceImpl(userService);
        userServiceProxy.sayHello("xy");
        userServiceProxy.sayByeBye("xy");
    }

    /**
     * 动态代理测试
     */
    @Test
    public void testDynamicProxy() {
        UserService userService = new UserServiceImpl();
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), new DynamicProxyUserServiceInvocationHandler(userService));
        userServiceProxy.sayHello("xy");
        userServiceProxy.sayByeBye("xy");
    }
}
