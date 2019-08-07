package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.web.dto.request.UserRequest;
import br.com.unipac.cpa.model.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResquestConverter implements Converter<UserRequest, User> {

	@Override
	public User convert(UserRequest source) {
		User target = new User();

		target.setEmail(source.getEmail());
		target.setPassword(source.getPassword());

		return target;
	}

}
