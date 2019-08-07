package br.com.unipac.cpa.web.convert.request;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.unipac.cpa.model.repository.CompanyRepository;

@Component
public class CourseRequestConverter
		implements Converter<CourseRequest, Course> {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Course convert(CourseRequest source) {
		Course course = Course.builder()
				.name(source.getName())
				.build();

		if (source.getCompanyId() != null && source.getCompanyId().intValue() > 0) {
			Optional<Company> company = companyRepository.findById(source.getCompanyId());
			if (company.isPresent()) {
				course.setCompany(company.get());
			} else {
				throw new ResourceNotFoundException("Course not found");
			}
		}

		return course;
	}

}
