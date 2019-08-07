package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Period;

import java.util.Optional;

public interface DisciplineService extends CrudService<Discipline, Long> {

	boolean sendInformation(Discipline discipline);

	Optional<Discipline> findByName(String name);
}
