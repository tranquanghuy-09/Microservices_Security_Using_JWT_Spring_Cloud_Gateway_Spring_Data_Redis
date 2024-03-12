package vn.edu.iuh.fit.auth_jwt.repository;

import vn.edu.iuh.fit.auth_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
