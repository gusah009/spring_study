package servletTest;

import jdbc.dao.RoleDao;
import jdbc.dto.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

//		int roleId = 501;
//		String description = "CTO";
//
//		Role role = new Role(roleId, description);
//
//		RoleDao dao = new RoleDao();
//		Role selectRole = dao.getRole(102);
//		int insertCount = dao.addRole(role);
//		List<Role> roles = dao.getRoles();
//
//		for (Role value : roles) {
//			System.out.println(value.toString());
//		}
//		System.out.println(insertCount);
		System.out.println("RESTART");
		return String.format("hello!!!~~! %s!", name);
	}

}