package com.example.bootboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 자동으로 시간 처리를 위한
public class BootboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(BootboardApplication.class, args);
	}

}
