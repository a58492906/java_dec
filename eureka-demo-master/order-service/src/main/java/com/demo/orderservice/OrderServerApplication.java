package com.demo.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.context.WebServerPortFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import java.io.File;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.demo.orderservice.repository")//扫描 @Repository 注解
public class OrderServerApplication implements ApplicationRunner, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServerApplication.class);

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        SpringApplication application = new SpringApplication(OrderServerApplication.class);
        application.run(args);


    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOG.info("web server is running");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("try to refresh context");
    }


}