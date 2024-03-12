package vn.edu.iuh.fit.auth_jwt.repository;

import vn.edu.iuh.fit.auth_jwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
