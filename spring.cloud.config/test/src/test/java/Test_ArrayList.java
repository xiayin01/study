import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ArrayList,CopyOnWriteArrayList并发测试
 *
 * @author xy
 */
public class Test_ArrayList {

    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        //List<Double> list = new ArrayList<>();//抛异常 java.lang.ArrayIndexOutOfBoundsException（线程不安全）
        List<Double> list = new CopyOnWriteArrayList<>();//线程安全，不会抛异常
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        es.execute(new AddThread(list));
        es.execute(new AddThread(list));
        es.shutdown();
    }

    static class AddThread implements Runnable {

        private List<Double> list;

        AddThread(List<Double> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                list.add(Math.random());
            }
        }
    }
}