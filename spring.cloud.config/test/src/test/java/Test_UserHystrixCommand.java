import com.netflix.hystrix.HystrixCommand;
import com.xy.test.entity.User;
import com.xy.test.hystrix.CountService;
import com.xy.test.hystrix.CountServiceImpl;
import com.xy.test.hystrix.UserHystrixCommand;
import com.xy.test.hystrix.UserHystrixCommandSemaphore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * hystrix测试
 *
 * @author xy
 */

public class Test_UserHystrixCommand {

    @Test
    public void test() throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountServiceImpl.class);
        CountService countService = applicationContext.getBean(CountService.class);
        User u = new User();
        u.setName("测试");
        u.setId(0);
        //Future<User> future = new UserHystrixCommand(u).queue();
        int count = new UserHystrixCommand(countService).execute();
        System.out.println("Test_UserHystrixCommand....线程隔离-线程池执行结果:" + count);
        User user = new UserHystrixCommandSemaphore(u).execute();
        System.out.println("Test_UserHystrixCommand...线程隔离-信号量执行结果:" + user);
        int i = 1;
        for (; i < 15; i++) {
            HystrixCommand<Integer> hystrixCommand = new UserHystrixCommand(countService);
            Integer result = hystrixCommand.execute();
            System.out.println("Test_UserHystrixCommand....线程隔离-线程池执行结果循环测试:" + result);
        }
        //等待6s，使得熔断器进入半打开状态
        Thread.sleep(6000);
        for (; i < 20; i++) {
            HystrixCommand<Integer> hystrixCommand = new UserHystrixCommand(countService);
            Integer result = hystrixCommand.execute();
            System.out.println("Test_UserHystrixCommand....线程隔离-线程池执行结果循环测试:" + result);
        }
    }
}
