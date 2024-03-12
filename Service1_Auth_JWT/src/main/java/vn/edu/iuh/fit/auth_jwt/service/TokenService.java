package vn.edu.iuh.fit.auth_jwt.service;

import vn.edu.iuh.fit.auth_jwt.entity.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);

}
