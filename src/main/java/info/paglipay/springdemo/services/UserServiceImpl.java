package info.paglipay.springdemo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import info.paglipay.springdemo.models.User;
import info.paglipay.springdemo.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	public User addUser(User user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
//		user.setPassword(user.getPassword());
		return userRepo.save(user);
	}

	@Override
	public Optional<User> getUserById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepo.findAll();
	}

	@Override
	public void updateUser(User change) {
		userRepo.save(change);
	}

	@Override
	public boolean deleteUser(int id) {
		try {
			userRepo.deleteById(id);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
