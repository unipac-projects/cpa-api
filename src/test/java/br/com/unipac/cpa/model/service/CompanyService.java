package br.com.unipac.cpa.model.service;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Company;

public interface CompanyService extends CrudService<Company, Long> {
	Optional<Company> findByName(String name);
}