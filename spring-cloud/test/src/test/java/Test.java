import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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
        String b1="a";
        String c=new String("a");
        System.out.println(a.equals(b1));
        System.out.println(a.equals(c));


        List<Long> aList=new ArrayList<>();
        aList.add(1L);
        aList.add(2L);
        List<Long> bList=new ArrayList<>();
        bList.add(2L);
        bList.add(3L);
        bList.add(4L);
        bList.removeIf(bb->aList.stream().anyMatch(aa->aa.equals(bb)));
        bList.forEach(System.out::println);


    }
}
