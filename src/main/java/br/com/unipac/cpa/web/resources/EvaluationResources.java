package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.EvaluationRequest;
import br.com.unipac.cpa.web.dto.response.EvaluationResponse;
import br.com.unipac.cpa.web.support.EvaluationSupport;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/evaluations")
public class EvaluationResources {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EvaluationSupport evaluationSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<EvaluationResponse>> list() {
		List<EvaluationResponse> result = evaluationSupport.list();
		
		if (result != null) {
			logger.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<EvaluationResponse> get(@PathVariable("id") Long id) {
		EvaluationResponse result = evaluationSupport.convertToFindById(id);
		
		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = Constants.CLIENTS_IN_CACHE, allEntries = true)
	public ResponseEntity<EvaluationResponse> add(@RequestBody EvaluationRequest evaluationRequest) {
		EvaluationResponse result = evaluationSupport.convertToCreate(evaluationRequest);
		
		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = Constants.CLIENTS_IN_CACHE, allEntries = true)
	public ResponseEntity<EvaluationResponse> change(@PathVariable("id") Long id, @RequestBody EvaluationRequest evaluationRequest) {
		EvaluationResponse result = evaluationSupport.convertToChange(id, evaluationRequest);
		
		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = Constants.CLIENTS_IN_CACHE, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		boolean result = evaluationSupport.remove(id);
		
		if (result) {
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/find")
	@ResponseBody
	@Timed
	public ResponseEntity<EvaluationResponse> findByName(@RequestParam(value="name", required=false) String name) {
		EvaluationResponse result = evaluationSupport.convertToFindByName(name);

		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
