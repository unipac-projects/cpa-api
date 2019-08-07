package br.com.unipac.cpa.model.service;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.CompanyType;

public interface CompanyTypeService extends CrudService<CompanyType, Long> {
	Optional<CompanyType> findByName(String name);
}