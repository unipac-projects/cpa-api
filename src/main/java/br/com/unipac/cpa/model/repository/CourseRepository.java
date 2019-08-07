package br.com.unipac.cpa.model.repository;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByName(String name);
}
