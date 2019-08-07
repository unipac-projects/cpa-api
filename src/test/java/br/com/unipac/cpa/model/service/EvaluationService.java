package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.model.domain.Evaluation;

import java.util.Optional;

public interface EvaluationService extends CrudService<Evaluation, Long> {

	boolean sendInformation(Evaluation evaluation);

	Optional<Evaluation> findByName(String name);
}
