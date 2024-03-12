package vn.edu.iuh.fit.service3_product;

import vn.edu.iuh.fit.service3_product.models.Product;
import vn.edu.iuh.fit.service3_product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Service1ProductApplication {
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Service1ProductApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				for (int i=1; i<=10; i++){
					productRepository.save(new Product(i, "Product"+i, 123));
				}
			}
		};
	}



}
