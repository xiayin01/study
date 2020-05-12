public class Test_LoadOrder {

    public static void main(String[] args) {
        User user = new User(1);
    }
}

class Parent {
    private int id;
    private String name;

    static {
        System.out.println("父类静态块加载");
    }

    Parent() {
        System.out.println("父类无参构造函数加载");
    }

    Parent(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("父类有参构造函数加载");
    }
}

class User extends Parent {
    private int age;

    static {
        System.out.println("子类静态块加载");
    }

    public User() {
        System.out.println("子类无参构造函数加载");
    }

    User(int age) {
        this.age = age;
        System.out.println("子类有参函数加载1");
    }

    public User(int id, String name, int age) {
        super(id, name);
        this.age = age;
        System.out.println("子类有参函数加载2");
    }
}