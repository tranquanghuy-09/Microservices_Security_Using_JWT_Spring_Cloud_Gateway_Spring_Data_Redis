package vn.edu.iuh.fit.auth_jwt.controller;

import vn.edu.iuh.fit.auth_jwt.authen.UserPrincipal;
import vn.edu.iuh.fit.auth_jwt.entity.Token;
import vn.edu.iuh.fit.auth_jwt.entity.User;
import vn.edu.iuh.fit.auth_jwt.service.TokenService;
import vn.edu.iuh.fit.auth_jwt.service.UserService;
import vn.edu.iuh.fit.auth_jwt.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpSession session){

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        session.setAttribute("token", token.getToken());

        return ResponseEntity.ok(token.getToken());
    }


    @GetMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello");
    }




//    Object principal = SecurityContextHolder
//            .getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//        UserPrincipal userPrincipal = (UserPrincipal) principal;
//    }

}
