package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.web.dto.request.PeriodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BusinessFrontRequestConverter implements Converter<PeriodRequest, Period> {

    @Autowired
    private ProfessorRepository clientRepository;

    @Override
    public Period convert(PeriodRequest periodRequest) {
        Period period =  new Period();
        period.setName(periodRequest.getName());
        period.setDescription(periodRequest.getDescription());
        Optional<Professor> client = clientRepository.findById(periodRequest.getClientId());
        period.setProfessor(client.get());
        return period;
    }
}
