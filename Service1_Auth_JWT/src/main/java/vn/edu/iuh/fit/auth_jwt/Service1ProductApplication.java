package vn.edu.iuh.fit.auth_jwt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
public class Service1ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(Service1ProductApplication.class, args);
	}

}
