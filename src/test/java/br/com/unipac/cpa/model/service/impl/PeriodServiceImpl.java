package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Period;
import br.com.unipac.cpa.model.repository.PeriodRepository;
import br.com.unipac.cpa.model.service.PeriodService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeriodRepository periodRepository;

	@Override
	public boolean sendInformation(Period period) {
		logger.info("Saved: " + !StringUtils.isEmpty(period.toString()));
		return false;
	}

	@Override
	public Period save(Period e) {
		return periodRepository.save(e);
	}

	@Override
	public Period edit(Long id, Period period) {
		if (id != null && period != null) {
			Optional<Period> result = findById(id);

			if (result.isPresent()) {
				period.setId(id);
				return periodRepository.save(period);
			} else {
				throw new ResourceNotFoundException("BusinessFrontTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Period> findById(Long id) {
		return periodRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public List<Period> listAll() {
		Iterable<Period> itr = periodRepository.findAll();
		return (List<Period>) itr;
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public Page<Period> findAllPageable(Pageable pageable) {
		return periodRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Period> result = findById(id);

		if (result != null) {
			periodRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Period> findByName(String name) {
		return periodRepository.findByName(name);
	}

}
