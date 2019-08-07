package br.com.unipac.cpa.model.service;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.PersonType;

public interface PersonTypeService {
	Optional<List<PersonType>> all();
}
