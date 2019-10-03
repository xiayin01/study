import com.xy.test.config.DataSourceConfig;
import com.xy.test.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author xy
 */
public class Test_Tx {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insert();
        applicationContext.close();
    }
}
