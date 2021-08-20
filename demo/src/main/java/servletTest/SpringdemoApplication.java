package servletTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringdemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringdemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdemoApplication.class, args);
	}

	@GetMapping("/demo")
	public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
		return String.format("hello %s!", name);
	}

}