package br.com.unipac.cpa.model.service;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.DocumentRegion;

public interface DocumentRegionService {
	Optional<List<DocumentRegion>> all();
}
