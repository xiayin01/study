import com.xy.test.config.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author xy
 */
public class Test_Ext {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        //发布事件
        applicationContext.publishEvent(new ApplicationEvent("测试发布事件") {
        });
        applicationContext.close();
    }
}
