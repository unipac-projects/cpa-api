package br.com.unipac.cpa.model.service;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Professor;

public interface ProfessorService extends CrudService<Professor, Long> {

	boolean sendInformation(Professor professor);

	Optional<Professor> findByName(String name);
}
