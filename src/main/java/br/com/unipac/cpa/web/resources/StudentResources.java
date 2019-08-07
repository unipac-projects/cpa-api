package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.StudentRequest;
import br.com.unipac.cpa.web.dto.response.StudentResponse;
import br.com.unipac.cpa.web.support.StudentSupport;
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
@RequestMapping("/v1/students")
public class StudentResources {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentSupport studentSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<StudentResponse>> list() {
		List<StudentResponse> result = studentSupport.list();
		
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
	public ResponseEntity<StudentResponse> get(@PathVariable("id") Long id) {
		StudentResponse result = studentSupport.convertToFindById(id);
		
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
	public ResponseEntity<StudentResponse> add(@RequestBody StudentRequest studentRequest) {
		StudentResponse result = studentSupport.convertToCreate(studentRequest);
		
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
	public ResponseEntity<StudentResponse> change(@PathVariable("id") Long id, @RequestBody StudentRequest studentRequest) {
		StudentResponse result = studentSupport.convertToChange(id, studentRequest);
		
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
		boolean result = studentSupport.remove(id);
		
		if (result) {
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/find")
	@ResponseBody
	@Timed
	public ResponseEntity<StudentResponse> findByName(@RequestParam(value="name", required=false) String name) {
		StudentResponse result = studentSupport.convertToFindByName(name);

		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
