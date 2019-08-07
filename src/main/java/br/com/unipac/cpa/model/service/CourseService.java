package br.com.unipac.cpa.model.service;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Course;

public interface CourseService extends CrudService<Course, Long>{
	Optional<Course> findByName(String name);

}
