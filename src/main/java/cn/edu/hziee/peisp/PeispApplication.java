package cn.edu.hziee.peisp;

import cn.edu.hziee.peisp.netty.TCPServer;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
@RequiredArgsConstructor
@SpringBootApplication
@MapperScan("cn.edu.hziee.peisp.mapper")
public class PeispApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeispApplication.class, args);
    }
    private final TCPServer tcpServer;

    /**
     * This can not be implemented with lambda, because of the spring framework limitation
     * (https://github.com/spring-projects/spring-framework/issues/18681)
     *
     * @return
     */
    @SuppressWarnings({"Convert2Lambda", "java:S1604"})
    @Bean
    public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
        return new ApplicationListener<ApplicationReadyEvent>() {
            @Override
            public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                tcpServer.start();
            }
        };
    }

}
