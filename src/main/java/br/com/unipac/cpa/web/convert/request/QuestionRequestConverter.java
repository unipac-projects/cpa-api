package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Evaluation;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.service.impl.EvaluationServiceImpl;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuestionRequestConverter implements Converter<QuestionRequest, Question> {

    @Autowired
    private EvaluationServiceImpl evaluationRepository;

    @Override
    public Question convert(QuestionRequest questionRequest) {
        Question question =  new Question();
        question.setTitle(questionRequest.getTitle());
        question.setDescription(questionRequest.getDescription());
        Optional<Evaluation> evaluation = evaluationRepository.findById(questionRequest.getId());
        question.setEvaluation(String.valueOf(evaluation.get()));
        return question;
    }
}