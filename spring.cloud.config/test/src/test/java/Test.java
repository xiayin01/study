import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 *
 * @author xy
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(ThreadLocalRandom.current().nextInt());

        String a="a";
        String b="a";
        String c=new String("a");
        System.out.println(a==b);
        System.out.println(a==c);

    }
}
