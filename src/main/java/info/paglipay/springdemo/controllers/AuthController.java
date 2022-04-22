package info.paglipay.springdemo.controllers;


import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import info.paglipay.springdemo.models.UserLogin;
import info.paglipay.springdemo.services.AuthService;
import info.paglipay.springdemo.services.UserService;
import info.paglipay.springdemo.utilities.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Autowired
  UserService userService;

  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<info.paglipay.springdemo.models.User> login(
    @RequestBody UserLogin request
  ) {
    try {
      Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getUsername(),
          request.getPassword()
        )
      );

      User user = (User) authenticate.getPrincipal();
      info.paglipay.springdemo.models.User retUser = userService.getUserByUsername(
        user.getUsername().toLowerCase(Locale.ROOT)
//    		  "HughTheMann"
      );
      retUser.setPassword(null);

      return ResponseEntity
        .ok()
        .header(
          HttpHeaders.AUTHORIZATION,
          jwtTokenUtil.generateAccessToken(user)
        )
        .body(retUser);
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
