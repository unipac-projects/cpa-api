package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.web.dto.response.CompanyResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyResponseConverter implements Converter<Company, CompanyResponse> {

	@Override
	public CompanyResponse convert(Company source) {
		return CompanyResponse.builder()
				.id(source.getId())
				.name(source.getName())
				.email(source.getEmail())
				.address(source.getAddress())
				.personType(source.getPersonType())
				.phone(source.getPhone())
				.mobile(source.getMobile())
				.documentRegion(source.getDocumentRegion())
				.socialId(source.getSocialId())
				.nationality(source.getNationality())
				.companyTypeId(source.getCompanyType().getId())
				.localId(source.getLocal().getId())
				.build();
	}

}
