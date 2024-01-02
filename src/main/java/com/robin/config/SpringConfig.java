package com.robin.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.robin.service")
@PropertySource("classpath:jdbc.properties") // 若是识别不了路径，则在前面加上classpath属性
@Import({MybatisConfig.class,JdbcConfig.class})
@EnableTransactionManagement    // 声明开启事务
public class SpringConfig {
}
