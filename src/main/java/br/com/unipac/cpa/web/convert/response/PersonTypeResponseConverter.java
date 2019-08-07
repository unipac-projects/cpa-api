package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.web.dto.response.PersonTypeResponse;
import br.com.unipac.cpa.model.domain.PersonType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonTypeResponseConverter implements Converter<PersonType, PersonTypeResponse> {

	@Override
	public PersonTypeResponse convert(PersonType source) {
		return PersonTypeResponse.builder()
				.id(Long.valueOf(source.getOrdinal()))
				.name(source.getName())
				.code(source.name())
				.build();
	}

}
