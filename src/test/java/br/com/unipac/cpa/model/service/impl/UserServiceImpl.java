package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.domain.Role;
import br.com.unipac.cpa.model.repository.UserRepository;
import br.com.unipac.cpa.model.service.PasswordCrypto;
import br.com.unipac.cpa.model.service.UserService;
import br.com.unipac.cpa.web.dto.request.UserRequest;
import br.com.unipac.cpa.web.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordCrypto passwordCrypto;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(UserResponse dto) {
		User user = new User();
		Set<Role> roles = new HashSet<>();

		user.setEmail(dto.getEmail());
		user.setPassword(passwordCrypto.encrypt(dto.getPassword()));

		// create a new user with basic user privileges
		//oles.add(new Role(Roles.USER.toString(), user));
		//roles.add(new Role(Roles.ADMIN.toString(), user));

		// user.setRoles(roles);

		return user;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByEmailAndPassword(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByEmail(username);
	}

	@Override
	public String validatePasswordResetToken(User user, String token) {
		return null;
	}

	@Override
	public String changeUserPassword(User user, String token) {
		return null;
	}

	@Override
	public boolean register(UserRequest userRequest) {
		return false;
	}

    public List<User> listAll() {
		return userRepository.findAll();
    }

	public Optional<User> findById(long id) {
		return Optional.ofNullable(userRepository.getOne(id));
	}
}
