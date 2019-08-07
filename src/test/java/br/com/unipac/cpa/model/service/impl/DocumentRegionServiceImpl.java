package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.service.DocumentRegionService;
import org.springframework.stereotype.Service;

@Service
public class DocumentRegionServiceImpl implements DocumentRegionService {

	@Override
	public Optional<List<DocumentRegion>> all() {
		List<DocumentRegion> regions= DocumentRegion.getDocumentRegions();
		return Optional.of(regions);
	}

}
