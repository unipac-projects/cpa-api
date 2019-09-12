package br.com.unipac.cpa.model.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<E, I extends Serializable> {
    E save(E e);
    E edit(I id, E e);
    List<E> listAll();
    Page<E> findAllPageable(Pageable pageable);
    Optional<E> findById(I id);
	boolean remove(I id);

    QuestionResponse convert(Question question, Class<QuestionResponse> questionResponseClass);
}
