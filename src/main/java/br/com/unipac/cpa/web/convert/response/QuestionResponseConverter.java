package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuestionResponseConverter implements Converter<Question, QuestionResponse> {
    @Override
    public QuestionResponse convert(Question question) {
        QuestionResponse questionResponse =  new QuestionResponse();
        questionResponse.setTitle(question.getTitle());
        questionResponse.setDescription(question.getDescription());
        questionResponse.setEvaluationId(question.getEvaluation());
        return questionResponse;
    }
}
