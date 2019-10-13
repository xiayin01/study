import com.xy.test.config.DataSourceConfig;
import com.xy.test.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 事务测试
 *
 * @author xy
 */
public class Test_Tx {

    /**
     * 必须在DataSourceConfig类添加包扫描 {@link ComponentScan ("com.xy.test.service")}
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insert();
        applicationContext.close();
    }
}
