package br.com.unipac.cpa.model.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.constants.Constants;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.exception.ResourceNotFoundException;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.model.service.ProfessorService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProfessorRepository studentRepository;

	@Override
	public boolean sendInformation(Professor professor) {
		logger.info("Saved: " + !StringUtils.isEmpty(professor.toString()));
		return false;
	}

	@Override
	public Professor save(Professor e) {
		return studentRepository.save(e);
	}

	@Override
	public Professor edit(Long id, Professor professor) {
		if (id != null && professor != null) {
			Optional<Professor> result = findById(id);

			if (result.isPresent()) {
				professor.setId(id);
				return studentRepository.save(professor);
			} else {
				throw new ResourceNotFoundException("ClientTypee não existe");
			}
		}
		return null;
	}

	@Override
	public Optional<Professor> findById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public List<Professor> listAll() {
		Iterable<Professor> itr = studentRepository.findAll();
		return (List<Professor>) itr;
	}

	@Override
	@Cacheable(Constants.CLIENTS_IN_CACHE)
	public Page<Professor> findAllPageable(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	@Override
	public boolean remove(Long id) {
		Optional<Professor> result = findById(id);

		if (result != null) {
			studentRepository.deleteById(id);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	@Override
	public Optional<Professor> findByName(String name) {
		return studentRepository.findByName(name);
	}

}
