package vn.edu.iuh.fit.service3_product.repositories;

import vn.edu.iuh.fit.service3_product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
