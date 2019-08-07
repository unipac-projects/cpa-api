package br.com.unipac.cpa.model.repository;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Professor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {
	Optional<Professor> findByName(String name);
}
