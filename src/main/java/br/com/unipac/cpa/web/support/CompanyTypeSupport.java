package br.com.unipac.cpa.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.web.dto.response.CompanyTypeResponse;
import br.com.unipac.cpa.model.domain.CompanyType;
import br.com.unipac.cpa.model.service.CompanyTypeService;
import br.com.unipac.cpa.web.dto.request.CompanyTypeRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CompanyTypeSupport {

    private static final Logger log = LogManager.getLogger(CompanyTypeSupport.class);

    @Autowired
    private CompanyTypeService service;

    @Autowired
    private ConversionService conversion;

    public CompanyTypeResponse convertToFindById(Long id) {
        CompanyTypeResponse founded = null;
        Optional<CompanyType> companyType = service.findById(id);

        if (companyType.isPresent()) {
            founded = conversion.convert(companyType.get(), CompanyTypeResponse.class);
            log.info("CompanyType: " + founded.toString());
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public CompanyTypeResponse convertToFindByName(String name) {
        Optional<CompanyType> companyType = service.findByName(name);
        CompanyTypeResponse founded = conversion.convert(companyType.get(), CompanyTypeResponse.class);
        log.info("CompanyType: " + founded.toString());
        return founded;
    }

    public List<CompanyTypeResponse> list() {
        List<CompanyTypeResponse> companyTypes = new ArrayList<>();
        service.listAll().forEach(companyType -> {
            CompanyTypeResponse saved = conversion.convert(companyType, CompanyTypeResponse.class);
            companyTypes.add(saved);
        });
        return companyTypes;
    }

    public CompanyTypeResponse convertToCreate(CompanyTypeRequest companyTypeRequest) {
        CompanyType clientType = conversion.convert(companyTypeRequest, CompanyType.class);
        CompanyType result = service.save(clientType);
        return conversion.convert(result, CompanyTypeResponse.class);
    }

    public CompanyTypeResponse convertToChange(Long id, CompanyTypeRequest companyTypeRequest) {
        CompanyType clientType = conversion.convert(companyTypeRequest, CompanyType.class);
        CompanyType result = service.edit(id, clientType);
        return conversion.convert(result, CompanyTypeResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
