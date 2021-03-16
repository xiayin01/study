package com.xy.user.web.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UserWebLoggingConfiguration {

    public UserWebLoggingConfiguration() throws Exception {
        System.out.println("UserWebLoggingConfiguration");
        // 通过代码的方式调整日志级别
        Logger logger = Logger.getLogger("com.xy");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setEncoding("UTF-8");
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) throws IOException {

        Logger logger = Logger.getLogger("com.xy");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("META-INF/logging.properties")) {
            LogManager logManager = LogManager.getLogManager();
            // 读取日志配置
            logManager.readConfiguration(inputStream);
        }

        logger.info("Hello,World");
        logger.warning("2021");
    }
}
