package info.paglipay.springdemo.services;

import java.util.List;
import java.util.Optional;

import info.paglipay.springdemo.models.User;

public interface UserService {
  public User addUser(User user);
  public Optional<User> getUserById(int id);
  public List<User> getAllUsers();
  public void updateUser(User change);
  public boolean deleteUser(int id);

  User getUserByUsername(String username);
}