package org.sharding.inline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sharding.inline.dao.mybatis")
public class ApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

}