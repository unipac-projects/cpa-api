package br.com.unipac.cpa.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.web.dto.response.CompanyResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.service.CompanyService;
import br.com.unipac.cpa.web.dto.request.CompanyRequest;

@Component
public class CompanySupport {

	private static final Logger log = LogManager.getLogger(CompanySupport.class);

	@Autowired
	private CompanyService service;

	@Autowired
	private ConversionService conversionService;

	public CompanyResponse convertToFindById(Long id) {
		Optional<Company> company = service.findById(id);
		CompanyResponse founded = conversionService.convert(company.get(), CompanyResponse.class);
		log.info("Company: " + founded.toString());
		return founded;
	}

	public CompanyResponse convertToFindByName(String name) {
		Optional<Company> company = service.findByName(name);
		CompanyResponse founded = conversionService.convert(company.get(), CompanyResponse.class);
		log.info("Company: " + founded.toString());
		return founded;
	}

	public List<CompanyResponse> list() {
		List<CompanyResponse> companys = new ArrayList<>();
		service.listAll().forEach(company -> {
			CompanyResponse saved = conversionService.convert(company, CompanyResponse.class);
			companys.add(saved);
		});
		return companys;
	}

	public CompanyResponse convertToCreate(CompanyRequest companyRequest) {
		Company company = conversionService.convert(companyRequest, Company.class);
		Company saved = service.save(company);
		CompanyResponse result = getConverter(saved);
		return result;
	}

	private CompanyResponse getConverter(Company company) {
		return conversionService.convert(company, CompanyResponse.class);
	}

	public CompanyResponse convertToChange(Long id, CompanyRequest companyRequest) {
		Company company = conversionService.convert(companyRequest, Company.class);
		Company result = service.edit(id, company);
		return conversionService.convert(result, CompanyResponse.class);
	}

	public boolean remove(Long id) {
		return service.remove(id);
	}
}
