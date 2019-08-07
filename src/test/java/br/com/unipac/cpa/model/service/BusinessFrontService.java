package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.model.domain.Period;

import java.util.Optional;

public interface BusinessFrontService extends CrudService<Period, Long> {

	boolean sendInformation(Period period);

	Optional<Period> findByName(String name);
}
