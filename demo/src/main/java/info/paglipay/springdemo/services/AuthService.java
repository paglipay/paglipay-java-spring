package info.paglipay.springdemo.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// authorization method checks for the object exist in DB using the username
		info.paglipay.springdemo.models.User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user found of this username:" + username);
		}

		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
}
