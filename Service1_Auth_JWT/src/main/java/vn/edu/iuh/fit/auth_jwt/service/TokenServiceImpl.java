package vn.edu.iuh.fit.auth_jwt.service;

import vn.edu.iuh.fit.auth_jwt.entity.Token;
import vn.edu.iuh.fit.auth_jwt.repository.TokenRepository;
import vn.edu.iuh.fit.auth_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}

