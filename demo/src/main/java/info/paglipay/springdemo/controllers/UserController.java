package info.paglipay.springdemo.controllers;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import info.paglipay.springdemo.models.User;
import info.paglipay.springdemo.services.UserService;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping(
    value = "/register",
    consumes = "application/json",
    produces = "application/json"
  )
  public ResponseEntity<User> newUser(@RequestBody User u) {
    if (u.getUsername() != null) u.setUsername(
      u.getUsername().toLowerCase(Locale.ROOT)
    );
    try {
      User created = userService.addUser(u);
      if (created.getId() != 0) {
        return new ResponseEntity<>(created, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (DataIntegrityViolationException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}