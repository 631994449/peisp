package cn.edu.hziee.peisp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.hziee.peisp.mapper")
public class PeispApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeispApplication.class, args);
    }

}
