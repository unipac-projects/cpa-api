package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.util.Optional;

@Component
public class QuestionRequestConverter implements Converter<QuestionRequest, Question> {

    @Autowired
    private OpcaoRepository opcaoRepository;

    @Override
    public Question converter(QuestionRequest questionRequest){
        Question question = new Question();
        question.setTitle(questionRequest.getTitle());
        question.setDescription(questionRequest.getDescription());
//        Optional<>
//        question.setEvaluation();
//        question.setScala_likert();
        return question;
    }
}
