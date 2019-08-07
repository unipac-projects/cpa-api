package br.com.unipac.cpa.web.convert.response;

import br.com.unipac.cpa.web.dto.response.DocumentRegionResponse;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentRegionResponseConverter implements Converter<DocumentRegion, DocumentRegionResponse> {

	@Override
	public DocumentRegionResponse convert(DocumentRegion source) {
		return DocumentRegionResponse.builder()
				.name(source.getState())
				.code(source.name())
				.sigla(source.getAcronym())
				.build();
	}

}
