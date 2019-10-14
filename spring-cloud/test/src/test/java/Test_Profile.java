import com.xy.test.config.ProfileConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试
 *
 * @author xy
 */
public class Test_Profile {

    @org.junit.Test
    public void test() {
        //可以通过有参构造器+VM(-Dspring.profiles.active=test)指定运行test环境
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProfileConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }
    }

    @Test
    public void test1() {
        //1.创建applicationContext对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2.设置环境
        applicationContext.getEnvironment().setActiveProfiles("test", "dev");
        //3.注册主配置类
        applicationContext.register(ProfileConfig.class);
        //启动刷新容器
        applicationContext.refresh();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }
    }

}
