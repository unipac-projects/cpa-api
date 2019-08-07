package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.service.PersonTypeService;
import org.springframework.stereotype.Service;

@Service
public class PersonTypeServiceImpl implements PersonTypeService {

	@Override
	public Optional<List<PersonType>> all() {
		List<PersonType> types = PersonType.getPersonTypes();
		return Optional.of(types);
	}

}
