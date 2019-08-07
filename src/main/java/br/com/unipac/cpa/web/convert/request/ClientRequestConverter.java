package br.com.unipac.cpa.web.convert.request;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.web.dto.request.ProfessorRequest;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.unipac.cpa.model.repository.CompanyRepository;

@Component
public class ClientRequestConverter implements Converter<ProfessorRequest, Professor> {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Professor convert(ProfessorRequest source) {
		Professor professor = Professor.builder()
				.name(source.getName())
				.email(source.getEmail())
				.mobile(source.getMobile())
				.build();
		
		if (source.getCompanyId() != null && source.getCompanyId().intValue() > 0) {
			Optional<Company> company = companyRepository.findById(source.getCompanyId());
			if (company.isPresent()) {
				professor.setCompany(company.get());
			} else {
				throw new ResourceNotFoundException("Id náo encontrado");
			}
		}

		return professor;
	}

}
