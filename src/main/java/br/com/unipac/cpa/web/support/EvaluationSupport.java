package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.model.service.EvaluationService;
import br.com.unipac.cpa.web.dto.request.EvaluationRequest;
import br.com.unipac.cpa.web.dto.response.EvaluationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EvaluationSupport {

	private final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
	private ConversionService conversionService;
	
	public EvaluationResponse convertToFindById(Long id) {
		Optional<Evaluation> project = evaluationService.findById(id);
		EvaluationResponse founded = conversionService.convert(project.get(), EvaluationResponse.class);
		log.info("Evaluation" + founded.toString());
		return founded;
	}

	public EvaluationResponse convertToFindByName(String name) {
		Optional<Evaluation> project = evaluationService.findByName(name);
		EvaluationResponse founded = conversionService.convert(project.get(), EvaluationResponse.class);
		log.info("Evaluation: " + founded.toString());
		return founded;
	}

	public List<EvaluationResponse> list() {
		List<EvaluationResponse> projectes = new ArrayList<>();
		evaluationService.listAll().forEach(project -> {
			EvaluationResponse saved = conversionService.convert(project, EvaluationResponse.class);
			projectes.add(saved);
		});
		return projectes;
	}

	public EvaluationResponse convertToCreate(EvaluationRequest projectRequest) {
		Evaluation evaluation = conversionService.convert(projectRequest, Evaluation.class);
		Evaluation saved = evaluationService.save(evaluation);
		return getConverter(saved);
	}

	private EvaluationResponse getConverter(Evaluation evaluation) {
		return conversionService.convert(evaluation, EvaluationResponse.class);
	}

	public EvaluationResponse convertToChange(Long id, EvaluationRequest projectRequest) {
		Evaluation evaluation = conversionService.convert(projectRequest, Evaluation.class);
		Evaluation updated = evaluationService.edit(id, evaluation);
		return getConverter(updated);
	}

	public boolean remove(Long id) {
		return evaluationService.remove(id);
	}
}
