package com.admin.bootstudy.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class AwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsApplication.class, args);
	}

}
