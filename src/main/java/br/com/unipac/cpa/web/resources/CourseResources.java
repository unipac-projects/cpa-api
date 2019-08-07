package br.com.unipac.cpa.web.resources;

import java.util.List;

import javax.validation.Valid;

import br.com.unipac.cpa.web.support.CourseSupport;
import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.dto.request.CourseRequest;
import br.com.unipac.cpa.web.dto.response.CourseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/v1/courses")
public class CourseResources {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseSupport conversionSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<CourseResponse>> list() {
		List<CourseResponse> courseRequests = conversionSupport.list();

		if (courseRequests != null) {
			logger.info(Constants.TOTAL + courseRequests.size());
			return ResponseEntity.ok(courseRequests);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<CourseResponse> get(@PathVariable("id") Long id) {
		CourseResponse result = conversionSupport.convertToFindById(id);

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
	@CacheEvict(value = Constants.SEGMENTS_IN_CACHE, allEntries = true)
	public ResponseEntity<CourseResponse> add(@Valid @RequestBody CourseRequest courseRequest) {
		CourseResponse result = conversionSupport.convertToCreate(courseRequest);

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
	@CacheEvict(value = Constants.SEGMENTS_IN_CACHE, allEntries = true)
	public ResponseEntity<CourseResponse> change(@PathVariable("id") Long id,
												 @RequestBody CourseRequest courseRequest) {
		CourseResponse result = conversionSupport.convertToChange(id, courseRequest);
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
		boolean result = conversionSupport.remove(id);
		if (result) {
			return ResponseEntity.ok(Constants.DADOS_DELETADOS);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(path = "/find")
	@ResponseBody
	@Timed
	public ResponseEntity<CourseResponse> findByName(@RequestParam(value="name", required=false) String name) {
		CourseResponse result = conversionSupport.convertToFindByName(name);

		if (result != null) {
			logger.info(Constants.TOTAL + result.toString());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
