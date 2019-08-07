package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.service.DisciplineService;
import br.com.unipac.cpa.web.dto.request.DisciplineRequest;
import br.com.unipac.cpa.web.dto.response.DisciplineResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DisciplineSupport {

    private static final Logger log = LogManager.getLogger(DisciplineSupport.class);

    @Autowired
    private DisciplineService service;

    @Autowired
    private ConversionService conversion;

    public DisciplineResponse convertToFindById(Long id) {
        DisciplineResponse founded = null;
        Optional<Discipline> businessFront = service.findById(id);

        if (businessFront.isPresent()) {
            founded = conversion.convert(businessFront.get(), DisciplineResponse.class);
            log.info("Discipline: " + founded.toString());
        } else {
            throw new ResourceNotFoundException("Company Type not found");
        }

        return founded;
    }

    public DisciplineResponse convertToFindByName(String name) {
        Optional<Discipline> businessFront = service.findByName(name);
        DisciplineResponse founded = conversion.convert(businessFront.get(), DisciplineResponse.class);
        log.info("Discipline: " + founded.toString());
        return founded;
    }

    public List<DisciplineResponse> list() {
        List<DisciplineResponse> businessFronts = new ArrayList<>();
        service.listAll().forEach(businessFront -> {
            DisciplineResponse saved = conversion.convert(businessFront, DisciplineResponse.class);
            businessFronts.add(saved);
        });
        return businessFronts;
    }

    public DisciplineResponse convertToCreate(DisciplineRequest disciplineRequest) {
        Discipline discipline = conversion.convert(disciplineRequest, Discipline.class);
        Discipline result = service.save(discipline);
        return conversion.convert(result, DisciplineResponse.class);
    }

    public DisciplineResponse convertToChange(Long id, DisciplineRequest disciplineRequest) {
        Discipline discipline = conversion.convert(disciplineRequest, Discipline.class);
        Discipline result = service.edit(id, discipline);
        return conversion.convert(result, DisciplineResponse.class);
    }

    public boolean remove(Long id) {
        return service.remove(id);
    }
}
