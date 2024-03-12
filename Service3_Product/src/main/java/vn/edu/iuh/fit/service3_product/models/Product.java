package vn.edu.iuh.fit.service3_product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_price")
    private int price;
    @OneToOne
    private User user;

    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
