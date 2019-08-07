package br.com.unipac.cpa.web.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.web.dto.response.PersonTypeResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.service.PersonTypeService;

@Component
public class PersonTypeSupport {

	private static final Logger log = LogManager.getLogger(CompanyTypeSupport.class);

	@Autowired
	private PersonTypeService service;

	@Autowired
	private ConversionService documentRegionConvert;

	public List<PersonTypeResponse> list() {
		List<PersonTypeResponse> documentRegions = new ArrayList<>();
		Optional<List<PersonType>> list = service.all();
		
		if (list.isPresent()) {
			list.get().forEach(documentRegion -> {
				PersonTypeResponse saved = documentRegionConvert.convert(documentRegion, PersonTypeResponse.class);
				documentRegions.add(saved);
			});
			
			log.info(documentRegions.size());
			return documentRegions;
		}
		
		return Collections.emptyList();
	}
}
