package com.xy.user.web.jmx.mxbeaan;

import com.xy.user.web.domain.Student;
import com.xy.user.web.jmx.mxbean.StudentJmxMXBeanImpl;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class StudentJmxMXBeanTest {

    public static void main(String[] args) throws Exception {
        // 获取平台 MXBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 StudentJmxMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("com.xy.user.web.jmx.mxbean:type=StudentJmx");
        // 创建 Student 实例
        Student student = new Student(1L, "test", 12);
        mBeanServer.registerMBean(createStudentMXBean(student), objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(student);
        }
    }

    private static Object createStudentMXBean(Student student) {
        return new StudentJmxMXBeanImpl(student);
    }
}
