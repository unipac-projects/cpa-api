package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.Local;
import br.com.unipac.cpa.model.service.LocalService;
import br.com.unipac.cpa.web.dto.response.LocalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocalSupport {

    @Autowired
    private ConversionService localConverter;

    @Autowired
    private LocalService localService;

    public List<LocalResponse> get() {
        List<LocalResponse> dtos = new ArrayList<>();

        List<Local> result = localService.listAll();
        result.stream().forEach(local -> {
            LocalResponse dto = localConverter.convert(local, LocalResponse.class);
            dtos.add(dto);
        });

        return dtos;
    }
}
