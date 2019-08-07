package br.com.unipac.cpa.model.repository;

import java.util.Optional;

import br.com.unipac.cpa.model.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	Optional<Company> findByName(String name);
}
