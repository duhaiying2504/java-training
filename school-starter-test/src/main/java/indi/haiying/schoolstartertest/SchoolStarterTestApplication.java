package indi.haiying.schoolstartertest;

import indi.haiying.springbootschoolstarter.School;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolStarterTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolStarterTestApplication.class, args);
		System.out.println(BeanUtil.getBean(School.class));
	}

}
