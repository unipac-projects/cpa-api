package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.service.QuestionService;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component
public class QuestionSupport {
    public static final Logger log = LogManager.getLogger(QuestionSupport.class);

    @Autowired
    private QuestionService service;

    @Autowired
    private ConversionService conversion;

    public QuestionResponse convertToFindById(Long id){
        Optional<Question> question = service.findById(id);
        QuestionResponse founded = conversion.convert(question.get(), QuestionResponse.class);
        log.info("Question: " + founded.toString());
        return founded;
    }

    public QuestionResponse convertToCreate(QuestionRequest questionRequest) {
        Question question = conversion.convert(questionRequest, Question.class);
        Question result = service.save(question);
        return conversion.convert(result, QuestionResponse.class);
    }

    public QuestionResponse convertToChange(Long id, QuestionRequest questionRequest) {
        Question question = conversion.convert(questionRequest, Question.class);
        Question result = service.edit(id, question);
        return conversion.convert(result, QuestionResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
