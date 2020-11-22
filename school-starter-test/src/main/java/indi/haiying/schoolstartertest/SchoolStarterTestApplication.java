package indi.haiying.schoolstartertest;

import indi.haiying.springbootschoolstarter.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SchoolStarterTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolStarterTestApplication.class, args);
		log.warn("school: {}", BeanUtil.getBean(School.class));
	}

}
