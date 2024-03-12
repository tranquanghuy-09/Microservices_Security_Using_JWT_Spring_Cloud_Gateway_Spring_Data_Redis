package vn.edu.iuh.fit.service3_product.services;

import vn.edu.iuh.fit.service3_product.models.Product;
import vn.edu.iuh.fit.service3_product.models.User;
import vn.edu.iuh.fit.service3_product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(long id){
        User user = restTemplate.getForObject("http://localhost:8081/api/v1/users/"+id, User.class);
        Product product = productRepository.findById(id).get();
        product.setUser(user);
        return product;
    }

}
