package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessFrontRepository extends JpaRepository<Period, Long> {
    Optional<Period> findByName(String name);
}
