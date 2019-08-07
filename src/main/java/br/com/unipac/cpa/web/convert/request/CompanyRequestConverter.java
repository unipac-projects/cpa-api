package br.com.unipac.cpa.web.convert.request;

import java.util.Optional;

import br.com.unipac.cpa.exception.ResourceNotFoundException;

import br.com.unipac.cpa.model.domain.*;
import br.com.unipac.cpa.model.service.LocalService;
import br.com.unipac.cpa.web.dto.request.CompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.unipac.cpa.model.service.CompanyTypeService;

@Component
public class CompanyRequestConverter implements Converter<CompanyRequest, Company> {

	@Autowired
	private CompanyTypeService companyTypeService;

	@Autowired
	private LocalService localService;

	@Override
	public Company convert(CompanyRequest source) {
		Company company = Company.builder()
				.name(source.getName())
				.email(source.getEmail())
				.address(source.getAddress())
				.personType(PersonType.get(source.getPersonType()))
				.phone(source.getPhone())
				.mobile(source.getMobile())
				.documentRegion(DocumentRegion.get(source.getDocumentRegion()))
				.socialId(source.getSocialId())
				.nationality(source.getNationality())
				.build();

		if (source.getCompanyTypeId() != null && source.getCompanyTypeId().intValue() > 0) {
			Optional<CompanyType> companyType = companyTypeService.findById(source.getCompanyTypeId());
			if (companyType.isPresent()) {
				company.setCompanyType(companyType.get());
			} else {
				throw new ResourceNotFoundException("Company not found");
			}
		}

		if (source.getLocalId() != null && source.getLocalId().intValue() > 0) {
			Optional<Local> local = localService.findById(source.getLocalId());
			if (local.isPresent()) {
				company.setLocal(local.get());
			} else {
				throw new ResourceNotFoundException("Local not found");
			}
		}

		return company;
	}

}
