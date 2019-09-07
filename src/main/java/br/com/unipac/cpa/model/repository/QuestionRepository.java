package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
    Optional<Question> findByName(String title);
}
