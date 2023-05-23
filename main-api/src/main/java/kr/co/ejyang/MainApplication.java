package kr.co.ejyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MainApplication {

    public static void main(String[] args) {
        // 프로퍼티 설정 ( main-api, module-file )
//        System.setProperty("spring.config.name", "application, application-file");

        // run
        SpringApplication.run(MainApplication.class, args);
    }
}

