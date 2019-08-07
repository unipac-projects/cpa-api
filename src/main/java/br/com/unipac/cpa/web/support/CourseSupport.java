package br.com.unipac.cpa.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.service.CourseService;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CourseSupport {

	private static final Logger log = LogManager.getLogger(CourseSupport.class);

	@Autowired
	private CourseService service;

	@Autowired
	private ConversionService conversion;
	
	public CourseResponse convertToFindById(Long id) {
		Optional<Course> course = service.findById(id);
		CourseResponse founded = conversion.convert(course.get(), CourseResponse.class);
		log.info("Course: " + founded.toString());
		return founded;
	}

	public CourseResponse convertToFindByName(String name) {
		Optional<Course> course = service.findByName(name);
		CourseResponse founded = conversion.convert(course.get(), CourseResponse.class);
		log.info("Course: " + founded.toString());
		return founded;
	}

	public List<CourseResponse> list() {
		List<CourseResponse> courses = new ArrayList<>();
		service.listAll().forEach(course -> {
			CourseResponse saved = conversion.convert(course, CourseResponse.class);
			courses.add(saved);
		});
		return courses;
	}

	public CourseResponse convertToCreate(CourseRequest courseRequest) {
		Course course = conversion.convert(courseRequest, Course.class);
		Course result = service.save(course);
		return conversion.convert(result, CourseResponse.class);
	}

	public CourseResponse convertToChange(Long id, CourseRequest courseRequest) {
		Course course = conversion.convert(courseRequest, Course.class);
		Course result = service.edit(id, course);
		return conversion.convert(result, CourseResponse.class);
	}

	public boolean remove(Long id) {
		return service.remove(id);	
	}
}
