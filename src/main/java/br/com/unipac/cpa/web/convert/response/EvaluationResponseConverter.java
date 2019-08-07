package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.web.dto.response.EvaluationResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EvaluationResponseConverter implements Converter<Evaluation, EvaluationResponse> {

	@Override
	public EvaluationResponse convert(Evaluation source) {
		return EvaluationResponse.builder()
				.id(source.getId())
				.name(source.getName())
				.description(source.getDescription())
				.date(source.getDate())
				.build();

	}

}
