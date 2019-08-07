package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.web.dto.response.ProfessorResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseConverter implements Converter<Professor, ProfessorResponse> {

	@Override
	public ProfessorResponse convert(Professor source) {
		return ProfessorResponse.builder()
				.id(source.getId())
				.name(source.getName())
				.email(source.getEmail())
				.mobile(source.getMobile())
				.companyId(source.getCompany().getId())
				.build();

	}

}
