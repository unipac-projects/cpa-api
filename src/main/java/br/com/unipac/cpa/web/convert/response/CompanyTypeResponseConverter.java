package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.CompanyType;
import br.com.unipac.cpa.web.dto.response.CompanyTypeResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyTypeResponseConverter implements Converter<CompanyType, CompanyTypeResponse> {

    @Override
    public CompanyTypeResponse convert(CompanyType source) {
        return CompanyTypeResponse.builder().id(source.getId()).name(source.getName()).build();
    }
}
