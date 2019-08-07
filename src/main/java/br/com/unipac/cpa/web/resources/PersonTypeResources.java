package br.com.unipac.cpa.web.resources;

import java.util.List;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.web.support.PersonTypeSupport;
import br.com.unipac.cpa.web.dto.response.PersonTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping(path = "/v1/person-types")
public class PersonTypeResources {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonTypeSupport conversionSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<PersonTypeResponse>> get() {
		List<PersonTypeResponse> result = conversionSupport.list();

		if (result != null) {
			logger.info(Constants.TOTAL + result.size());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

}
