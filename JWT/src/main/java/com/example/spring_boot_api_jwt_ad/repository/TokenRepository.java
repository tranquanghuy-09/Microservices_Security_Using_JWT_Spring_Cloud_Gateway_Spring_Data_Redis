package com.example.spring_boot_api_jwt_ad.repository;

import com.example.spring_boot_api_jwt_ad.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository
        extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
