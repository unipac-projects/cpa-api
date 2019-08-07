package br.com.unipac.cpa.web.support;

import java.util.ArrayList;
import java.util.List;

import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRepository;
import br.com.unipac.cpa.web.dto.response.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserSupport {

	private static final Logger logger = LogManager.getLogger(UserSupport.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversionService userConvert;

	public UserResponse findUserByUsername(@PathVariable("username") String username) {
		User user = userRepository.findByEmail(username);
		UserResponse result = userConvert.convert(user, UserResponse.class);
		logger.info("User: " + user.toString());
		return result;
	}

	public List<UserResponse> list() {
		List<UserResponse> dtos = new ArrayList<>();
		userRepository.findAll().forEach(user -> {
			UserResponse result = userConvert.convert(user, UserResponse.class);
			dtos.add(result);
		});
		return dtos;
	}
}
