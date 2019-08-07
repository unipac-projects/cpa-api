package br.com.unipac.cpa.model.repository;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
	Optional<CompanyType> findByName(String name);
}

