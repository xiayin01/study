package com.xy.user.web.jmx.mxbean;

import com.xy.user.web.domain.Student;

public class StudentJmxMXBeanImpl implements StudentJmxMXBean {

    private final Student student;

    public StudentJmxMXBeanImpl(Student student) {
        this.student = student;
    }

    @Override
    public Student getStudent() {
        return this.student;
    }

    @Override
    public Student addStudent() {
        return this.student;
    }

    @Override
    public void test() {
        System.out.println("-----MBeanOperationInfo-----");
    }
}
