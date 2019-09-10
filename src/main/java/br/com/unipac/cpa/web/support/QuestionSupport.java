package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.service.QuestionService;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QuestionSupport {
    private static final Logger log = LogManager.getLogger(QuestionSupport.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ConversionService conversionService;

    public QuestionResponse convertToFindById(Long id){
        Optional<Question> question = questionService.findById(id);
        QuestionResponse founded = conversionService.convert(question.get(), QuestionResponse.class);
        log.info("Question: " + founded.toString());
        return founded;
    }

    public QuestionResponse convertToFindByName(String name) {
        Optional<Question> question = questionService.findByName(name);
        QuestionResponse founded = conversionService.convert(question.get(), QuestionResponse.class);
        log.info("Course: " + founded.toString());
        return founded;
    }

    public List<QuestionResponse> list() {
        List<QuestionResponse> questionResponses = new ArrayList<>();
        questionService.listAll().forEach(question -> {
            QuestionResponse saved = questionService.convert(question, QuestionResponse.class);
            questionResponses.add(saved);
        });
        return questionResponses;
    }

    public QuestionResponse convertToCreate(QuestionRequest questionRequest) {
        Question question = conversionService.convert(questionRequest, Question.class);
        Question result = questionService.save(question);
        return conversionService.convert(result, QuestionResponse.class);
    }

    public QuestionResponse convertToChange(Long id, QuestionRequest questionRequest) {
        Question question = conversionService.convert(questionRequest, Question.class);
        Question result = questionService.edit(id, question);
        return conversionService.convert(result, QuestionResponse.class);
    }

    public boolean remove(Long id) {
        return questionService.remove(id);
    }
}
