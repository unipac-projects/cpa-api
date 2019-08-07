package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Student;
import br.com.unipac.cpa.web.dto.response.StudentResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentResponseConverter implements Converter<Student, StudentResponse> {

	@Override
	public StudentResponse convert(Student source) {
		return StudentResponse.builder()
				.id(source.getId())
				.name(source.getName())
				.email(source.getEmail())
				.mobile(source.getMobile())
				.register(source.getRegister())
				.companyId(source.getCompany().getId())
				.build();

	}

}
