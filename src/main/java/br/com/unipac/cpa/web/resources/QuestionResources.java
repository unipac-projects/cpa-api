package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.QuestionRequest;
import br.com.unipac.cpa.web.dto.response.QuestionResponse;
import br.com.unipac.cpa.web.support.QuestionSupport;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/questions")
public class QuestionResources {
    private final Logger logger = LoggerFactory.getLogger(QuestionResources.class);

    @Autowired
    private QuestionSupport questionSupport;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<List<QuestionResponse>> list() {
        List<QuestionResponse> questionRequest = questionSupport.list();

        if(questionRequest != null) {
            logger.info(Constants.TOTAL + questionRequest.size());
            return ResponseEntity.ok(questionRequest);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<QuestionResponse> get(@PathVariable("id") Long id) {
        QuestionResponse questionResponse = questionSupport.convertToFindById(id);

        if (questionResponse != null) {
            //TO DO - EDITAR CONSTANTS
            logger.info(Constants.TOTAL + questionResponse.toString());
            return ResponseEntity.ok(questionResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    @CacheEvict(value = Constants.SEGMENTS_IN_CACHE, allEntries = true)
    public ResponseEntity<QuestionResponse> add(@Valid @RequestBody QuestionRequest questionRequest) {
        QuestionResponse questionResponse = questionSupport.convertToCreate(questionRequest);

        if (questionResponse != null) {
            logger.info(Constants.TOTAL + questionResponse.toString());
            return ResponseEntity.ok(questionResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    @CacheEvict(value = Constants.SEGMENTS_IN_CACHE, allEntries = true)
    public ResponseEntity<QuestionResponse> change(@PathVariable("id") Long id,
                                                 @RequestBody QuestionRequest questionRequest) {
        QuestionResponse result = questionSupport.convertToChange(id, questionRequest);
        if (result != null) {
            logger.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    @CacheEvict(value = Constants.SEGMENTS_IN_CACHE, allEntries = true)
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean result = questionSupport.remove(id);
        if (result) {
            return ResponseEntity.ok(Constants.DADOS_DELETADOS);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/find")
    @ResponseBody
    @Timed
    public ResponseEntity<QuestionResponse> findByName(@RequestParam(value="name", required=false) String name) {
        QuestionResponse result = questionSupport.convertToFindByName (name);

        if (result != null) {
            logger.info(Constants.TOTAL + result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
