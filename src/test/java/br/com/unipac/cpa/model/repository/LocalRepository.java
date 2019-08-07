package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
