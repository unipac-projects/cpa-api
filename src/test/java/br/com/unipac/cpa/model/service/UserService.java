package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.web.dto.request.UserRequest;
import br.com.unipac.cpa.web.dto.response.UserResponse;
import br.com.unipac.cpa.model.domain.User;

public interface UserService {
	User createUser(UserResponse dto);

	String validatePasswordResetToken(User user, String token);

	String changeUserPassword(User user, String token);

	boolean register(UserRequest userRequest);

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
}
