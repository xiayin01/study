package com.xy.user.web.domain;

import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * 学生
 */
public class Student implements Serializable {

    /**
     * 学号
     */
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 实现组合类型CompositeData
     *
     * @param id   id
     * @param name name
     * @param age  age
     */
    @ConstructorProperties({"id", "name", "age"})
    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
