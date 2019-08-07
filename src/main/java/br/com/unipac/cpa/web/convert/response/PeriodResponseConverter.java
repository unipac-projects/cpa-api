package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.web.dto.response.PeriodResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PeriodResponseConverter implements Converter<Period, PeriodResponse> {
    @Override
    public PeriodResponse convert(Period period) {
        PeriodResponse periodResponse =  new PeriodResponse();
        periodResponse.setName(period.getName());
        periodResponse.setProfessor(period.getProfessor());
        return periodResponse;
    }
}
