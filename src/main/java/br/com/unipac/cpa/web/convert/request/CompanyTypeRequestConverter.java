package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.web.dto.request.CompanyTypeRequest;
import br.com.unipac.cpa.model.domain.CompanyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyTypeRequestConverter implements Converter<CompanyTypeRequest, CompanyType> {

	@Override
	public CompanyType convert(CompanyTypeRequest source) {
		return CompanyType.builder().name(source.getName()).build();
	}

}
