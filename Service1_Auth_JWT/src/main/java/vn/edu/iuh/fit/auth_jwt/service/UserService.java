package vn.edu.iuh.fit.auth_jwt.service;

import vn.edu.iuh.fit.auth_jwt.authen.UserPrincipal;
import vn.edu.iuh.fit.auth_jwt.entity.User;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
}
