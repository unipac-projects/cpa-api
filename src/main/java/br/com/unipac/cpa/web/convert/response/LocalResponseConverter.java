package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.model.domain.Local;
import br.com.unipac.cpa.web.dto.response.LocalResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalResponseConverter implements Converter<Local, LocalResponse> {

   @Override
    public LocalResponse convert(Local local) {
        String address = local.getCity() + "/" + local.getUf();
        return LocalResponse.builder().id(local.getId()).address(address).build();
    }
}
