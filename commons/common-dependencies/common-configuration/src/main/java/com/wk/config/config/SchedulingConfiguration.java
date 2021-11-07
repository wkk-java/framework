package com.wk.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {
    //第一种线程池定义方式，可代替CachedThreadPool、FixedThreadPool、SingleThreadExecutor这三种
    // Spring线程池
    @Lazy //线程池懒加载
    @Bean(name="threadPoolTaskExecutor",destroyMethod="shutdown") //name为线程池名称，destroyMethod="shutdown"在spring bean回收后释放资源
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        //封装的是原生的ThreadPoolExecutor类型线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数（获取硬件）：线程池创建时候初始化的线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        System.out.println(corePoolSize);
        executor.setCorePoolSize(corePoolSize);
        //最大线程数+5：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(corePoolSize * 2 + 1);
        //缓冲队列500：用来缓冲执行任务的队列
        executor.setQueueCapacity(500);
        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("WKK-Async-");
        executor.initialize();
        return executor;
    }

    //第二种线程池定义方式，使用的是WorkStealingPool
    //java8 抢占式线程池
    @Lazy
    @Bean(name="workStealingPool",destroyMethod="shutdown")
    public ExecutorService workStealingPool(){
        ExecutorService executorService = Executors.newWorkStealingPool();
        return executorService;
    }

    //第三种线程池定义方式，为周期任务线程池
    @Lazy
    @Bean(name="scheduledThreadPool",destroyMethod="shutdown")
    public ExecutorService scheduledThreadPool() {
        return Executors.newScheduledThreadPool(3);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduledThreadPool());
    }
}