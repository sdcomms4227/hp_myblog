package com.example.hp_myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class HpMyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpMyblogApplication.class, args);
	}

}
