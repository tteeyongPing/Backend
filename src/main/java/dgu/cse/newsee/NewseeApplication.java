package dgu.cse.newsee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "dgu.cse.newsee.domain.entity")
public class NewseeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewseeApplication.class, args);
	}

}
