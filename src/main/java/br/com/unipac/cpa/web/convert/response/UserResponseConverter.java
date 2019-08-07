package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.web.dto.response.UserResponse;
import br.com.unipac.cpa.model.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter implements Converter<User, UserResponse> {

	@Override
	public UserResponse convert(User source) {
		return UserResponse.builder()
				.id(source.getId())
				.email(source.getEmail())
				.password(source.getPassword())
				.build();
	}

}
