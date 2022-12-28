package com.sparta.blogpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing//없으면 entity패키지의 Timestamped 클래스의 .data.annotation.LastModifiedDate같은 날짜 자동생성이 안된다.
public class BolgPostApplication {
    public static void main(String[] args) {
        SpringApplication.run(BolgPostApplication.class, args);
    }
}
