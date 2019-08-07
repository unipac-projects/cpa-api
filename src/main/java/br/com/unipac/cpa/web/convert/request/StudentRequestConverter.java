package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.Student;
import br.com.unipac.cpa.model.repository.CompanyRepository;
import br.com.unipac.cpa.web.dto.request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentRequestConverter implements Converter<StudentRequest, Student> {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Student convert(StudentRequest source) {
		Student client = Student.builder()
				.name(source.getName())
				.email(source.getEmail())
				.mobile(source.getMobile())
				.register(source.getRegister())
				.build();
		
		if (source.getCompanyId() != null && source.getCompanyId().intValue() > 0) {
			Optional<Company> company = companyRepository.findById(source.getCompanyId());
			if (company.isPresent()) {
				client.setCompany(company.get());
			} else {
				throw new ResourceNotFoundException("Id náo encontrado");
			}
		}

		return client;
	}

}
