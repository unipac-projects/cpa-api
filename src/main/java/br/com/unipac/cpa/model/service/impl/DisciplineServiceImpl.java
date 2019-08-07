package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.repository.DisciplineRepository;
import br.com.unipac.cpa.model.service.DisciplineService;
import br.com.unipac.cpa.model.service.DisciplineService;
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
public class DisciplineServiceImpl implements DisciplineService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	public boolean sendInformation(Discipline discipline) {
		logger.info("Saved: " + !StringUtils.isEmpty(discipline.toString()));
		return false;
	}

	@Override
	public Discipline save(Discipline e) {
		return disciplineRepository.save(e);
	}

	@Override
	public Discipline edit(Long id, Discipline discipline) {
		if (id != null && discipline != null) {
			Optional<Discipline> result = findById(id);

			if (result.isPresent()) {
				discipline.setId(id);
				return disciplineRepository.save(discipline);
			} else {
				throw new ResourceNotFoundException("Disciplina não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Discipline> findById(Long id) {
		return disciplineRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public List<Discipline> listAll() {
		Iterable<Discipline> itr = disciplineRepository.findAll();
		return (List<Discipline>) itr;
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public Page<Discipline> findAllPageable(Pageable pageable) {
		return disciplineRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Discipline> result = findById(id);

		if (result != null) {
			disciplineRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Discipline> findByName(String name) {
		return disciplineRepository.findByName(name);
	}

}
