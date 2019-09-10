package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Question;
import br.com.unipac.cpa.model.repository.QuestionRepository;
import br.com.unipac.cpa.model.service.QuestionService;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Cacheable;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean sendInformation(Question question) {
        logger.info("Saved: " + !StringUtils.isEmpty(question.toString()));
        return false;
    }

    @Override
    public Question save(Question e) {
        return questionRepository.save(e);
    }

    @Override
    public Question edit(Long id, Question question) {
        if (id != null && question != null) {
            Optional<Question> result = findById(id);

            if (result.isPresent()) {
                question.setId(id);
                return questionRepository.save(question);
            } else {
                throw new ResourceNotFoundException("Questão não existe.");
            }
        }
        return null;
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    @Cacheable(Constant.CLIENTS_IN_CACHE)
    public Page<Question> findAllPageable(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public boolean remove(Long id) {
        Optional<Question> result = findById(id);

        if (result != null) {
            questionRepository.deleteById(id);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Optional<Question> findByName(String titulo){return questionRepository.findByName(titulo);}
}