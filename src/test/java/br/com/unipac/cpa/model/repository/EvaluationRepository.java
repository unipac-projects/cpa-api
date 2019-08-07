package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByName(String name);
}
