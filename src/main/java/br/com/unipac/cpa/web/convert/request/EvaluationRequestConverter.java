package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.util.DateUtil;
import br.com.unipac.cpa.web.dto.request.EvaluationRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EvaluationRequestConverter implements Converter<EvaluationRequest, Evaluation> {

	@Override
	public Evaluation convert(EvaluationRequest source) {
		Evaluation evaluation = Evaluation.builder()
				.name(source.getName())
				.description(source.getDescription())
				.date(DateUtil.convert(source.getDate()))
				.build();
		
		return evaluation;
	}

}
