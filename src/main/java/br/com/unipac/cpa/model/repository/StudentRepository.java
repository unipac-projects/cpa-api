package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	Optional<Student> findByName(String name);
}
