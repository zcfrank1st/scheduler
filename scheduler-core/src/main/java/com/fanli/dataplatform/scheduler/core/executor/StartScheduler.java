package com.fanli.dataplatform.scheduler.core.executor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by adima on 14-3-31.
 */
public class StartScheduler {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-applicationcontext-resource.xml");
        ResetExecutor reset = (ResetExecutor) context.getBean("reset");
        reset.execute();
        new ClassPathXmlApplicationContext("classpath:spring-applicationcontext-resource.xml",
                "classpath:spring-applicationcontext-bean.xml"
        );
    }
}
