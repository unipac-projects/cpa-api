package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.repository.EvaluationRepository;
import br.com.unipac.cpa.model.service.EvaluationService;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements EvaluationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public boolean sendInformation(Evaluation evaluation) {
		logger.info("Saved: " + !StringUtils.isEmpty(evaluation.toString()));
		return false;
	}

	@Override
	public Evaluation save(Evaluation e) {
		return evaluationRepository.save(e);
	}

	@Override
	public Evaluation edit(Long id, Evaluation evaluation) {
		if (id != null && evaluation != null) {
			Optional<Evaluation> result = findById(id);

			if (result.isPresent()) {
				evaluation.setId(id);
				return evaluationRepository.save(evaluation);
			} else {
				throw new ResourceNotFoundException("ProjectTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Evaluation> findById(Long id) {
		return evaluationRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public List<Evaluation> listAll() {
		Iterable<Evaluation> itr = evaluationRepository.findAll();
		return (List<Evaluation>) itr;
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public Page<Evaluation> findAllPageable(Pageable pageable) {
		return evaluationRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Evaluation> result = findById(id);

		if (result != null) {
			evaluationRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Evaluation> findByName(String name) {
		return evaluationRepository.findByName(name);
	}

}
