package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseResponseConverter implements Converter<Course, CourseResponse> {

	@Override
	public CourseResponse convert(Course source) {
		return CourseResponse.builder()
				.id(source.getId())
				.name(source.getName())
				.companyId(source.getCompany().getId())
				.build();
	}

}
