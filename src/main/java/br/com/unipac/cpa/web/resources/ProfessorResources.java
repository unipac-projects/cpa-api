package br.com.unipac.cpa.web.resources;

import java.util.List;

import br.com.unipac.cpa.web.support.ProfessorSupport;
import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.ProfessorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import br.com.unipac.cpa.web.dto.response.ProfessorResponse;

@RestController
@RequestMapping("/v1/professors")
public class ProfessorResources {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProfessorSupport professorSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<ProfessorResponse>> list() {
		List<ProfessorResponse> result = professorSupport.list();
		
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
	public ResponseEntity<ProfessorResponse> get(@PathVariable("id") Long id) {
		ProfessorResponse result = professorSupport.convertToFindById(id);
		
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
	public ResponseEntity<ProfessorResponse> add(@RequestBody ProfessorRequest professorRequest) {
		ProfessorResponse result = professorSupport.convertToCreate(professorRequest);
		
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
	public ResponseEntity<ProfessorResponse> change(@PathVariable("id") Long id, @RequestBody ProfessorRequest professorRequest) {
		ProfessorResponse result = professorSupport.convertToChange(id, professorRequest);
		
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
		boolean result = professorSupport.remove(id);
		
		if (result) {
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/find")
	@ResponseBody
	@Timed
	public ResponseEntity<ProfessorResponse> findByName(@RequestParam(value="name", required=false) String name) {
		ProfessorResponse result = professorSupport.convertToFindByName(name);

		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
