package com.xy.test.entity;

/**
 * 用户
 *
 * @author xy
 */
public class User {

    /**
     * id
     */
    private long id;
    /**
     * 名称
     */
    private String name;

    public User() {
        System.out.println("user...无参构造器");
    }

    public void init() {
        System.out.println("user...init...");
    }

    public void destroy() {
        System.out.println("user...destroy...");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
